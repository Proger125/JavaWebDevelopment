package test.edu.epam.arrays.services;

import edu.epam.arrays.entity.IntArray;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.service.impl.ArrayReplaceServiceImpl;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ArrayReplaceServiceTest {
    IntArray array;
    ArrayReplaceServiceImpl service;
    @BeforeMethod
    public void setUp(){
        array = new IntArray(1, 2, 3, 4, 5);
        service = new ArrayReplaceServiceImpl();
    }
    @AfterMethod
    public void tearDown(){
        array = null;
        service = null;
    }
    @Test
    public void replaceElementsByExpressionTestTrue() throws ArrayCustomException {
        IntArray expected = new IntArray(1, -1, 3, -1, 5);
        service.replaceElementsByExpression(array, (n)->n % 2 == 0, -1);
        assertEquals(array, expected);
    }
    @Test
    public void replaceElementsByExpressionTestFalse() throws ArrayCustomException{
        IntArray expected = new IntArray(1, -1, 3, -1, 5);
        service.replaceElementsByExpression(array, (n)->n % 2 == 1, -1);
        assertNotEquals(array, expected);
    }
}
