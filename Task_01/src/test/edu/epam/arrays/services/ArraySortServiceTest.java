package test.edu.epam.arrays.services;

import edu.epam.arrays.entity.IntArray;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.service.impl.ArraySortServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArraySortServiceTest {
    ArraySortServiceImpl sortService;
    @DataProvider
    public Object[][] dataProviderFunction(){
        return new Object[][]{
                {new IntArray(5, 4, 3, 2, 1), new IntArray(1, 2, 3, 4, 5)},
                {new IntArray(3, 3, 1, 5, 4), new IntArray(1, 3, 3, 4, 5)},
                {new IntArray(1, 1, 5, 3, 4), new IntArray(1, 1, 3, 4, 5)}
        };
    }
    @BeforeClass
    public void setUp(){
        sortService = new ArraySortServiceImpl();
    }
    @AfterClass
    public void tearDown(){
        sortService = null;
    }
    @Test(dataProvider = "dataProviderFunction")
    public void bubbleSortTestTrue(IntArray actual, IntArray expected) throws ArrayCustomException {
        sortService.bubbleSort(actual);
        assertEquals(actual, expected);
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void bubbleSortTestException() throws ArrayCustomException {
        IntArray array = null;
        sortService.bubbleSort(array);
    }
    @Test(dataProvider = "dataProviderFunction")
    public void insertionSortTestTrue(IntArray actual, IntArray expected) throws ArrayCustomException {
        sortService.insertionSort(actual);
        assertEquals(actual, expected);
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void insertionSortException() throws ArrayCustomException{
        IntArray array = null;
        sortService.bubbleSort(array);
    }
}
