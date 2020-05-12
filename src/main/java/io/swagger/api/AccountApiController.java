package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Balance;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.threeten.bp.OffsetDateTime;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-06T14:07:06.341Z[GMT]")
@Controller
public class AccountApiController implements AccountApi {

    private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deactivateAccount(@ApiParam(value = "id of account that needs to be updated",required=true) @PathVariable("accountId") String accountId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> getAccount(@ApiParam(value = "Pass in the ID of the account",required=true) @PathVariable("accountId") String accountId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"AccountID\" : \"ingb-219009315\",\n  \"IsActive\" : true,\n  \"AccountType\" : \"Saving\",\n  \"AccountHolder\" : 1234567890\n}, {\n  \"AccountID\" : \"ingb-219009315\",\n  \"IsActive\" : true,\n  \"AccountType\" : \"Saving\",\n  \"AccountHolder\" : 1234567890\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Account>> getAccountsWithUserId(@ApiParam(value = "Pass in the ID of the user",required=true) @PathVariable("userId") String userId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Account>>(objectMapper.readValue("[ {\n  \"AccountID\" : \"ingb-219009315\",\n  \"IsActive\" : true,\n  \"AccountType\" : \"Saving\",\n  \"AccountHolder\" : 1234567890\n}, {\n  \"AccountID\" : \"ingb-219009315\",\n  \"IsActive\" : true,\n  \"AccountType\" : \"Saving\",\n  \"AccountHolder\" : 1234567890\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Account>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Account>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Balance>> getBalance(@ApiParam(value = "Pass in the ID of the back account",required=true) @PathVariable("accountId") String accountId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Balance>>(objectMapper.readValue("[ {\n  \"amount\" : 1732.79,\n  \"account\" : \"12897865446567\"\n}, {\n  \"amount\" : 1732.79,\n  \"account\" : \"12897865446567\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Balance>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Balance>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> getTransaction(@ApiParam(value = "Pass in the ID of the account of which to get the transactions from",required=true) @PathVariable("accountId") String accountId
,@ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
,@Min(1) @Max(100) @ApiParam(value = "The max number of results to return", allowableValues = "", defaultValue = "20") @Valid @RequestParam(value = "limit", required = false, defaultValue="20") Integer limit
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Transaction transaction = new Transaction(OffsetDateTime.now(), (double)77777, "sjors", "patrick", (long)5, Transaction.TransactionTypeEnum.TRANSFER);
                Transaction transaction1 = new Transaction(OffsetDateTime.now(), (double)77777, "Yolo", "Swek", (long)5, Transaction.TransactionTypeEnum.TRANSFER);
                List<Transaction> transactions = new ArrayList<Transaction>();
                transactions.add(transaction);
                transactions.add(transaction1);
                return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> registerAccount(@ApiParam(value = ""  )  @Valid @RequestBody Account body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateAcount(@ApiParam(value = "Updated account object" ,required=true )  @Valid @RequestBody Account body
,@ApiParam(value = "id of account that needs to be updated",required=true) @PathVariable("accountId") String accountId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
