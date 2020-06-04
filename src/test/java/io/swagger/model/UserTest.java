package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {
    private User user;

    //make new user before each test
    @BeforeEach
    public void setup(){
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse("2020/02/22");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user = new User("John", "Doe", "example@example.com", "Johnny", "password", date, true, true, true);
    }

    @Test
    public void createUserShouldNotBeNull() {
        assertNotNull(user);
    }

    @Test
    void getIdShouldNotReturnNull() {
        user.setId(101L);
        assertNotNull(user.getId());
    }
    @Test
    void setId() {
        Long id = 101L;
        user.setId(id);
        assertEquals(user.getId(), id);
    }

    @Test
    public void getFirstnameShouldNotReturnNull(){
        assertNotNull(user.getFirstname());
    }

    @Test
    public void setFirstName(){
        String name = "firstname";
        user.setFirstname(name);
        assertEquals(user.getFirstname(), name);
    }

    @Test
    void getLastnameShouldNotReturnNull() {
        assertNotNull(user.getLastname());
    }
    @Test
    void setLastname() {
        String name = "lastname";
        user.setLastname(name);
        assertEquals(user.getLastname(), name);
    }

    @Test
    void getEmailShouldNotReturnNull() {
        assertNotNull(user.getEmail());
    }
    @Test
    void setEmail() {
        String email = "email";
        user.setEmail(email);
        assertEquals(user.getEmail(), email);
    }

    @Test
    void getUsernameShouldNotReturnNull() {
        assertNotNull(user.getUsername());
    }
    @Test
    void setUsername() {
        String username = "username";
        user.setUsername(username);
        assertEquals(user.getUsername(), username);
    }

    @Test
    void getPasswordShouldNotReturnNull() {
        assertNotNull(user.getPassword());
    }
    @Test
    void setPassword() {
        String pwd = "123";
        user.setPassword(pwd);
        assertEquals(user.getPassword(), pwd);
    }

    @Test
    void getDateOfBirthShouldNotReturnNull() {
        assertNotNull(user.getDateOfBirth());
    }
    @Test
    void setDateOfBirth() {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy/MM/dd").parse("2222/02/22");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setDateOfBirth(date);
        assertEquals(user.getDateOfBirth(), date);
    }


    @Test
    void isIsEmployeeShouldNotReturnNull() {
        assertNotNull(user.isIsEmployee());
    }
    @Test
    void setIsEmployee() {
        Boolean bool = false;
        user.setIsEmployee(bool);
        assertEquals(user.isIsEmployee(), bool);
    }

    @Test
    void isIsCustomerShouldNotReturnNull() {
        assertNotNull(user.isIsCustomer());
    }
    @Test
    void setIsCustomer() {
        Boolean bool = false;
        user.setIsCustomer(bool);
        assertEquals(user.isIsCustomer(), bool);
    }

    @Test
    void isIsActiveShouldNotReturnNull() {
        assertNotNull(user.isIsActive());
    }
    @Test
    void setIsActive() {
        Boolean bool = false;
        user.setIsActive(bool);
        assertEquals(user.isIsActive(), false);
    }

    @Test
    void testEqualsReturnsBoolean() {
        Boolean bool = user.equals(user);
        assertTrue(bool instanceof Boolean);
    }
}
