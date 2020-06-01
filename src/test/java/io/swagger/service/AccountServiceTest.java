package io.swagger.service;

import io.swagger.dao.AccountRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    Account account1;
    Account account2;

    @BeforeEach
    public void setUp() {
        accountService = new AccountService();
        account1 = new Account("NL42INHO0000000002", (long) -100, (long) 123456789, Account.AccountTypeEnum.CURRENT, true);
//        account2 = new Account("NL42INHO0000000003", (long) -0, (long) 123456789, Account.AccountTypeEnum.SAVING, true);

//        Mockito.when(accountRepository.findByaccountID("NL42INHO0000000002"))
//                .thenReturn(account1);
//        Mockito.when(transactionRepository.findByAccountToOrderByDateDesc("NL42INHO0000000002"))
//                .thenReturn(Arrays.asList(new Transaction(BigDecimal.valueOf(97777), "NL42INHO0000000003", "NL42INHO0000000002", (long) 5, Transaction.TransactionTypeEnum.DEPOSIT)));
    }

    @Test
    public void GetNullWhenAccountDoesNotExist(){
        Account account = accountService.getAccountById("NL42INHO0000000004");
        System.out.println(account);
    }

//    @Test
//    void multiplicationOfZeroIntegersShouldReturnZero() {
//        // assert statements
//        assertEquals(0, 10*0, "10 x 0 must be 0");
//        assertEquals(0, 10*0, "0 x 10 must be 0");
//        assertEquals(0, 10*0, "0 x 0 must be 0");
//    }

}