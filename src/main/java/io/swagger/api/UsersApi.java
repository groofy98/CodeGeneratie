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
@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "Deactivate existing user", nickname = "deactivateUser", notes = "Deacivate a user", authorizations = {
        @Authorization(value = "ApiKeyAuth")    }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "executed"),
        @ApiResponse(code = 400, message = "Invalid user id supplied"),
        @ApiResponse(code = 401, message = "failed to authenticate"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/{id}/deactivate",
        method = RequestMethod.PUT)
    ResponseEntity<Void> deactivateUser(@ApiParam(value = "id of user that needs to be updated",required=true) @PathVariable("id") Long id
);


    @ApiOperation(value = "get logged in user", nickname = "getLoggedInUser", notes = "get the logged in user", response = User.class, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "user", response = User.class),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/loggedInUser",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<User> getLoggedInUser();


    @ApiOperation(value = "Logs in user", nickname = "loginUser", notes = "", response = AuthToken.class, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "authentication token", response = AuthToken.class),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/users/login",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<AuthToken> loginUser(@ApiParam(value = ""  )  @Valid @RequestBody User body
);


    @ApiOperation(value = "Logs out current logged in user session", nickname = "logoutUser", notes = "", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation") })
    @RequestMapping(value = "/users/logout",
        method = RequestMethod.GET)
    ResponseEntity<Void> logoutUser();


    @ApiOperation(value = "Adds a new User", nickname = "registerUser", notes = "", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "executed"),
        @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/users/register",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> registerUser(@ApiParam(value = ""  )  @Valid @RequestBody User body
);


    @ApiOperation(value = "Get user by ID", nickname = "searchUser", notes = "", response = User.class, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "user", response = User.class),
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<User> searchUser(@ApiParam(value = "user ID",required=true) @PathVariable("id") Long id
);

    @ApiOperation(value = "Get list of users", nickname = "searchusers", notes = "Calling this allows you to fetch the list of users in the system", response = User.class, responseContainer = "List", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "user data", response = User.class, responseContainer = "List") })
    @RequestMapping(value = "/users",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<User>> searchusers(@NotNull @Min(1) @Max(100) @ApiParam(value = "The max number of results to return", required = true, allowableValues = "") @Valid @RequestParam(value = "count", required = true) Integer count
,@ApiParam(value = "The number of items to skip before starting to collect the result set") @Valid @RequestParam(value = "offset", required = false) Integer offset
);


    @ApiOperation(value = "Update existing User", nickname = "updateUser", notes = "By filling in this form, you update a user", tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "executed"),
        @ApiResponse(code = 400, message = "Invalid user supplied"),
        @ApiResponse(code = 401, message = "failed to authenticate"),
        @ApiResponse(code = 404, message = "User not found") })
    @RequestMapping(value = "/users/{id}",
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "Updated user object" ,required=false )  @Valid @RequestBody User body
,@ApiParam(value = "id of user that needs to be updated",required=true) @PathVariable("id") Long id
);

}
