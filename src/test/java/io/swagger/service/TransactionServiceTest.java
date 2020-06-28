package io.swagger.service;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.Transaction;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BalanceService balanceService;

    @BeforeEach
    public void setUp() {
        Transaction transaction = new Transaction(BigDecimal.valueOf(777), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER);

        Mockito.when(transactionRepository.findById(transaction.getId()))
                .thenReturn(java.util.Optional.of(transaction));
    }
}