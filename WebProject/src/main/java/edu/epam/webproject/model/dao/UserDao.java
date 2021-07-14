package edu.epam.webproject.model.dao;

import edu.epam.webproject.entity.SignInData;
import edu.epam.webproject.entity.SignUpData;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;

import java.util.Optional;

public interface UserDao extends AbstractDao<User>{
    @Override
    default boolean add(User entity) {
        return false;
    }

    boolean signUp(SignUpData data) throws DaoException;
    Optional<User> signIn(SignInData data);
    boolean setPasswordById(long id, String password);
    boolean banUserById(long id);
    boolean unBanUserById(long id);
}
