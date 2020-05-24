package io.swagger.service;

import io.swagger.api.AccountApiController;
import io.swagger.dao.AccountRepository;
import io.swagger.dao.BalanceRepository;
import io.swagger.model.Account;
import io.swagger.model.Balance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BalanceRepository balanceRepository;

    private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

    private AccountService() {

    }

    public Account getAccountById(String id) {
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        List<Account> filteredList = accountList.stream().filter(account -> account.getAccountID().contains(id)).collect(Collectors.toList());
        if (filteredList.size() > 0)
            return filteredList.get(0);
        else
            return null;
    }

    public List<Account> getAccountsByUserId(long userId) {
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        List<Account> filteredList = accountList.stream().filter(account -> account.getAccountHolder().equals(userId)).collect(Collectors.toList());
        if (filteredList.size() > 0)
            return filteredList;
        else
            return null;
    }

    public HttpStatus createAccount(Account givenAccount){
        //make new random
        Random random = new Random();
        // format the new iban
        //this makes bijv. NL03INHO
        String newAccountId = "NL"+String.format("%02d",random.nextInt(99))+"INHO";

        //get all accounts
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        //get the highest accountID
        //String accountID = Collections.max(accountList, Comparator.comparing(a -> a.getAccountID().substring(10))).getAccountID();
        //get only the numbers from the account id and +1 this.
        //this will make bijv. NL03INHO0000000023
        newAccountId += String.format("%010d", accountRepository.count()+2);

        //create new account
        Account account = new Account(newAccountId, givenAccount.getAbsoluteLimit(), givenAccount.getAccountHolder(), givenAccount.getAccountType(), true);
        //add account to list
        accountList.add(account);
        //add accounts to DB
        accountList.forEach((accountRepository::save));

        createBalance(newAccountId);

        return HttpStatus.CREATED;
    }
    public void createBalance(String accountId){
        List<Balance> accountList = (List<Balance>) balanceRepository.findAll();
        accountList.add(new Balance(accountId, new BigDecimal("0.00")));
        accountList.forEach((balanceRepository::save));
    }

    public HttpStatus deactivateAccount(String accountId){
        List<Account> accountList = (List<Account>) accountRepository.findAll();
        Account deactivateAccount = accountList.stream().filter(account -> account.getAccountID().equals(accountId)).collect(Collectors.toList()).get(0);
        deactivateAccount.setIsActive(false);
        accountList.forEach((accountRepository::save));
        return HttpStatus.ACCEPTED;
    }
}
