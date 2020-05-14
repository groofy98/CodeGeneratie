package io.swagger.service;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    private TransactionService(){

    }

    public List<Transaction> getAllTransactions(){
        return (List<Transaction>) transactionRepository.findAll();
    }
}
