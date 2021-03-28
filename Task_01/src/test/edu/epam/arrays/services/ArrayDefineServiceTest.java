package test.edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.services.impl.ArrayDefineService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ArrayDefineServiceTest {
    Array array;
    ArrayDefineService service;
    @BeforeClass
    public void setUp(){
        array = new Array(1, 2, 3, 4, 5);
        service = new ArrayDefineService();
    }
    @AfterClass
    public void tearDown(){
        array = null;
        service = null;
    }
    @Test
    public void sumTestTrue() throws ArrayCustomException {
        Integer expected = 15;
        Integer actual = service.sum(array);
        assertEquals(actual, expected);
    }
    @Test
    public void sumTestFalse() throws ArrayCustomException{
        Integer expected = 10;
        Integer actual = service.sum(array);
        assertNotEquals(actual, expected);
    }
    @Test
    public void countElementsByExpressionTestTrue() throws ArrayCustomException {
        Integer expected = 2;
        Integer actual = service.countElementsByExpression(array, (n)->n % 2 == 0);
        assertEquals(actual, expected);
    }
    @Test
    public void countElementsByExpressionTestFalse() throws ArrayCustomException{
        Integer expected = 2;
        Integer actual = service.countElementsByExpression(array, (n)->n % 2 != 0);
        assertNotEquals(actual, expected);
    }
}
