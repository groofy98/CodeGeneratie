/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Balance;
import io.swagger.model.Transaction;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-07T10:02:40.697Z[GMT]")
@Api(value = "account", description = "the account API")
public interface AccountApi {

    @ApiOperation(value = "Deactivate existing account", nickname = "deactivateAccount", notes = "Deacivate an account", authorizations = {
            @Authorization(value = "ApiKeyAuth")}, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "executed"),
            @ApiResponse(code = 400, message = "Invalid account id supplied"),
            @ApiResponse(code = 401, message = "failed to authenticate"),
            @ApiResponse(code = 404, message = "Account not found")})
    @RequestMapping(value = "/account/{accountId}/deactivate",
            method = RequestMethod.PUT)
    ResponseEntity<Void> deactivateAccount(@ApiParam(value = "id of account that needs to be updated", required = true) @PathVariable("accountId") String accountId
    );


    @ApiOperation(value = "Get the account details", nickname = "getAccount", notes = "By passing in the appropriate AccountID, you get the account details", response = Account.class, responseContainer = "List", tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "account details gotten", response = Account.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/account/{accountId}/details",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Account> getAccount(@ApiParam(value = "Pass in the ID of the account", required = true) @PathVariable("accountId") String accountId
    );


    @ApiOperation(value = "Get all the accounts belonging to one user", nickname = "getAccountsWithUserId", notes = "By passing in the appropriate userId, you get all accounts belonging to that user", response = Account.class, responseContainer = "List", tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "accounts gotten", response = Account.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/account/{userId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Account>> getAccountsWithUserId(@ApiParam(value = "Pass in the ID of the user", required = true) @PathVariable("userId") String userId
    );


    @ApiOperation(value = "Get the ammount of balance on your account", nickname = "getBalance", notes = "By passing in the appropriate balance, you can check how much balance you have", response = Balance.class, responseContainer = "List", tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Balance gotten from account", response = Balance.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/account/balance/{accountId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Balance> getBalance(@ApiParam(value = "Pass in the ID of the back account", required = true) @PathVariable("accountId") String accountId
    );


    @ApiOperation(value = "Get a list of transactions", nickname = "getTransaction", notes = "", response = Transaction.class, responseContainer = "List", tags = {"transactions",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = Transaction.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Invalid status value"),
            @ApiResponse(code = 404, message = "Account not found")})
    @RequestMapping(value = "/account/{accountId}/transactions",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getTransaction(@ApiParam(value = "Pass in the ID of the account of which to get the transactions from", required = true) @PathVariable("accountId") String accountId
            , @ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
            , @Min(1) @Max(100) @ApiParam(value = "The max number of results to return", allowableValues = "", defaultValue = "20") @Valid @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit
    );


    @ApiOperation(value = "Adds a new Account", nickname = "registerAccount", notes = "", tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "executed"),
            @ApiResponse(code = 400, message = "bad input parameter")})
    @RequestMapping(value = "/account/register",
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> registerAccount(@ApiParam(value = "") @Valid @RequestBody Account body
    );


    @ApiOperation(value = "Updated existing account", nickname = "updateAcount", notes = "By filling in this form, you update an account", authorizations = {
            @Authorization(value = "ApiKeyAuth")}, tags = {"accounts",})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid account supplied"),
            @ApiResponse(code = 404, message = "account not found")})
    @RequestMapping(value = "/account/{accountId}/details",
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateAcount(@ApiParam(value = "Updated account object", required = true) @Valid @RequestBody Account body
            , @ApiParam(value = "id of account that needs to be updated", required = true) @PathVariable("accountId") String accountId
    );

}
