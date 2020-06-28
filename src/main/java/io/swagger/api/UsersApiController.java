package io.swagger.api;

import io.swagger.model.AuthToken;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.UserDetail;
import io.swagger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-06-28T11:28:32.663Z[GMT]")
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final UserService userService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, UserService userService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.userService = userService;
    }

    public ResponseEntity<Void> deactivateUser(@ApiParam(value = "id of user that needs to be updated",required=true) @PathVariable("id") Long id
) {
        if(userService.checkAuthorization()){
            String accept = request.getHeader("Accept");
            HttpStatus httpStatus = userService.deactivateUser(id);
            return new ResponseEntity<Void>(httpStatus);
        } else {
            log.error("Couldn't serialize response for content type application/json");
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<User> getLoggedInUser() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Object security = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = ((UserDetail) security).getUser();
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


    public ResponseEntity<Void> logoutUser() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> registerUser(@ApiParam(value="user object that needs to be added") @Valid @RequestBody User body
)   {
        if(userService.checkAuthorization()) {
            String accept = request.getHeader("Accept");
            userService.registerUser(body);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<User> searchUser(@ApiParam(value = "user ID",required=true) @PathVariable("id") Long id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            if(userService.checkAuthorization(id)){
                try {
                    return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "Updated user object", required = false) @Valid @RequestBody User body
            , @ApiParam(value = "id of user that needs to be updated", required = true) @PathVariable("id") Long id
    ) {
        if(userService.checkAuthorization()){
            String accept = request.getHeader("Accept");
            HttpStatus httpStatus = userService.updateUser(id, body);
            return new ResponseEntity<Void>(httpStatus);
        } else {
            return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
        }

    }
}
