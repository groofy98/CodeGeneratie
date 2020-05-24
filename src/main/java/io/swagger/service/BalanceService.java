package io.swagger.service;

import io.swagger.dao.BalanceRepository;
import io.swagger.model.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalanceService {

    @Autowired
    private BalanceRepository balanceRepository;

    private BalanceService() {

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
