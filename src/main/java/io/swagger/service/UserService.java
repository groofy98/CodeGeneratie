package io.swagger.service;

import io.swagger.api.AccountApiController;
import io.swagger.api.UsersApiController;
import io.swagger.dao.AccountRepository;
import io.swagger.dao.BalanceRepository;
import io.swagger.dao.UserRepository;
import io.swagger.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private UserService() {

    }

    //TODO: make actual auth token; currently returns a hardcoded one (b15938252a78)
    public AuthToken loginUser(User userInfo) {
        AuthToken authToken = new AuthToken();
        String enteredEmail = userInfo.getEmail();
        String enteredPassword = userInfo.getPassword();
        User user = userRepository.findUserByEmail(enteredEmail);

        if (user.getEmail().toLowerCase().equals(enteredEmail.toLowerCase()) && user.getPassword().equals(enteredPassword)){
            authToken.setAuthToken("b15938252a78");
        } else {
            System.err.println("Login failed"); //For testing - remove later
            authToken.setAuthToken(null);
        }
        return authToken;
    }

    public HttpStatus createUser(User givenUser){
        return HttpStatus.NOT_IMPLEMENTED;
    }

    //Get a single user by userId
    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

    //Register user in database
    public void registerUser(User user){
        userRepository.save(user);
        System.out.println(user);
    }

    //Deactivate a user
    public HttpStatus deactivateUser(Long userID){
        User user = userRepository.findUserById(userID);
        user.setIsActive(false);
        userRepository.save(user);
        return HttpStatus.OK;
    }

    //Updates the user
    public HttpStatus updateUser(Long userID, User userEdit){
        //find current data of user
        User user = getUserById(userID);

        //check which fields are filled in in userEdit
        if (userEdit.getFirstname() != null) {
            user.setFirstname(userEdit.getFirstname());
        }
        if (userEdit.getUsername() != null) {
            user.setUsername(userEdit.getUsername());
        }
        if (userEdit.getLastname() != null) {
            user.setLastname(userEdit.getLastname());
        }
        if (userEdit.getDateOfBirth() != null) {
            user.setDateOfBirth(userEdit.getDateOfBirth());
        }
        if (userEdit.getPassword() != null) {
            user.setDateOfBirth(userEdit.getDateOfBirth());
        }
        if (userEdit.getEmail() != null) {
            user.setEmail(userEdit.getEmail());
        }
        if (userEdit.getPassword() != null) {
            user.setPassword(userEdit.getPassword());
        }
        if (userEdit.isIsEmployee() != null) {
            user.setIsEmployee(userEdit.isIsEmployee());
        }
        if (userEdit.isIsCustomer() != null) {
            user.setIsCustomer(userEdit.isIsCustomer());
        }

        userRepository.save(user);
        return HttpStatus.OK;
    }
}
