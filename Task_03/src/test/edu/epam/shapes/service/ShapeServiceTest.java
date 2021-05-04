package test.edu.epam.shapes.service;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.factory.ShapeFactory;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
public class ShapeServiceTest {
    private CalculateService service;
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    @BeforeClass
    public void setUp() throws ShapeException {
        ShapeFactory factory = new ShapeFactory();
        service = new CalculateServiceImpl();
        rectangle1 = factory.createRectangle(new int[]{1, 1, 5, 5});
        rectangle2 = factory.createRectangle(new int[]{1, 1, 10, 5});
    }
    @AfterClass
    public void tearDown(){
        rectangle1 = null;
    }
    @Test
    public void PerimeterTestTrue(){
        int actual = service.perimeter(rectangle1);
        int expected = 20;
        assertEquals(actual, expected);
    }
    @Test
    public void PerimeterTestFalse(){
        int actual = service.perimeter(rectangle2);
        int expected = 10;
        assertNotEquals(actual, expected);
    }
    @Test
    public void SquareTestTrue(){
        int actual = service.square(rectangle1);
        int expected = 25;
        assertEquals(actual, expected);
    }
    @Test
    public void SquareTestFalse(){
        int actual = service.square(rectangle2);
        int expected = 100;
        assertNotEquals(actual, expected);
    }
}
