package io.swagger.service;

import io.swagger.dao.AccountRepository;
import io.swagger.dao.UserRepository;
import io.swagger.model.Account;
import io.swagger.model.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    User user;
    @BeforeEach
    public void setUp() {
        userService = new UserService();
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse("2020/02/22");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user = new User("Test", "Tester", "Test@test.com", "test", "password", date, true, true, true);
    }

    @Test
    public void GetHttpErrorWhenUserIsNotRegistered(){
        HttpStatus status = userService.registerUser(user);
        assertSame(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void GetNullWhenNoUserIsReturned(){
        User user1 = userService.getUserById(user.getId());
        assertNull(user1);
    }

    @Test
    public void GetHttpErrorWhenUserIsNotUpdated(){
        user.setEmail(null);
        HttpStatus status = userService.updateUser(user.getId(), user);
        assertSame(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void GetHttpErrorWhenDeactivationIsUnsuccessful(){
        HttpStatus status = userService.deactivateUser(user.getId());
        assertSame(status, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
