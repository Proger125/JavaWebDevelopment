package edu.epam.webproject.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Class provides methods to encrypt password
 */
public class PasswordEncryptor {

    /**
     * Instance of singleton
     */
    private static final PasswordEncryptor instance = new PasswordEncryptor();

    /**
     * Private constructor without parameters
     */
    private PasswordEncryptor(){ }

    /**
     * Returns instance of {@link PasswordEncryptor}
     * @return {@link PasswordEncryptor}
     */
    public static PasswordEncryptor getInstance(){
        return instance;
    }

    /**
     * Checks if password equals hashed password by hash
     * @param password - current password
     * @param hash - hashed password
     * @return true if password are the same, false otherwise
     */
    public boolean checkHash(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }

    /**
     * Returns hash of password
     * @param password - current password
     * @return hash of password
     */
    public String getHash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
