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
    private final DaoProvider daoProvider = DaoProvider.getInstance();
    private final UserDao userDao = daoProvider.getUserDao();

    @Override
    public Optional<User> signIn(String email, String password) throws ServiceException {
        if (!UserValidator.validateEmail(email) || !UserValidator.validatePassword(password)){
            return Optional.empty();
        }
        try {
            return userDao.signIn(email, password);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle signIn request at UserService", e);
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
            throw new ServiceException("Unable to handle signUp request at UserService", e);
        }
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findAllUsers request at UserService", e);
        }
    }

    @Override
    public User findUserById(long id) throws ServiceException {
        try {
            Optional<User> optionalUser = userDao.findById(id);
            if (optionalUser.isPresent()){
                return optionalUser.get();
            }else{
                throw new ServiceException("Can't find user by id: " + id);
            }
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findUserById request at UserService", e);
        }
    }

    @Override
    public boolean checkUserByEmail(String email) throws ServiceException {
        try{
            return userDao.checkUserByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle checkUserByEmail request at UserService", e);
        }
    }

    @Override
    public void updateUserIconById(long id, String icon) throws ServiceException {
        try{
            userDao.updateUserIconById(id, icon);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle updateUserIconById request at UserService");
        }
    }

    @Override
    public boolean updateUserPasswordByEmail(String email, String password, String repeatPassword) throws ServiceException {
        boolean result = false;
        try{
            if (password.equals(repeatPassword)){
                PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
                String hashedPassword = encryptor.getHash(password);
                userDao.setPasswordByEmail(email, hashedPassword);
                result = true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle updateUserPasswordByEmail", e);
        }
        return result;
    }

    @Override
    public User activateUserByEmail(String email) throws ServiceException {
        try{
            return userDao.activateUserByEmail(email);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle activateUserByEmail request at UserService", e);
        }
    }

    @Override
    public void changeUserStatusById(long id, User.UserStatus status) throws ServiceException {
        try{
            userDao.changeUserStatusById(id, status);
        }catch (DaoException e){
            throw new ServiceException("Unable to handle changeUserById request at UserService", e);
        }
    }

    @Override
    public User.UserStatus findUserStatusById(long id) throws ServiceException {
        try{
            return userDao.findUserStatusById(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to handle findUserStatusById request at UserService", e);
        }
    }
}
