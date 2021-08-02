package edu.epam.webproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String EMAIL_REGEXP = "[a-z].?([-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(com|net|org|pro|tel|travel)";
    private static final String PASSWORD_REGEXP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$";
    private static final String LOGIN_REGEXP = "^[a-z0-9_-]{3,16}$";
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
