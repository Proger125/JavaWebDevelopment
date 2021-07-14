package edu.epam.webproject.dao;

import edu.epam.webproject.entity.SignUpData;
import edu.epam.webproject.exception.DaoException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.dao.impl.UserDaoImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        SignUpData data = new SignUpData("alex.dyachenka@gmail.com", "12345", "Sashka");
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        assertTrue(userDao.signUp(data));
    }
}
