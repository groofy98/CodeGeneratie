package io.swagger.service;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    private TransactionService(){

    }

    public Transaction getTransactionById(long id){
        return transactionRepository.findOne(id);
    }

    public void addTransaction(Transaction transaction){
        transactionRepository.save(transaction);
        System.out.println(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return (List<Transaction>) transactionRepository.findAll();
    }
}
