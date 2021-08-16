package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;

import java.util.Optional;

public interface UserDao extends AbstractDao<User>{
    @Override
    default boolean add(User entity) {
        return false;
    }

    boolean signUp(String login, String email, String password) throws DaoException;
    Optional<User> signIn(String email, String password) throws DaoException;
    void changeUserStatusById(long id, User.UserStatus status) throws DaoException;
    boolean setPasswordById(long id, String password);
    void updateUserIconById(long id, String icon) throws DaoException;
    User activateUserByEmail(String email) throws DaoException;
}
