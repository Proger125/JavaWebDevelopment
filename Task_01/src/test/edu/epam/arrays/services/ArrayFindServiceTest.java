package test.edu.epam.arrays.services;

import edu.epam.arrays.entity.IntArray;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.service.impl.ArrayFindServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ArrayFindServiceTest {
    IntArray array;
    ArrayFindServiceImpl service;
    @BeforeClass
    public void setUp(){
        array = new IntArray(1, 2, 3, 4, 5);
        service = new ArrayFindServiceImpl();
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
        IntArray array1 = new IntArray();
        Integer actual = service.findMin(array1);
    }
    @Test
    public void findMinStreamTestTrue() throws ArrayCustomException {
        Integer expected = 1;
        Integer actual = service.findMinStream(array);
        assertEquals(actual, expected);
    }
    @Test
    public void findMinStreamTestFalse() throws ArrayCustomException{
        Integer expected = 2;
        Integer actual = service.findMinStream(array);
        assertNotEquals(actual, expected);
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void findMinStreamTestException() throws ArrayCustomException {
        IntArray array1 = new IntArray();
        Integer actual = service.findMinStream(array1);
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
        IntArray array1 = new IntArray();
        Integer actual = service.findMax(array1);
    }
    @Test
    public void findMaxStreamTestTrue() throws ArrayCustomException{
        Integer expected = 5;
        Integer actual = service.findMaxStream(array);
        assertEquals(actual, expected);
    }
    @Test
    public void findMaxStreamTestFalse() throws ArrayCustomException{
        Integer expected = 4;
        Integer actual = service.findMinStream(array);
        assertNotEquals(actual, expected);
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void findMaxStreamTestException() throws ArrayCustomException{
        IntArray array1 = new IntArray();
        Integer actual = service.findMaxStream(array1);
    }
}
