package io.swagger.service;

import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private AccountService() {

    }

    public Account getAccountById(String id) {
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        List<Account> filteredList = accountList.stream().filter(account -> account.getAccountID().contains(id)).collect(Collectors.toList());
        return filteredList.get(0);
    }

    public List<Account> getAccountsByUserId(long userId){
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        List<Account> filteredList = accountList.stream().filter(account -> account.getAccountHolder().equals(userId)).collect(Collectors.toList());
        return filteredList;
    }

//    public void addTransaction(Transaction transaction){
//        transactionRepository.save(transaction);
//        System.out.println(transaction);
//    }

    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }
}
