package test.edu.epam.arrays.services;

import edu.epam.arrays.entity.IntArray;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.service.impl.ArrayDefineServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ArrayDefineServiceTest {
    IntArray array;
    ArrayDefineServiceImpl service;
    @BeforeClass
    public void setUp(){
        array = new IntArray(1, 2, 3, 4, 5);
        service = new ArrayDefineServiceImpl();
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
    public void sumStreamTestTrue() throws ArrayCustomException{
        Integer expected = 15;
        Integer actual = service.sum(array);
        assertEquals(actual, expected);
    }
    @Test
    public void sumStreamTestFalse() throws ArrayCustomException{
        Integer expected = 10;
        Integer actual = service.sumStream(array);
        assertNotEquals(actual, expected);
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void sumStreamTestException() throws ArrayCustomException {
        IntArray array1 = new IntArray();
        Integer actual = service.sumStream(array1);
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
    @Test
    public void countElementsByExpressionStream() throws ArrayCustomException{
        Long expected = 2L;
        Long actual = service.countElementsByExpressionStream(array, (a) -> a % 2 == 0);
        assertEquals(actual, expected);
    }
    @Test
    public void defineAverageInArrayStreamTestTrue() throws ArrayCustomException {
        Double expected = 3.0;
        Double actual = service.defineAverageInArrayStream(array);
        assertEquals(actual, expected);
    }
}
