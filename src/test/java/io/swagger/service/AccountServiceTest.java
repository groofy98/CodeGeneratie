package io.swagger.service;

import io.swagger.dao.AccountRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @MockBean
    AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        accountService = new AccountService();
}

    @Test
    public void GetNullWhenAccountDoesNotExist(){
        Account account = accountService.getAccountById("invalid_IBAN");
        assertNull(account);
        System.out.println(account);
    }

    @Test
    public void GetNullWhenUserDoesNotExist(){
        List<Account> accountList = accountService.getAccountsByUserId(0);
        assertNull(accountList);
        System.out.println(accountList);
    }

    @Test
    public void GetHttpErrorWhenAccountCannotBeMade(){
        Account account = new Account("NL42INHO000000002", (long) 0, (long) 0, Account.AccountTypeEnum.SAVING, true);
        HttpStatus status = accountService.createAccount(account);
        assertSame(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void GetHttpErrorWhenBalanceCannotBeMade(){
        HttpStatus status = accountService.createBalance("hoihoi");
        assertSame(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void GetHttpErrorWhenDeactivateIsUnsuccessful(){
        HttpStatus status = accountService.deactivateAccount("hoihoi");
        assertSame(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void GetHttpErrorWhenUpdateIsUnsuccessful(){
        Account account = new Account("NL42INHO000000002", (long) 0, (long) 0, Account.AccountTypeEnum.SAVING, true);

        HttpStatus status = accountService.updateAccount("hoihoi", account);
        assertSame(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @Test
//    void multiplicationOfZeroIntegersShouldReturnZero() {
//        // assert statements
//        assertEquals(0, 10*0, "10 x 0 must be 0");
//        assertEquals(0, 10*0, "0 x 10 must be 0");
//        assertEquals(0, 10*0, "0 x 0 must be 0");
//    }

}