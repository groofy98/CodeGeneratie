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

    AccountService() {

    }

    public Account getAccountById(String id) {
        Account mockAccount = new Account("NL47INHO0000000069", (long) 0, (long) 123456789, Account.AccountTypeEnum.SAVING, true);
        try {
            Account account = accountRepository.findByaccountID(id);
            return account;
        }
        catch (Exception e){
            return mockAccount;
        }
    }

    public List<Account> getAccountsByUserId(long userId) {
        return accountRepository.findByaccountHolder(userId);
    }

    public HttpStatus createAccount(Account givenAccount) {
        //make new random
        Random random = new Random();

        // format the new iban
        //this makes bijv. NL03INHO
        String newAccountId = "NL" + String.format("%02d", random.nextInt(99)) + "INHO";

        //this will make bijv. NL03INHO0000000023
        newAccountId += String.format("%010d", accountRepository.count() + 2);

        //create new account
        Account account = new Account(newAccountId, givenAccount.getAbsoluteLimit(), givenAccount.getAccountHolder(), givenAccount.getAccountType(), true);
        //add accounts to DB
        accountRepository.save(account);

        //create balance for object
        createBalance(newAccountId);

        return HttpStatus.CREATED;
    }

    public void createBalance(String accountId) {
        balanceRepository.save(new Balance(accountId, new BigDecimal("0.00")));
    }

    public HttpStatus deactivateAccount(String accountId) {
        Account account = accountRepository.findByaccountID(accountId);
        account.setIsActive(false);
        accountRepository.save(account);
        return HttpStatus.OK;
    }

    public HttpStatus updateAccount(String accountId, Account newAccount) {
        Account account = accountRepository.findByaccountID(accountId);
        account.setAbsoluteLimit(newAccount.getAbsoluteLimit());
        account.setAccountType(newAccount.getAccountType());
        accountRepository.save(account);
        return HttpStatus.OK;
    }
}
