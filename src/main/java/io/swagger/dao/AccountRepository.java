package io.swagger.dao;

import io.swagger.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByaccountID(String id);

    List<Account> findByaccountHolder(long userId);

//    @Query("INSERT INTO Account(accountID, accountType, accountHolder, absoluteLimit, isActive) VALUES (?1, ?2, ?3, ?4, ?5)")
//    void addAccountToDB(String accountID, Account.AccountTypeEnum accountType, Long accountHolder, Long absoluteLimit, Boolean isActive);
}

