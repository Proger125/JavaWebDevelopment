package test.edu.epam.shapes.factory;
import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.factory.ShapeFactory;
import edu.epam.shapes.parser.ShapeParser;
import edu.epam.shapes.reader.RectangleReader;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
public class ShapeFactoryTest {
    private RectangleReader reader;
    private ShapeParser parser;
    private ShapeFactory factory;
    private List<Rectangle> correctList1;
    private List<Rectangle> incorrectList1;
    private List<Rectangle> actualList;
    @BeforeClass
    public void setUp(){
        reader = new RectangleReader();
        parser = new ShapeParser();
        factory = new ShapeFactory();
        actualList = new ArrayList<>();
        correctList1 = new ArrayList<>();
        correctList1.add(new Rectangle(1, new Point(1, 1), 5, 5));
        correctList1.add(new Rectangle(2, new Point(2, 1), 6, 5));
        incorrectList1 = new ArrayList<>();
        incorrectList1.add(new Rectangle(1, new Point(1, 1), 5, 5));
    }
    @AfterClass
    public void tearDown(){
        reader = null;
        parser = null;
        factory = null;
    }
    @Test
    public void FactoryResultTestTrue() throws ShapeException {
        List<String> strings = reader.readAllCorrectLines("src/test/edu/epam/resources/input1.txt", " ");
        List<int[]> list = parser.parseAllCorrectStrings(strings);
        actualList = factory.createRectangles(list);
        assertEquals(actualList, correctList1);
    }
    @Test
    public void FactoryResultTestFalse() throws ShapeException {
        List<String> strings = reader.readAllCorrectLines("src/test/edu/epam/resources/input1.txt", " ");
        List<int[]> list = parser.parseAllCorrectStrings(strings);
        assertNotEquals(actualList, incorrectList1);
    }
}
