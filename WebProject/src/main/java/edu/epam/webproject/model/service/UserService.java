package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Interface provides methods to interact with {@link User} data from Dao layer
 */
public interface UserService {

    String INCORRECT_DATA_EXCEPTION_MESSAGE = "Incorrect user data";
    /**
     *  Signs in {@link User} by email and password
     *
     * @param email - user email
     * @param password - user password
     * @return {@link Optional<User>}
     * @throws ServiceException when problems with DAO occurs
     */
    Optional<User> signIn(String email, String password) throws ServiceException;

    /**
     * Signs up {@link User} by login, email and password
     *
     * @param login - new user login
     * @param email - new user email
     * @param password - new user password
     * @return true if it's possible to create new account, false otherwise
     * @throws ServiceException when problems with DAO occurs
     */
    boolean signUp(String login, String email, String password) throws ServiceException;

    /**
     * Finds all {@link User}
     *
     * @return {@link List<User>}
     * @throws ServiceException when problems with DAO occurs
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Finds {@link User} by id
     *
     * @param id - user id
     * @return {@link User}
     * @throws ServiceException when problems with DAO occurs
     */
    User findUserById(long id) throws ServiceException;

    /**
     * Checks is there {@link User} with such email in database
     *
     * @param email - user email
     * @return true if database contains {@link User} with such email, false otherwise
     * @throws ServiceException when problems with DAO occurs
     */
    boolean checkUserByEmail(String email) throws ServiceException;

    /**
     * Updates {@link User} icon by id
     *
     * @param id - user id
     * @param icon - new user icon
     * @throws ServiceException when problems with DAO occurs
     */
    void updateUserIconById(long id, String icon) throws ServiceException;

    /**
     * Updates {@link User} password by email
     *
     * @param email - user email
     * @param password - new user password
     * @param repeatPassword - repetition of password
     * @return true if password equals repeatPassword and false otherwise
     * @throws ServiceException when problems with DAO occurs
     */
    boolean updateUserPasswordByEmail(String email, String password, String repeatPassword) throws ServiceException;

    /**
     * Activates {@link User} by email.
     *
     * @param email - user email
     * @return {@link User}
     * @throws ServiceException when problems with DAO occurs
     */
    User activateUserByEmail(String email) throws ServiceException;

    /**
     * Changes {@link User} status by id
     *
     * @param id - user id
     * @param status - new user status
     * @throws ServiceException when problems with DAO occurs
     */
    void changeUserStatusById(long id, User.UserStatus status) throws ServiceException;

    /**
     * Finds {@link User} status by id
     *
     * @param id - user id
     * @return {@link User.UserStatus}
     * @throws ServiceException when problems with DAO occurs
     */
    User.UserStatus findUserStatusById(long id) throws ServiceException;
}
