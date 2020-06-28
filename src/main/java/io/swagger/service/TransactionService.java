package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.api.NotFoundException;
import io.swagger.dao.BalanceRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.model.*;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BalanceService balanceService;

    // No Args constructor
    private TransactionService(){
    }

    // Get a single transaction by transactionId
    public Transaction getTransactionById(long id) throws ApiException {
        Transaction transaction = transactionRepository.findById(id);
        return transaction;
    }

    // Check if user has access to account
    public boolean checkAuthorized(String accountId){
        return accountService.checkAuthorization(accountId);
    }

    // Add a transaction to the database
    public void addTransactionToDatabase(Transaction transaction){
        // Remove funds from sending account
        balanceService.removeAmount(transaction.getAccountFrom(), transaction.getAmount());
        // Add funds to receiving account
        balanceService.addAmount(transaction.getAccountTo(), transaction.getAmount());
        // Save successful transaction
        transactionRepository.save(transaction);
        System.out.println(transaction);
    }

    // Set a userID from securityDetails
    public void setUser(Transaction transaction){
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((UserDetail) userDetails).getUser();
        transaction.setUserId(user.getId());
    }

    // Handle a new incoming transaction from the api
    public void newTransaction(Transaction transaction) throws ApiException {
        // Set the user from Security context as the authorization check is already done
        setUser(transaction);
        // Set transaction type and check validity
        transaction.setTransactionType(getTransactionType(transaction.getAccountFrom(), transaction.getAccountTo()));
        // Check account limitations
        checkAccountLimits(transaction);
        // Check transaction limitations
        checkTransactionLimits(transaction);
        // All ok and adding the transaction to the database an returning http status 201/created
        addTransactionToDatabase(transaction);
    }

    // Check for transaction type and legality
    public Transaction.TransactionTypeEnum getTransactionType(String accountFrom, String accountTo) throws ApiException {

        // Check if transaction isn't to self
        if (accountTo.equals(accountFrom))
            throw new  ApiException(400, "Can't transfer to the same account");

        Account to = accountService.getAccountById(accountTo);
        Account from = accountService.getAccountById(accountFrom);

        // Check if transaction is a withdraw
        if (to.getAccountType() == Account.AccountTypeEnum.SAVING){
            if (from.getAccountHolder().equals(to.getAccountHolder()))
                return Transaction.TransactionTypeEnum.DEPOSIT;
            else
                throw new ApiException(400, "Can't transfer from savings account to someone else");
        }
        // Check if transaction is a deposit
        if (from.getAccountType() == Account.AccountTypeEnum.SAVING){
            if (from.getAccountHolder().equals(to.getAccountHolder()))
                return Transaction.TransactionTypeEnum.WITHDRAW;
            else
                throw new  ApiException(400, "Can't transfer from current to someone else savings account");
        }
        return Transaction.TransactionTypeEnum.TRANSFER;
    }

    // Check if transaction is within account limitations
    public void checkAccountLimits(Transaction transaction) throws ApiException {
        Account account = accountService.getAccountById(transaction.getAccountFrom());
        Balance balance = balanceService.getBalanceById(transaction.getAccountFrom());
        // Check if account doesn't surpass its absolute limit
        if ( -1 == balance.getAmount().subtract(transaction.getAmount()).compareTo(BigDecimal.valueOf(account.getAbsoluteLimit()))) {
            throw new  ApiException(400, "Insufficient funds");
        }
        // Check if transaction doesn't surpass day limit
        if ( 1 == getTransactionTotalToday(transaction).add(transaction.getAmount()).compareTo(Account.dayLimit)){
            throw new  ApiException(400, "Day limit reached");
        }
    }

    // Check if transaction is within the transaction limitations
    public void checkTransactionLimits(Transaction transaction) throws ApiException {
        // Check if the given amount is within transaction limit
        if (1 == transaction.getAmount().compareTo(Transaction.transactionLimit))
            throw new  ApiException(400, "Given amount is to high");
        if (-1 == transaction.getAmount().compareTo(BigDecimal.valueOf(0)))
            throw new  ApiException(400, "The given amount should be higher than 0");
    }

    // Returns the amount of money that a account has transferred today
    public BigDecimal getTransactionTotalToday(Transaction transaction){
        List<Transaction> transactions = getTransactionsTodayByAccountId(transaction);
        // Sums up all transactions of today
        BigDecimal dayTotal =  transactions.stream().map(t -> t.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return dayTotal;
    }

    // Get all transaction of today by account Id
    public List<Transaction> getTransactionsTodayByAccountId(Transaction transaction){
        OffsetDateTime dateFrom = OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS);
        System.out.println(dateFrom);
        return transactionRepository.findByAccountOnDate(dateFrom, transaction.getAccountFrom());
    }

    // Get all transactions
    public List<Transaction> getAllTransactions(){
        return (List<Transaction>) transactionRepository.findAll();
    }

    // Get all transactions to an account
    public List<Transaction> getTransactionsToAccountById(String accountId){
        return transactionRepository.findByAccountToOrderByDateDesc(accountId);
    }

    // Get all transactions from an account
    public List<Transaction> getTransactionsFromAccountById(String accountId){
        return transactionRepository.findByAccountFromOrderByDateDesc(accountId);
    }


    // Get all transactions with corresponding accountID
    public List<Transaction> getAllTransactionsById(String accountId){
        // Get all transactions from and to an account and merge the results
        //TODO Replace by transactionRepository.findByAccountOrderByDateDesc
        List<Transaction> result = Stream.concat(getTransactionsFromAccountById(accountId).stream(), getTransactionsToAccountById(accountId).stream())
                .collect(Collectors.toList());
        // Sort the list by date
        Collections.sort(result, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return t2.getDate().compareTo(t1.getDate());
            }
        });
        return result;
    }
}
