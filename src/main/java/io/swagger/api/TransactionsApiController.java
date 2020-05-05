package io.swagger.api;

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

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T11:29:14.407Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createTransaction(@ApiParam(value = "Transaction object that needs to be added to the store" ,required=true )  @Valid @RequestBody Transaction body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> getTransaction(@NotNull @ApiParam(value = "Pass in the ID of the account of which to get the transactions from", required = true) @Valid @RequestParam(value = "accountId", required = true) String accountId
,@ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
,@Min(1) @Max(100) @ApiParam(value = "The max number of results to return", allowableValues = "") @Valid @RequestParam(value = "count", required = false) Integer count
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Transaction>>(objectMapper.readValue("[ {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"amount\" : 5000.5,\n  \"from\" : \"NL69INGB0123456789\",\n  \"id\" : 0,\n  \"to\" : \"NL02ABNA0123456789\",\n  \"userId\" : 6\n}, {\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"amount\" : 5000.5,\n  \"from\" : \"NL69INGB0123456789\",\n  \"id\" : 0,\n  \"to\" : \"NL02ABNA0123456789\",\n  \"userId\" : 6\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Transaction> getTransactionById(@ApiParam(value = "Id of transaction to return",required=true) @PathVariable("transactionId") Long transactionId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Transaction>(objectMapper.readValue("{\n  \"date\" : \"2000-01-23T04:56:07.000+00:00\",\n  \"amount\" : 5000.5,\n  \"from\" : \"NL69INGB0123456789\",\n  \"id\" : 0,\n  \"to\" : \"NL02ABNA0123456789\",\n  \"userId\" : 6\n}", Transaction.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Transaction>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

}
