package io.swagger.service;

import io.swagger.dao.BalanceRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.Balance;
import io.swagger.model.Transaction;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
    public Transaction getTransactionById(long id){
        return transactionRepository.findOne(id);
    }

    // Add a transaction to the database
    public void addTransaction(Transaction transaction){
        // Remove funds from sending account
        balanceService.removeAmount(transaction.getAccountFrom(), transaction.getAmount());
        // Add funds to receiving account
        balanceService.addAmount(transaction.getAccountTo(), transaction.getAmount());
        // Save successful transaction
        transactionRepository.save(transaction);
        System.out.println(transaction);
    }

    // Check for transaction type and legality
    public Transaction.TransactionTypeEnum getTransactionType(String accountTo, String accountFrom){

        // Check if transaction isn't to self
        if (accountTo.equals(accountFrom))
            throw new IllegalArgumentException("Can't transfer to the same account");

        Account to = accountService.getAccountById(accountTo);
        Account from = accountService.getAccountById(accountFrom);

        // Check if transaction is a withdraw
        if (from.getAccountType() == Account.AccountTypeEnum.SAVING){
            if (from.getAccountHolder().equals(to.getAccountHolder()))
                return Transaction.TransactionTypeEnum.DEPOSIT;
            else
                throw new IllegalArgumentException("Can't transfer from savings account to someone else");
        }
        // Check if transaction is a deposit
        if (to.getAccountType() == Account.AccountTypeEnum.SAVING){
            if (from.getAccountHolder().equals(to.getAccountHolder()))
                return Transaction.TransactionTypeEnum.WITHDRAW;
            else
                throw new IllegalArgumentException("Can't transfer from current to someone else savings account");
        }

        return Transaction.TransactionTypeEnum.TRANSFER;
    }

    // Check if transaction is within account limitations
    public void checkAccountLimits(Transaction transaction){
        Account account = accountService.getAccountById(transaction.getAccountFrom());
        Balance balance = balanceService.getBalanceById(transaction.getAccountFrom());
        if ( -1 == balance.getAmount().subtract(BigDecimal.valueOf(transaction.getAmount())).compareTo(BigDecimal.valueOf(account.getAbsoluteLimit()))){
            System.out.println("insufficient funds");
        }
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
