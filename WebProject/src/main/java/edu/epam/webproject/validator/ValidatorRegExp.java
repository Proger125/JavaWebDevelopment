package edu.epam.webproject.validator;

public class ValidatorRegExp {
    public static final String EMAIL_REGEXP = "[a-z].?([-a-z0-9!#$%&'*+/=?^_`{|}~]+(\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*)*@([a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(com|net|org|pro|tel|travel|ru)";
    public static final String PASSWORD_REGEXP = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$";
    public static final String LOGIN_REGEXP = "^[a-z0-9_-]{3,16}$";
    private ValidatorRegExp(){}
}
