package edu.epam.webproject.validator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class EmailValidatorTest {
    @Test
    public void emailValidatorTestTrue(){
        String email = "alexdyachenka@gmail.com";
        assertTrue(UserValidator.validateEmail(email));
    }
    @Test
    public void emailValidatorTestFalse(){
        String email = "alexdyachenka@gmail.";
        assertFalse(UserValidator.validateEmail(email));
    }
}
