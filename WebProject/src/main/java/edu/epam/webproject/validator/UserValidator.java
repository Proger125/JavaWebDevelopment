package edu.epam.webproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.epam.webproject.validator.ValidatorRegExp.*;

public class UserValidator {

    private UserValidator(){}
    public static boolean validateUser(String login, String email, String password){
        return validateLogin(login) && validateEmail(email) && validatePassword(password);
    }
    public static boolean validateEmail(String email){
        Pattern pattern = Pattern.compile(EMAIL_REGEXP);
        Matcher matcher = pattern.matcher(email);
        boolean flag = false;
        if (matcher.matches()){
            flag = true;
        }
        return flag;
    }
    public static boolean validatePassword(String password){
        Pattern pattern = Pattern.compile(PASSWORD_REGEXP);
        Matcher matcher = pattern.matcher(password);
        boolean flag = false;
        if (matcher.matches()){
            flag = true;
        }
        return flag;
    }
    public static boolean validateLogin(String login){
        Pattern pattern = Pattern.compile(LOGIN_REGEXP);
        Matcher matcher = pattern.matcher(login);
        boolean flag = false;
        if (matcher.matches()){
            flag = true;
        }
        return flag;
    }
}
