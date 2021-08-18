package edu.epam.webproject.model.dao.impl;

import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.ColumnName;
import edu.epam.webproject.model.dao.UserDao;
import edu.epam.webproject.util.PasswordEncryptor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String SIGN_UP_SQL = "INSERT INTO users (login, password, email, role_id, status_id) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_USER_BY_EMAIL_SQL = "SELECT users.user_id, users.password, users.login, users.email, users.icon, roles.role_type, user_status.status_type " +
            "FROM users " +
            "JOIN roles ON users.role_id = roles.role_id JOIN user_status ON users.status_id = user_status.status_id WHERE users.email = ?";
    private static final String FIND_USER_BY_ID_SQL = "SELECT users.user_id, users.password, users.login, users.email, users.icon, roles.role_type, user_status.status_type " +
            "FROM users " +
            "JOIN roles ON users.role_id = roles.role_id JOIN user_status ON users.status_id = user_status.status_id WHERE users.user_id = ?";
    private static final String FIND_ALL_USERS_SQL = "SELECT users.user_id, users.login, users.email, users.icon, roles.role_type, user_status.status_type " +
            "FROM users " +
            "JOIN roles ON users.role_id = roles.role_id JOIN user_status ON users.status_id = user_status.status_id WHERE users.role_id <> 1";
    private static final String CHANGE_USER_STATUS_BY_ID_SQL = "UPDATE users SET status_id = ? WHERE user_id = ?";
    private static final String CHANGE_USER_STATUS_BY_EMAIL_SQL = "UPDATE users SET status_id = ? WHERE email = ?";
    private static final String UPDATE_USER_ICON_BY_ID_SQL = "UPDATE users SET icon = ? WHERE user_id = ?";
    private static final String FIND_USER_STATUS_BY_ID = "SELECT user_status.status_type FROM users JOIN user_status ON users.status_id = user_status.status_id WHERE user_id = ?";
    private static final String CHECK_USER_BY_EMAIL = "SELECT user_id FROM users WHERE email = ?";
    private static final String UPDATE_USER_PASSWORD_BY_EMAIL = "UPDATE users SET password = ? WHERE email = ?";
    private UserDaoImpl(){
    }

    public static UserDaoImpl getInstance(){
        return instance;
    }

    /**
     * Check if database contains user with specified parameters
     * @param email - user email
     * @param password - user password
     * @return {@link Optional<User>} which contains {@link User} if there is such user in database,
     * {@link null} otherwise
     * @throws DaoException when problems with database connection occurs
     */
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
                    user = createUser(resultSet);
                }
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.signIn request", e);
        }
    }

    /**
     * Changes {@link User} status
     * @param id - users id
     * @param status - new user status
     * @throws DaoException when problems with database connection occurs
     */
    @Override
    public void changeUserStatusById(long id, User.UserStatus status) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(CHANGE_USER_STATUS_BY_ID_SQL)) {
            statement.setInt(ChangeUserStatusIndex.STATUS, status.getValue());
            statement.setLong(ChangeUserStatusIndex.ID, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.changeUserStatusById request", e);
        }
    }

    @Override
    public void setPasswordByEmail(String email, String password) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_EMAIL)){
            statement.setString(UpdateUserPasswordIndex.PASSWORD, password);
            statement.setString(UpdateUserPasswordIndex.EMAIL, email);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.setPasswordByEmail request", e);
        }
    }

    /**
     * Update {@link User} icon
     * @param id - users id
     * @param icon - new users icon
     * @throws DaoException when problems with database connection occurs
     */
    @Override
    public void updateUserIconById(long id, String icon) throws DaoException {
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ICON_BY_ID_SQL)) {
            statement.setString(UpdateUserIconIndex.ICON, icon);
            statement.setLong(UpdateUserIconIndex.ID, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.updateUserIconById request", e);
        }
    }

    /**
     * Activates {@link User} account by changing its {@link User.UserStatus} to {@link User.UserStatus#APPROVED}
     * @param email - users email
     * @return - {@link User} with all data
     * @throws DaoException when problems with database connection occurs
     */
    @Override
    public User activateUserByEmail(String email) throws DaoException {
        User user = new User();
        Connection connection = null;
        try{
            connection = pool.getConnection();

            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_USER_STATUS_BY_EMAIL_SQL);
            preparedStatement.setInt(ChangeUserStatusIndex.STATUS, User.UserStatus.APPROVED.getValue());
            preparedStatement.setString(ChangeUserStatusIndex.EMAIL, email);
            preparedStatement.execute();

            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_SQL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = createUser(resultSet);
            }
            connection.commit();
            return user;
        } catch (SQLException e) {
            if (connection != null){
                try{
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new DaoException("Unable to rollback in UserDao.activateUserByEmail request", ex);
                }
            }
            throw new DaoException("Unable to handle UserDao.activateUserByEmail request", e);
        }
        finally {
            if (connection != null){
                try{
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "Unable to handle UserDao.activateUserByEmail request");
                }
            }
        }
    }

    @Override
    public User.UserStatus findUserStatusById(long id) throws DaoException {
        User.UserStatus status = null;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USER_STATUS_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                status = User.UserStatus.valueOf(resultSet.getString(ColumnName.STATUS_TYPE).toUpperCase());
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.findUserStatusById request", e);
        }
        return status;
    }

    @Override
    public boolean checkUserByEmail(String email) throws DaoException {
        boolean result = false;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(CHECK_USER_BY_EMAIL)){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.checkUserByEmail request", e);
        }
        return result;
    }

    /**
     * Adds new user with {@link User.UserStatus#IN_PROCESS} status to database with specified parameters
     * @param login - new user login
     * @param email - new user email
     * @param password - new user password
     * @return true if user was added to database, false if database contains user with same email
     * @throws DaoException when problems with database connection occurs
     */
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
                throw new DaoException("Unable to handle UserDao signUp request", e);
            }
        }
    }

    @Override
    public Optional<User> findById(long id) throws DaoException {
        User user = null;
        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID_SQL)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                user = createUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao.findById request", e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> list;
        try(Connection connection = pool.getConnection();
            Statement statement = connection.createStatement()){
            ResultSet set = statement.executeQuery(FIND_ALL_USERS_SQL);
            list = new ArrayList<>();
            while (set.next()){
                User user = createUser(set);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to handle UserDao findAll request", e);
        }
        return list;
    }

    private static User createUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ColumnName.USER_ID));
        user.setStatus(User.UserStatus.valueOf(resultSet.getString(ColumnName.STATUS_TYPE).toUpperCase()));
        user.setRole(User.Role.valueOf(resultSet.getString(ColumnName.ROLE_TYPE).toUpperCase()));
        user.setLogin(resultSet.getString(ColumnName.LOGIN));
        user.setEmail(resultSet.getString(ColumnName.EMAIL));
        user.setIcon(resultSet.getString(ColumnName.ICON));
        return user;
    }
    private static class SignUpIndex{
        private static final int LOGIN = 1;
        private static final int PASSWORD = 2;
        private static final int EMAIL = 3;
        private static final int ROLE = 4;
        private static final int STATUS = 5;
    }
    private static class ChangeUserStatusIndex{
        private static final int STATUS = 1;
        public static final int ID = 2;
        public static final int EMAIL = 2;
    }
    private static class UpdateUserIconIndex{
        private static final int ICON = 1;
        private static final int ID = 2;
    }
    private static class UpdateUserPasswordIndex{
        private static final int PASSWORD = 1;
        private static final int EMAIL = 2;
    }
}
