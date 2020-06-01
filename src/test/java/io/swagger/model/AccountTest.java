package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;


public class AccountTest {

    private Account account;

    @BeforeEach
    public void setup(){
        account = new Account();
    }

    @Test
    public void createTransactionShouldNotBeNull() {
        assertNotNull(account);
    }

}
