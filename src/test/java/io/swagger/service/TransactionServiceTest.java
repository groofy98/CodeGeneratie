package io.swagger.service;

import io.swagger.api.ApiException;
import io.swagger.dao.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @MockBean
    private TransactionRepository transactionRepository;

    @MockBean
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BalanceService balanceService;

    @BeforeEach
    public void setUp() {
        Account accountFrom = new Account("NL42INHO0000000002", (long) -100, (long) 100001, Account.AccountTypeEnum.CURRENT, true);
        Account accountTo =   new Account("NL42INHO0000000003", (long) 0, (long) 100001, Account.AccountTypeEnum.SAVING, true);
        Account account3 = new Account("NL42INHO0000000004", (long) 0, (long) 100002, Account.AccountTypeEnum.CURRENT, true);

        Transaction transaction = new Transaction(BigDecimal.valueOf(777), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
        Mockito.when(accountService.getAccountById("NL42INHO0000000002")).thenReturn(accountFrom);
        Mockito.when(accountService.getAccountById("NL42INHO0000000003")).thenReturn(accountTo);
        Mockito.when(accountService.getAccountById("NL42INHO0000000004")).thenReturn(account3);
        Mockito.when(transactionRepository.findById(transaction.getId()))
                .thenReturn(java.util.Optional.of(transaction));
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


}