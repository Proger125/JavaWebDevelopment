package edu.epam.webproject.model.service.impl;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.dao.DaoProvider;
import edu.epam.webproject.model.dao.UserDao;
import edu.epam.webproject.model.service.UserService;
import edu.epam.webproject.util.PasswordEncryptor;
import edu.epam.webproject.validator.UserValidator;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final UserDao userDao = daoProvider.getUserDao();

    @Override
    public Optional<User> signIn(String email, String password) throws ServiceException {
        if (!UserValidator.validateEmail(email) || !UserValidator.validatePassword(password)){
            return Optional.empty();
        }
        try {
            return userDao.signIn(email, password);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle signIn request at User Service", e);
        }
    }

    @Override
    public boolean signUp(String login, String email, String password) throws ServiceException {
        if (!UserValidator.validateUser(login, email, password)){
            throw new ServiceException("Incorrect user data");
        }
        try {
            PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
            String hashedPassword = encryptor.getHash(password);
            return userDao.signUp(login, email, hashedPassword);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle signUp request at User Service", e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findAllUsers request at User Service");
        }
    }

    @Override
    public Optional<User> updateUserById(long id, User user) {
        return Optional.empty();
    }
}
