package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;

import java.util.Optional;

/**
 * The interface provides methods to interact with {@link User} data from database
 */
public interface UserDao extends AbstractDao<User>{
    /**
     * Adds new {@link} with {@link User.UserStatus#IN_PROCESS} status and with {@link User.Role#USER} role to database with specified parameters
     * @param login - new user login
     * @param email - new user email
     * @param password - new user password
     * @return true if user was added to database, false if database contains user with same email
     * @throws DaoException when problems with database connection occurs
     */
    boolean signUp(String login, String email, String password) throws DaoException;

    /**
     * Check if database contains {@link User} with specified parameters
     * @param email - user email
     * @param password - user password
     * @return {@link Optional<User>} which contains {@link User} if there is such user in database,
     * {@link null} otherwise
     * @throws DaoException when problems with database connection occurs
     */
    Optional<User> signIn(String email, String password) throws DaoException;

    /**
     * Changes {@link User} status
     * @param id - users id
     * @param status - new user status
     * @throws DaoException when problems with database connection occurs
     */
    void changeUserStatusById(long id, User.UserStatus status) throws DaoException;

    /**
     * Sets new password to {@link User}
     * @param email - users email
     * @param password - new password
     */
    void setPasswordByEmail(String email, String password) throws DaoException;

    /**
     * Update {@link User} icon
     * @param id - users id
     * @param icon - new users icon
     * @throws DaoException when problems with database connection occurs
     */
    void updateUserIconById(long id, String icon) throws DaoException;

    /**
     * Activates {@link User} account by changing its {@link User.UserStatus} to {@link User.UserStatus#APPROVED}
     * @param email - users email
     * @return - {@link User} with all data
     * @throws DaoException when problems with database connection occurs
     */
    User activateUserByEmail(String email) throws DaoException;

    /**
     * Finds {@link User} by id and returns its status
     * @param id - users id
     * @return {@link User.UserStatus}
     * @throws DaoException when problems with database connection occurs
     */
    User.UserStatus findUserStatusById(long id) throws DaoException;

    /**
     * Checks if there is a {@link User} with this email in the database
     * @param email - users email
     * @return true if there, false otherwise
     * @throws DaoException when problems with database connection occurs
     */
    boolean checkUserByEmail(String email) throws DaoException;
}
