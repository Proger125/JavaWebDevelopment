package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.ColumnName;
import edu.epam.webproject.model.dao.UserDao;
import edu.epam.webproject.util.PasswordEncryptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String SIGN_UP_SQL = "INSERT INTO users (login, password, email, role_id, status_id) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_USER_BY_EMAIL_SQL = "SELECT users.user_id, users.password, users.login, users.email, users.icon, roles.role_type, user_status.status_type " +
            "FROM users " +
            "JOIN roles ON users.role_id = roles.role_id JOIN user_status ON users.status_id = user_status.status_id WHERE users.email = ?";
    private UserDaoImpl(){

    }

    public static UserDaoImpl getInstance(){
        return instance;
    }

    @Override
    public Optional<User> signIn(String email, String password) throws DaoException {
        PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
        User user = null;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_SQL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                String hashPassword = resultSet.getString(ColumnName.PASSWORD);
                if (encryptor.checkHash(password, hashPassword)){
                    user = new User();
                    user.setId(resultSet.getInt(ColumnName.USER_ID));
                    user.setLogin(resultSet.getString(ColumnName.LOGIN));
                    user.setEmail(resultSet.getString(ColumnName.EMAIL));
                    user.setIcon(resultSet.getString(ColumnName.ICON));
                    user.setRole(User.Role.valueOf(resultSet.getString(ColumnName.ROLE_TYPE).toUpperCase()));
                    user.setStatus(User.UserStatus.valueOf(resultSet.getString(ColumnName.STATUS_TYPE).toUpperCase()));
                }
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.signIn request");
        }
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
    public boolean signUp(String login, String email, String password) throws DaoException {

        final int DUPLICATE_EMAIL_ERROR_CODE = 1062;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(SIGN_UP_SQL)){
            statement.setString(SignUpIndex.LOGIN, login);
            statement.setString(SignUpIndex.PASSWORD, password);
            statement.setString(SignUpIndex.EMAIL, email);
            statement.setInt(SignUpIndex.ROLE, User.Role.USER.getValue());
            statement.setInt(SignUpIndex.STATUS, User.UserStatus.IN_PROCESS.getValue());
            statement.execute();
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == DUPLICATE_EMAIL_ERROR_CODE){
                return false;
            }else{
                throw new DaoException("Unable to handle UserDao signUp request");
            }
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
