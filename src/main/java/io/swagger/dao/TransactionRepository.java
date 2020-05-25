package io.swagger.dao;

import io.swagger.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    // Get all Transactions from an account
    List<Transaction> findByAccountFromOrderByDateDesc(String accountId);

    // Get all transactions to an account
    List<Transaction> findByAccountToOrderByDateDesc(String accountId);
}
