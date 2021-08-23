package edu.epam.webproject.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class PasswordEncryptorTest {
    @Test
    public void checkPasswordTestTrue(){
        String password = "Sashka125";
        PasswordEncryptor passwordEncryptor = PasswordEncryptor.getInstance();
        String hashedPassword = passwordEncryptor.getHash(password);
        boolean actual = passwordEncryptor.checkHash(password, hashedPassword);
        assertTrue(actual);
    }
}
