package io.swagger.service;

import io.swagger.api.AccountApiController;
import io.swagger.dao.AccountRepository;
import io.swagger.dao.BalanceRepository;
import io.swagger.model.Account;
import io.swagger.model.Balance;
import io.swagger.model.User;
import io.swagger.model.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public boolean checkAuthorization(String accountId){
        Account account = getAccountById(accountId);

        Object AuthDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((UserDetail) AuthDetails).getUser();

        if (! account.getAccountHolder().equals(user.getId()) && !((UserDetail) AuthDetails).getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            return false;
        return true;
    }

    public Account getAccountById(String id) {
        try {
            Account account = accountRepository.findByaccountID(id);
            return account;
        } catch (Exception e) {
            log.error("Error whilst getting an account via IBAN:" + e);
            return null;
        }
    }

    public List<Account> getAccountsByUserId(long userId) {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((UserDetail) userDetails).getUser();
        user.getId();


        try {
            return accountRepository.findByaccountHolder(userId);
        } catch (Exception e) {
            log.error("Error whilst getting an account via a user:" + e);
            return null;
        }
    }

    public HttpStatus createAccount(Account givenAccount) {
        try {
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
            HttpStatus status = createBalance(newAccountId);

            //check if creating a balance went well
            if (status != HttpStatus.INTERNAL_SERVER_ERROR)
                return HttpStatus.CREATED;
            else
                return HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception e) {
            log.error("Error whilst creating a new");
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public HttpStatus createBalance(String accountId) {
        try {
            //check if the account exists
            if (getAccountById(accountId) != null) {
                //try creating the account, else return error
                balanceRepository.save(new Balance(accountId, new BigDecimal("0.00")));
                return HttpStatus.CREATED;
            } else {
                log.error("Error whilst creating balance, account does not exist");
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } catch (Exception e) {
            log.error("Error whilst creating balance" + e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public HttpStatus deactivateAccount(String accountId) {
        try {
            Account account = accountRepository.findByaccountID(accountId);
            account.setIsActive(false);
            accountRepository.save(account);
            return HttpStatus.OK;
        }
        catch (Exception e){
            log.error("Error whilst deactivating account" + e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public HttpStatus updateAccount(String accountId, Account newAccount) {
        try {
            Account account = accountRepository.findByaccountID(accountId);
            account.setAbsoluteLimit(newAccount.getAbsoluteLimit());
            account.setAccountType(newAccount.getAccountType());
            accountRepository.save(account);
            return HttpStatus.OK;
        }
        catch (Exception e){
            log.error("Error whilst deactivating account" +e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
