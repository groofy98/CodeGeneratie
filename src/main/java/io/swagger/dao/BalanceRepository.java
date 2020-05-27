package io.swagger.dao;

import io.swagger.model.Balance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends CrudRepository<Balance, Long> {
    // Get a balance by accountId
    Balance findOneByAccount(String account);
}
