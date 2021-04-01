package test.edu.epam.arrays.io;

import edu.epam.arrays.entity.IntArray;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.parser.ArrayParser;
import edu.epam.arrays.reader.ArrayReader;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ArrayInputTest {
    @Test
    public void arrayInput1TestCorrect() throws ArrayCustomException {
        ArrayReader reader = new ArrayReader("resources/input1.txt");
        String[] strings = reader.readAllLines();
        ArrayParser parser = new ArrayParser();
        IntArray actual = parser.parseToArray(strings, " ");
        IntArray expected = new IntArray(1, 2, 3, 4, 5);
        assertEquals(actual, expected);
    }
    @Test
    public void arrayInput2TestCorrect() throws ArrayCustomException{
        ArrayReader reader = new ArrayReader("resources/input2.txt");
        String[] strings = reader.readAllLines();
        ArrayParser parser = new ArrayParser();
        IntArray actual = parser.parseToArray(strings, " ");
        IntArray expected = new IntArray(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(actual, expected);
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void arrayInput3TestException() throws ArrayCustomException{
        ArrayReader reader = new ArrayReader("resources/input3.txt");
        String[] strings = reader.readAllLines();
        ArrayParser parser = new ArrayParser();
        IntArray actual = parser.parseToArray(strings, " ");
    }
    @Test(expectedExceptions = ArrayCustomException.class)
    public void arrayInput4TestException() throws ArrayCustomException{
        ArrayReader reader = new ArrayReader("resources/input4.txt");
        String[] strings = reader.readAllLines();
        ArrayParser parser = new ArrayParser();
        IntArray actual = parser.parseToArray(strings, " ");
    }
}
