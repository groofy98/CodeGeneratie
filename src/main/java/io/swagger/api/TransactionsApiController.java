package io.swagger.api;

import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-28T09:02:52.594Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final TransactionService transactionService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request, TransactionService transactionService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.transactionService = transactionService;
    }

    public ResponseEntity<Void> createTransaction(@ApiParam(value = "Transaction object that needs to be added" ,required=true )  @Valid @RequestBody Transaction body
) {
        // Check if request is sent with the correct headers
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            // Check if request is authorized
            if (transactionService.checkAuthorized(body.getAccountFrom())){
                // Try to add transaction to the db
                try {
                    transactionService.newTransaction(body);
                    return new ResponseEntity<Void>(HttpStatus.CREATED);
                }
                // Return error when something goes wrong
                catch (ApiException e) {
                    throw new ResponseStatusException(
                            HttpStatus.valueOf(e.getCode()), e.getMessage());
                }
            }
            else return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }

    // Get a single transaction by transaction id
    public ResponseEntity<Transaction> getTransactionById(@ApiParam(value = "Id of transaction to return",required=true) @PathVariable("transactionId") Long transactionId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Transaction transaction = transactionService.getTransactionById(transactionId);
                if (transactionService.checkAuthorized(transaction.getAccountFrom()) || transactionService.checkAuthorized(transaction.getAccountTo()))
                    return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
                else
                    return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
            } catch (ApiException e) {
                // If user is authorized but something else went wrong getting the transaction return the error
                throw new ResponseStatusException(
                    HttpStatus.valueOf(e.getCode()), e.getMessage());
                }

            catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Transaction>(HttpStatus.BAD_REQUEST);
    }

}
