package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void setup(){
        transaction = new Transaction();
    }

    @Test
    public void createTransactionShouldNotBeNull() {
        assertNotNull(transaction);
    }


}