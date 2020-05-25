package io.swagger.service;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    // No Args constructor
    private TransactionService(){
    }

    // Get a single transaction by transactionId
    public Transaction getTransactionById(long id){
        return transactionRepository.findOne(id);
    }

    // Add a transaction to the database
    public void addTransaction(Transaction transaction){
        transactionRepository.save(transaction);
        System.out.println(transaction);
    }

    // Get all transactions
    public List<Transaction> getAllTransactions(){
        return (List<Transaction>) transactionRepository.findAll();
    }

    // Get all transactions to an account
    public List<Transaction> getTransactionsToAccountById(String accountId){
        return transactionRepository.findByAccountToOrderByDateDesc(accountId);
    }

    // Get all transactions from an account
    public List<Transaction> getTransactionsFromAccountById(String accountId){
        return transactionRepository.findByAccountFromOrderByDateDesc(accountId);
    }

    // Get all transactions with corresponding accountID
    public List<Transaction> getAllTransactionsById(String accountId){
        // Get all transactions from and to an account and merge the results
        List<Transaction> result = Stream.concat(getTransactionsFromAccountById(accountId).stream(), getTransactionsToAccountById(accountId).stream())
                .collect(Collectors.toList());
        // Sort the list by date
        Collections.sort(result, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction t1, Transaction t2) {
                return t2.getDate().compareTo(t1.getDate());
            }
        });
        return result;
    }
}
