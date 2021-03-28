package test.edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.services.impl.ArrayFindService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ArrayFindServiceTest {
    Array array;
    ArrayFindService service;
    @BeforeClass
    public void setUp(){
        array = new Array(1, 2, 3, 4, 5);
        service = new ArrayFindService();
    }
    @AfterClass
    public void tearDown(){
        array = null;
        service = null;
    }
    @Test
    public void findMinTestTrue() throws ArrayCustomException {
        Integer expected = 1;
        Integer actual = service.findMin(array);
        assertEquals(actual, expected);
    }
    @Test
    public void findMinTestFalse() throws ArrayCustomException{
        Integer expected = 2;
        Integer actual = service.findMin(array);
        assertNotEquals(actual, expected);
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void findMinTestException() throws ArrayCustomException {
        Array array1 = new Array();
        Integer actual = service.findMin(array1);
    }
    @Test
    public void findMaxTestTrue() throws ArrayCustomException{
        Integer expected = 5;
        Integer actual = service.findMax(array);
        assertEquals(actual, expected);
    }
    @Test
    public void findMaxTestFalse() throws ArrayCustomException{
        Integer expected = 3;
        Integer actual = service.findMax(array);
        assertNotEquals(actual, expected);
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void findMaxTestException() throws ArrayCustomException{
        Array array1 = new Array();
        Integer actual = service.findMax(array1);
    }
}
