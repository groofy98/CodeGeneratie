package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Balance;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.AccountService;
import io.swagger.service.BalanceService;
import io.swagger.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

//for testing on localhost
@CrossOrigin(origins = { "http://localhost"})
@Controller
public class AccountApiController implements AccountApi {

    private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

    private final TransactionService transactionService;

    private final AccountService accountService;

    private final BalanceService balanceService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountApiController(ObjectMapper objectMapper, HttpServletRequest request, TransactionService transactionService, AccountService accountService, BalanceService balanceService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.balanceService = balanceService;
    }

    public ResponseEntity<Void> deactivateAccount(@ApiParam(value = "id of account that needs to be updated", required = true) @PathVariable("accountId") String accountId) {
        System.out.println("tot hier komt ie: "+accountId);
        HttpStatus httpStatus = accountService.deactivateAccount(accountId);
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(httpStatus);
    }

    public ResponseEntity<Account> getAccount(@ApiParam(value = "Pass in the ID of the account", required = true) @PathVariable("accountId") String accountId) {
        String accept = request.getHeader("Accept");

        //get account by accountId
        Account account = accountService.getAccountById(accountId);
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Account>(account, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Account>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<Account>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> getAccountsWithUserId(@ApiParam(value = "Pass in the ID of the user", required = true) @PathVariable("userId") String userId) {
        String accept = request.getHeader("Accept");

        //get account by userId
        List<Account> accountList = accountService.getAccountsByUserId(Long.parseLong(userId));
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Balance> getBalance(@ApiParam(value = "Pass in the ID of the back account", required = true) @PathVariable("accountId") String accountId) {
        String accept = request.getHeader("Accept");

        //get account by accountId
        Balance balance = balanceService.getBalanceById(accountId);

        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Balance>(balance, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Balance>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Balance>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> getTransaction
            (@ApiParam(value = "Pass in the ID of the account of which to get the transactions from", required = true) @PathVariable("accountId") String
                     accountId
                    , @ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
                    , @Min(1) @Max(100) @ApiParam(value = "The max number of results to return", allowableValues = "", defaultValue = "20") @Valid @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit
            ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Transaction>>(this.transactionService.getAllTransactionsById(accountId), HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> registerAccount(@ApiParam(value = "") @Valid @RequestBody Account body) {
        HttpStatus httpStatus = accountService.createAccount(body);
        return new ResponseEntity<Void>(httpStatus);
    }

    public ResponseEntity<Void> updateAcount
            (@ApiParam(value = "Updated account object", required = true) @Valid @RequestBody Account body
                    , @ApiParam(value = "id of account that needs to be updated", required = true) @PathVariable("accountId") String
                     accountId
            ) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
