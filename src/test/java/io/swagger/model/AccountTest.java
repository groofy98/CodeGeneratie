package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


public class AccountTest {

    private Account account;

    @BeforeEach
    public void setup(){
        account = new Account("NL47INHO0000000001", Account.AccountTypeEnum.SAVING, 10000L, true, -10L);
    }

    @Test
    public void createAccountShouldNotBeNull() {
        assertNotNull(account);
    }

    @Test
    void getAccountIDShouldNotReturnNull() {
        assertNotNull(account.getAccountID());
    }

    @Test
    void setId() {
        String id = "101";
        account.setAccountID(id);
        assertEquals(account.getAccountID(), id);
    }

    @Test
    void getAccountTypeShouldNotBeNull(){
        assertNotNull(account.getAccountID());
    }

    @Test
    void setAccountType() {
        Account.AccountTypeEnum accountType = Account.AccountTypeEnum.SAVING;
        account.setAccountType(accountType);
        assertEquals(account.getAccountType(), accountType);
    }

    @Test
    void getAccountHolderShouldNotBeNull(){
        assertNotNull(account.getAccountHolder());
    }

    @Test
    void setAccountHolder() {
        Long id = 101L;
        account.setAccountHolder(id);
        assertEquals(account.getAccountHolder(), id);
    }

    @Test
    void getAbsoluteLimitShouldNotBeNull(){
        assertNotNull(account.getAbsoluteLimit());
    }

    @Test
    void setAbsoluteLimit() {
        Long limit = -10L;
        account.setAbsoluteLimit(limit);
        assertEquals(account.getAbsoluteLimit(), limit);
    }

    @Test
    void isIsActiveShouldNotBeNull(){
        assertNotNull(account.isIsActive());
    }

    @Test
    void setIsActive() {
        Boolean isActive = true;
        account.setIsActive(isActive);
        assertEquals(account.isIsActive(), isActive);
    }

}
