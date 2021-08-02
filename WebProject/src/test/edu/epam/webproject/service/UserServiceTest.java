package edu.epam.webproject.service;

import edu.epam.webproject.exception.ServiceException;
import edu.epam.webproject.model.connection.ConnectionPool;
import edu.epam.webproject.model.service.ServiceProvider;
import edu.epam.webproject.model.service.UserService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class UserServiceTest {
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
    public void SignUpTestTrue() throws ServiceException {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        boolean actual = userService.signUp("admin-01", "alexdyachenka@gmail.com", "Sashka125");
        assertTrue(actual);
    }
}
