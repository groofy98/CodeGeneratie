package io.swagger.model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserTest {
    private User user;

    //make new user before each test
    @BeforeEach
    public void setup(){
        user = new User();
    }

    @Test
    @Before
    public void createUserShouldNotBeNull() {
        assertNotNull(user);
    }

}
