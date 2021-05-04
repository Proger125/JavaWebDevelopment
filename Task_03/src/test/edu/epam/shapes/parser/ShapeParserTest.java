package test.edu.epam.shapes.parser;

import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.parser.ShapeParser;
import edu.epam.shapes.reader.RectangleReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ShapeParserTest {
    private RectangleReader reader;
    private ShapeParser parser;
    private List<int[]> correctList1;
    private List<int[]> correctList2;
    @BeforeClass
    public void setUp(){
        reader = new RectangleReader();
        parser = new ShapeParser();
        correctList1 = new ArrayList<>();
        correctList1.add(new int[]{1, 1, 5, 5});
        correctList1.add(new int[]{2, 1, 6, 5});
        correctList2 = new ArrayList<>();
        correctList2.add(new int[]{1, 2, 3, 4});
        correctList2.add(new int[]{5, 6, 7, 8});
        correctList2.add(new int[]{1, 2, 3, 4});
    }
    @AfterClass
    public void tearDown(){
        reader = null;
        parser = null;
    }
    @Test
    public void ParserLengthTestTrue() throws ShapeException {
        List<String> list = reader.readAllCorrectLines("src/test/edu/epam/resources/input1.txt", " ");
        List<int[]> actualList = parser.parseAllCorrectStrings(list);
        int expected = correctList1.size();
        assertEquals(actualList.size(), expected);
    }
    @Test
    public void ParserLengthTestFalse() throws ShapeException {
        List<String> list = reader.readAllCorrectLines("src/test/edu/epam/resources/input2.txt", " ");
        List<int[]> actualList = parser.parseAllCorrectStrings(list);
        int expected = correctList2.size() - 1;
        assertNotEquals(actualList.size(), expected);
    }
    @Test
    public void ParserResultTestTrue() throws ShapeException {
        List<String> list = reader.readAllCorrectLines("src/test/edu/epam/resources/input1.txt", " ");
        List<int[]> actualList = parser.parseAllCorrectStrings(list);
        assertEquals(actualList, correctList1);
    }
    @Test
    public void ParserResultTestFalse() throws ShapeException {
        List<String> list = reader.readAllCorrectLines("src/test/edu/epam/resources/input2.txt", " ");
        List<int[]> actualList = parser.parseAllCorrectStrings(list);
        assertNotEquals(actualList, correctList1);
    }
    @Test(expectedExceptions = ShapeException.class)
    public void ParseTestException() throws ShapeException {
        List<int[]> actualList = parser.parseAllCorrectStrings(null);
    }
}
