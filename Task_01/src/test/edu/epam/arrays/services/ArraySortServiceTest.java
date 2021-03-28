package test.edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.services.impl.ArraySortService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArraySortServiceTest {
    ArraySortService sortService;
    @DataProvider
    public Object[][] dataProviderFunction(){
        return new Object[][]{
                {new Array(5, 4, 3, 2, 1), new Array(1, 2, 3, 4, 5)},
                {new Array(3, 3, 1, 5, 4), new Array(1, 3, 3, 4, 5)},
                {new Array(1, 1, 5, 3, 4), new Array(1, 1, 3, 4, 5)}
        };
    }
    @BeforeClass
    public void setUp(){
        sortService = new ArraySortService();
    }
    @Test(dataProvider = "dataProviderFunction")
    public void bubbleSortTest(Array data, Array expected) throws ArrayCustomException {
        sortService.bubbleSort(data);
        assertEquals(data, expected);
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void bubbleSortTestException() throws ArrayCustomException {
        Array array = null;
        sortService.bubbleSort(array);
    }
}
