package test.edu.epam.shapes.io;

import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.reader.RectangleReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class ShapeInputTest {
    private RectangleReader reader;
    private List<String> correctList1;
    private List<String> correctList2;
    @BeforeClass
    public void setUp(){
        reader = new RectangleReader();
        correctList1 = new ArrayList<>();
        correctList1.add("1 1 5 5");
        correctList1.add("2 1 6 5");
        correctList2 = new ArrayList<>();
        correctList2.add("1 2 3 4");
        correctList2.add("5 6 7 8");
        correctList2.add("1 2 3 4");
    }
    @AfterClass
    public void tearDown(){
        correctList2 = null;
        correctList1 = null;
    }
    @Test
    public void InputLengthTestTrue() throws ShapeException {
        List<String> actualList = reader.readAllCorrectLines("src/test/edu/epam/resources/input1.txt", " ");
        int expected = correctList1.size();
        assertEquals(actualList.size(), expected);
    }
    @Test
    public void InputLengthTestFalse() throws ShapeException {
        List<String> actualList = reader.readAllCorrectLines("src/test/edu/epam/resources/input2.txt", " ");
        int expected = correctList2.size() - 1;
        assertNotEquals(actualList.size(), expected);
    }
    @Test(expectedExceptions = ShapeException.class)
    public void InputTestExceptionIncorrectFileName() throws ShapeException {
        List<String> actualList = reader.readAllCorrectLines("test.txt", " ");

    }
    @Test(expectedExceptions = ShapeException.class)
    public void InputTestExceptionEmptyFile() throws ShapeException {
        List<String> actualList = reader.readAllCorrectLines("src/test/edu/epam/resources/input3.txt", " ");

    }
}
