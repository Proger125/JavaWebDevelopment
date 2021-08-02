package edu.epam.webproject.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class UserValidatorTest {
    @Test
    public void validateUserTestTrue(){
        String login = "admin_0-15";
        String email = "alexdyachenka@gmail.com";
        String password = "Sashka125";
        assertTrue(UserValidator.validateUser(login, email, password));
    }
}
