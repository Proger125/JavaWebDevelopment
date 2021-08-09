package edu.epam.webproject.model.service;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String INCORRECT_DATA_EXCEPTION_MESSAGE = "Incorrect user data";
    Optional<User> signIn(String email, String password) throws ServiceException;
    boolean signUp(String login, String email, String password) throws ServiceException;
    List<User> findAllUsers() throws ServiceException;
    User findUserById(long id) throws ServiceException;
    void updateUserIconById(long id, String icon) throws ServiceException;
    User activateUserByEmail(String email) throws ServiceException;
    Optional<User> updateUserById(long id, User user);
    void changeUserStatusById(long id, User.UserStatus status) throws ServiceException;
}
