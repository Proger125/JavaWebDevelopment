package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.SignInData;
import edu.epam.webproject.entity.SignUpData;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.UserDao;
import edu.epam.webproject.util.PasswordEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String SIGN_UP = "INSERT INTO users (login, password, email, role_id, status_id) VALUES (?, ?, ?, ?, ?)";

    private UserDaoImpl(){

    }

    public static UserDaoImpl getInstance(){
        return instance;
    }

    @Override
    public Optional<User> signIn(SignInData data) {
        return Optional.empty();
    }

    @Override
    public boolean setPasswordById(long id, String password) {
        return false;
    }

    @Override
    public boolean banUserById(long id) {
        return false;
    }

    @Override
    public boolean unBanUserById(long id) {
        return false;
    }

    @Override
    public boolean signUp(SignUpData data) throws DaoException {
        PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
        final int DUPLICATE_EMAIL_ERROR_CODE = 1062;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SIGN_UP)){
            statement.setString(SignUpIndex.LOGIN, data.getLogin());
            statement.setString(SignUpIndex.PASSWORD, encryptor.getHash(data.getPassword()));
            statement.setString(SignUpIndex.EMAIL, data.getEmail());
            statement.setInt(SignUpIndex.ROLE, User.Role.USER.getValue());
            statement.setInt(SignUpIndex.STATUS, User.UserStatus.IN_PROGRESS.getValue());
            statement.execute();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == DUPLICATE_EMAIL_ERROR_CODE){
                return false;
            }else{
                throw new DaoException("Unable to handle UserDao signUp request");
            }
            //TODO Dublicate email if (dublicate email){return false} else {throw new DaoException()}
        }
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<User> updateById(long id, User entity) {
        return Optional.empty();
    }
    private static class SignUpIndex{
        private static final int LOGIN = 1;
        private static final int PASSWORD = 2;
        private static final int EMAIL = 3;
        private static final int ROLE = 4;
        private static final int STATUS = 5;
    }
}
