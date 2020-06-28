/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.19).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.AuthToken;
import io.swagger.model.User;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-28T11:28:32.663Z[GMT]")
@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "Deactivate existing user", nickname = "deactivateUser", notes = "Sets the active proprety to false in the database for the user with corresponding ID.", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "executed"),
        @ApiResponse(code = 400, message = "Invalid user id supplied"),
        @ApiResponse(code = 401, message = "failed to authenticate"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/{id}/deactivate",
        method = RequestMethod.PUT)
    ResponseEntity<Void> deactivateUser(@ApiParam(value = "id of user that needs to be updated",required=true) @PathVariable("id") String id
);


    @ApiOperation(value = "Get logged in user", nickname = "getLoggedInUser", notes = "Returns a JSON user object of the currently logged in user", response = User.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "user", response = User.class),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/loggedInUser",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<User> getLoggedInUser();


    @ApiOperation(value = "Logs in user", nickname = "loginUser", notes = "By passing a username and password in JSON, an authentication token is returned which can be used to log in.", response = AuthToken.class, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "authentication token", response = AuthToken.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/users/login",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<AuthToken> loginUser(@ApiParam(value = ""  )  @Valid @RequestBody Object body
);


    @ApiOperation(value = "Logs out current logged in user session", nickname = "logoutUser", notes = "Deletes authentication token for currently logged in user, effectively denying access.", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "/users/logout",
        method = RequestMethod.GET)
    ResponseEntity<Void> logoutUser();


    @ApiOperation(value = "Adds a new User", nickname = "registerUser", notes = "By passing a JSON object with the necessary user information a new user is added to the database. ID is generated automatically.", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "executed"),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/users/register",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> registerUser(@ApiParam(value = ""  )  @Valid @RequestBody User body
);


    @ApiOperation(value = "Get user by ID", nickname = "searchUser", notes = "Returns a user object which corresponds with the given ID.", response = User.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "user", response = User.class),
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<User> searchUser(@ApiParam(value = "user ID",required=true) @PathVariable("id") Integer id
);


    @ApiOperation(value = "Update existing User", nickname = "updateUser", notes = "By passing a JSON object with user info, you will update a user with the filled in information.", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "executed"),
        @ApiResponse(code = 400, message = "Invalid user supplied"),
        @ApiResponse(code = 401, message = "failed to authenticate"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/{id}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody User body
,@ApiParam(value = "id of user that needs to be updated",required=true) @PathVariable("id") String id
);

}
