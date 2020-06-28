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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public UserService(){}

    //check if currently logged in user corresponds with requested user
    public boolean checkAuthorization(Long userId) {
        Object AuthDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((UserDetail) AuthDetails).getUser(); //gets currently logged in user

        if (!userId.equals(user.getId()) && !((UserDetail) AuthDetails).getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            return false;
        return true;
    }

    //checks if currently logged in user is admin
    public boolean checkAuthorization() {
        Object AuthDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ((UserDetail) AuthDetails).getUser(); //gets currently logged in user

        if (((UserDetail) AuthDetails).getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            return true;
        return false;
    }

    //Get a single user by userId
    public User getUserById(Long id){
        try{
            User user = userRepository.findUserById(id);
            return user;
        } catch(Exception e){
            log.error("Error thrown when getting user:" + e);
            return null;
        }
    }

    //Register user in database
    public HttpStatus registerUser(User user){
        try {
            userRepository.save(user);
            System.out.println(user);
            return HttpStatus.OK;
        } catch(Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    //Deactivate a user by userID
    public HttpStatus deactivateUser(Long userID){
        try {
            User user = userRepository.findUserById(userID);
            user.setIsActive(false);
            userRepository.save(user);
            return HttpStatus.OK;
        } catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    //Updates the user
    public HttpStatus updateUser(Long userID, User userEdit){
        try {
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
        } catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
