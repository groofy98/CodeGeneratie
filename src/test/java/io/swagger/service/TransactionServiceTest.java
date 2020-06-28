package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.dao.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.Balance;
import io.swagger.model.Transaction;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @MockBean
    private BalanceService balanceService;

    private List<Transaction> transactions;

    @BeforeEach
    public void setUp() {
        Account accountFrom = new Account("NL42INHO0000000002", (long) -100, (long) 100001, Account.AccountTypeEnum.CURRENT, true);
        Account accountTo =   new Account("NL42INHO0000000003", (long) 0, (long) 100001, Account.AccountTypeEnum.SAVING, true);
        Account account3 = new Account("NL42INHO0000000004", (long) 0, (long) 100002, Account.AccountTypeEnum.CURRENT, true);

        transactions = new ArrayList<>();
        Transaction transaction = new Transaction(BigDecimal.valueOf(777), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
        Transaction transaction2 = new Transaction(BigDecimal.valueOf(1000), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
        transactions.add(transaction);
        transactions.add(transaction2);

        Balance balance = new Balance("NL42INHO0000000002", BigDecimal.valueOf(1000));

        Mockito.when(accountService.getAccountById("NL42INHO0000000002")).thenReturn(accountFrom);
        Mockito.when(accountService.getAccountById("NL42INHO0000000003")).thenReturn(accountTo);
        Mockito.when(accountService.getAccountById("NL42INHO0000000004")).thenReturn(account3);

        Mockito.when(balanceService.getBalanceById("NL42INHO0000000002")).thenReturn(balance);

        Mockito.when(transactionRepository.findById(transaction.getId()))
                .thenReturn(java.util.Optional.of(transaction));
        Mockito.when(transactionRepository.findByAccountOnDate(OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS), transaction.getAccountFrom())).thenReturn(transactions);
    }

    @Test
    public void depositingShouldReturnCorrectType() throws ApiException {
        assertEquals(transactionService.getTransactionType("NL42INHO0000000002", "NL42INHO0000000003"), Transaction.TransactionTypeEnum.DEPOSIT);
    }

    @Test
    public void withdrawingShouldReturnCorrectType() throws ApiException{
        assertEquals(transactionService.getTransactionType("NL42INHO0000000003", "NL42INHO0000000002"), Transaction.TransactionTypeEnum.WITHDRAW);
    }

    @Test
    public void transferShouldReturnCorrectType() throws ApiException{
        assertEquals(transactionService.getTransactionType("NL42INHO0000000002", "NL42INHO0000000004"), Transaction.TransactionTypeEnum.TRANSFER);
        assertEquals(transactionService.getTransactionType("NL42INHO0000000004", "NL42INHO0000000002"), Transaction.TransactionTypeEnum.TRANSFER);
    }

    @Test
    public void throwExceptionWhenTransferringToSameAccount(){
        Exception exception = assertThrows(ApiException.class, () -> {
            transactionService.getTransactionType("NL42INHO0000000002", "NL42INHO0000000002");
        });
        String expectedMessage = "Can't transfer to the same account";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void throwExceptionWhenAmountIsToHigh(){
        Transaction illegalTransaction = new Transaction(BigDecimal.valueOf(1000000), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
        Exception exception = assertThrows(ApiException.class, () -> {
            transactionService.checkTransactionLimits(illegalTransaction);
        });
        String expectedMessage = "Given amount is to high";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void throwExceptionWhenAmountIsToLow(){
        Transaction illegalTransaction = new Transaction(BigDecimal.valueOf(-1), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
        Exception exception = assertThrows(ApiException.class, () -> {
            transactionService.checkTransactionLimits(illegalTransaction);
        });
        String expectedMessage = "The given amount should be higher than 0";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getTransactionTotalShouldReturnTotal(){
        Transaction transaction3 = new Transaction(BigDecimal.valueOf(1000), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
        assertTrue(transactionService.getTransactionTotalToday(transaction3).equals(BigDecimal.valueOf(1777)));
    }

    @Test
    public void accountLimitTrowsExceptionWhenReached(){
        Transaction illegalTransaction = new Transaction(BigDecimal.valueOf(2000), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
        Exception exception = assertThrows(ApiException.class, () -> {
            transactionService.checkAccountLimits(illegalTransaction);
        });
        String expectedMessage = "Insufficient funds";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void accountLimitDoesNothingWhenOK(){
        Transaction legalTransaction = new Transaction(BigDecimal.valueOf(100), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
    }

    @Test
    public void dayTotalReachedTrowsException(){
        transactions.add(new Transaction(BigDecimal.valueOf(10000000), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER));
        Transaction illegalTransaction = new Transaction(BigDecimal.valueOf(100), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
        Exception exception = assertThrows(ApiException.class, () -> {
            transactionService.checkAccountLimits(illegalTransaction);
        });
        String expectedMessage = "Day limit reached";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


}