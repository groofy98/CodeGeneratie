package io.swagger.service;

import io.swagger.api.AccountApiController;
import io.swagger.api.UsersApiController;
import io.swagger.dao.AccountRepository;
import io.swagger.dao.BalanceRepository;
import io.swagger.dao.UserRepository;
import io.swagger.model.Account;
import io.swagger.model.AuthToken;
import io.swagger.model.Balance;
import io.swagger.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    //TODO: actual authentication, currently accepts any credentials and returns hardcoded auth token
    public AuthToken loginUser(User givenUser) {
        AuthToken authToken = new AuthToken();
        authToken.setAuthToken("b15938252a78");
        return authToken;
    }

    public HttpStatus createUser(User givenUser){
        return HttpStatus.NOT_IMPLEMENTED;
    }
}
