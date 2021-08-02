package edu.epam.webproject.dao;

import edu.epam.webproject.entity.SignUpData;
import edu.epam.webproject.entity.User;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.impl.UserDaoImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserDaoTest {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    @BeforeClass
    public void setUp(){
        connectionPool.init();
    }
    @AfterClass
    public void tearDown(){
        connectionPool.destroy();
    }
    @Test
    public void signUpUserTestTrue() throws DaoException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        assertTrue(userDao.signUp("Alex", "alexdyachenka@gmail.com", "Sashka125"));
    }
    @Test
    public void signInUserTestTrue() throws DaoException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        User actual = (userDao.signIn("alex.dyachenka@gmail.com", "12345")).get();
        User expected = new User();
        expected.setId(11);
        expected.setLogin("Sashka");
        expected.setEmail("alex.dyachenka@gmail.com");
        expected.setIcon(null);
        expected.setRole(User.Role.USER);
        expected.setStatus(User.UserStatus.IN_PROCESS);
        assertEquals(actual, expected);
    }
}
