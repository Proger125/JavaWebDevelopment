package test.edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.services.impl.ArrayReplaceService;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ArrayReplaceServiceTest {
    Array array;
    ArrayReplaceService service;
    @BeforeMethod
    public void setUp(){
        array = new Array(1, 2, 3, 4, 5);
        service = new ArrayReplaceService();
    }
    @AfterMethod
    public void tearDown(){
        array = null;
        service = null;
    }
    @Test
    public void replaceElementsByExpressionTestTrue() throws ArrayCustomException {
        Array expected = new Array(1, -1, 3, -1, 5);
        service.replaceElementsByExpression(array, (n)->n % 2 == 0, -1);
        assertEquals(array, expected);
    }
    @Test
    public void replaceElementsByExpressionTestFalse() throws ArrayCustomException{
        Array expected = new Array(1, -1, 3, -1, 5);
        service.replaceElementsByExpression(array, (n)->n % 2 == 1, -1);
        assertNotEquals(array, expected);
    }
}
