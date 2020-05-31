package io.swagger.dao;

import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    // Get all Transactions from an account
    List<Transaction> findByAccountFromOrderByDateDesc(String accountId);

    // Get all transactions to an account
    List<Transaction> findByAccountToOrderByDateDesc(String accountId);

    // Get all transactions from a account today
    @Query("from Transaction t where t.date >= ?1 and t.accountFrom = ?2")
    List<Transaction> findByAccountOnDate(OffsetDateTime from, String accountId);
}
