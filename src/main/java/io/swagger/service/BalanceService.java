package io.swagger.service;

import io.swagger.dao.BalanceRepository;
import io.swagger.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    private BalanceService() {

    }

    // Add to balance off given account
    public void addAmount(String accountId, BigDecimal amount){
        Balance balance = balanceRepository.findOneByAccount(accountId);
        // Add to old amount and make it the new amount
        balance.setAmount(balance.getAmount().add(amount));
        // Save the new balance
        balanceRepository.save(balance);
        System.out.println(balance);
    }

    // Remove from balance off given account
    public void removeAmount(String accountId, BigDecimal amount){
        Balance balance = balanceRepository.findOneByAccount(accountId);
        // Subtract from old amount and make it the new amount
        balance.setAmount(balance.getAmount().subtract(amount));
        // Save the new balance
        balanceRepository.save(balance);
        System.out.println(balance);
        }

    public Balance getBalanceById(String id){
        List<Balance> accountList = (List<Balance>) balanceRepository.findAll();
        List<Balance> filteredList = accountList.stream().filter(Balance -> Balance.getAccount().contains(id)).collect(Collectors.toList());
        if (filteredList.size() > 0)
            return filteredList.get(0);
        else
            return null;
    }
}
