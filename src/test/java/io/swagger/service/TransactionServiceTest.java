package io.swagger.service;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    @MockBean
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    Transaction transaction;
    Account account1;
    Account account2;

    @Before
    public void setUp() {
        transaction = new Transaction(BigDecimal.valueOf(100), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.DEPOSIT);
        account1 = new Account("NL42INHO0000000002", (long) -100, (long) 123456789, Account.AccountTypeEnum.CURRENT, true);
        account2 = new Account("NL42INHO0000000003", (long) -0, (long) 123456789, Account.AccountTypeEnum.SAVING, true);

        Mockito.when(transactionRepository.findByAccountFromOrderByDateDesc("NL42INHO0000000002"))
                .thenReturn(Arrays.asList(new Transaction(BigDecimal.valueOf(777), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.DEPOSIT)));
        Mockito.when(transactionRepository.findByAccountToOrderByDateDesc("NL42INHO0000000002"))
                .thenReturn(Arrays.asList(new Transaction(BigDecimal.valueOf(97777), "NL42INHO0000000003", "NL42INHO0000000002", (long) 5, Transaction.TransactionTypeEnum.DEPOSIT)));

        Mockito.when(accountService.getAccountById(transaction.getAccountFrom())).thenReturn(account1);
        Mockito.when(accountService.getAccountById(transaction.getAccountTo())).thenReturn(account2);
    }

    @Test
    public void fromSameUserToSavingShouldReturnDeposit(){
        Transaction.TransactionTypeEnum transactionType = transactionService.getTransactionType(transaction.getAccountFrom(), transaction.getAccountTo());
        assertThat(transactionType == Transaction.TransactionTypeEnum.DEPOSIT);
    }

}