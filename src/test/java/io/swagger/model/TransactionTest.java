package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void setup(){
        transaction = new Transaction(BigDecimal.valueOf(777), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);
    }

    @Test
    public void createTransactionShouldNotBeNull() {
        assertNotNull(transaction);
    }


    @Test
    void setId() {
        Long id = (long) 101;
        transaction.setId(id);
        assertEquals(transaction.getId(), id);
    }

    @Test
    void setDate() {
        OffsetDateTime date = OffsetDateTime.now();
        transaction.setDate(date);
        assertEquals(transaction.getDate(), date);
    }

    @Test
    void setAmount() {
        BigDecimal amount = BigDecimal.valueOf(500);
        transaction.setAmount(amount);
        assertEquals(transaction.getAmount(), BigDecimal.valueOf(500));
    }

    @Test
    void setAccountFrom() {
        String accountFrom = "NL42INHO000000666";
        transaction.setAccountFrom(accountFrom);
        assertEquals(transaction.getAccountFrom(),"NL42INHO000000666");
    }

    @Test
    void setAccountTo() {
        String accountTo = "NL42INHO000000777";
        transaction.setAccountTo(accountTo);
        assertEquals(transaction.getAccountTo(),"NL42INHO000000777");
    }

    @Test
    void setUserId() {
        Long userId = (long) 102;
        transaction.setUserId(userId);
        assertEquals(transaction.getUserId(), userId);
    }

    @Test
    void setTransactionType() {
        Transaction.TransactionTypeEnum type = Transaction.TransactionTypeEnum.WITHDRAW;
        transaction.setTransactionType(type);
        assertEquals(transaction.getTransactionType(),Transaction.TransactionTypeEnum.WITHDRAW);
    }
}