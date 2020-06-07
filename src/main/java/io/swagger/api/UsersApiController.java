package io.swagger.api;

import io.swagger.model.AuthToken;
import io.swagger.model.User;
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
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-07T10:02:40.697Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deactivateUser(@ApiParam(value = "id of user that needs to be updated",required=true) @PathVariable("id") String id
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> getLoggedInUser() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"isCustomer\" : true,\n  \"firstname\" : \"John\",\n  \"password\" : \"John123!\",\n  \"isEmployee\" : false,\n  \"dateOfBirth\" : \"0007-10-17T00:00:00.000+0000\",\n  \"id\" : 5,\n  \"isActive\" : true,\n  \"email\" : \"JohnDoe@example.com\",\n  \"lastname\" : \"Doe\",\n  \"username\" : \"Johnny69\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<AuthToken> loginUser(@ApiParam(value = ""  )  @Valid @RequestBody Object body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<AuthToken>(objectMapper.readValue("{\n  \"authToken\" : \"b15938252a78\"\n}", AuthToken.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<AuthToken>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<AuthToken>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> logoutUser() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> registerUser(@ApiParam(value = ""  )  @Valid @RequestBody User body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> searchUser(@ApiParam(value = "user ID",required=true) @PathVariable("id") Integer id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"isCustomer\" : true,\n  \"firstname\" : \"John\",\n  \"password\" : \"John123!\",\n  \"isEmployee\" : false,\n  \"dateOfBirth\" : \"0007-10-17T00:00:00.000+0000\",\n  \"id\" : 5,\n  \"isActive\" : true,\n  \"email\" : \"JohnDoe@example.com\",\n  \"lastname\" : \"Doe\",\n  \"username\" : \"Johnny69\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<User>> searchusers(@NotNull @Min(1) @Max(100) @ApiParam(value = "The max number of results to return", required = true, allowableValues = "") @Valid @RequestParam(value = "count", required = true) Integer count
,@ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"isCustomer\" : true,\n  \"firstname\" : \"John\",\n  \"password\" : \"John123!\",\n  \"isEmployee\" : false,\n  \"dateOfBirth\" : \"0007-10-17T00:00:00.000+0000\",\n  \"id\" : 5,\n  \"isActive\" : true,\n  \"email\" : \"JohnDoe@example.com\",\n  \"lastname\" : \"Doe\",\n  \"username\" : \"Johnny69\"\n}, {\n  \"isCustomer\" : true,\n  \"firstname\" : \"John\",\n  \"password\" : \"John123!\",\n  \"isEmployee\" : false,\n  \"dateOfBirth\" : \"0007-10-17T00:00:00.000+0000\",\n  \"id\" : 5,\n  \"isActive\" : true,\n  \"email\" : \"JohnDoe@example.com\",\n  \"lastname\" : \"Doe\",\n  \"username\" : \"Johnny69\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody User body
,@ApiParam(value = "id of user that needs to be updated",required=true) @PathVariable("id") String id
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
