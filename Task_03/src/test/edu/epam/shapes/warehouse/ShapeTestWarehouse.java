package test.edu.epam.shapes.warehouse;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.factory.ShapeFactory;
import edu.epam.shapes.observer.impl.RectangleObserver;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import edu.epam.shapes.warehouse.Warehouse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class ShapeTestWarehouse {
    private Warehouse warehouse;
    private CalculateService service;
    private ShapeFactory factory;
    @BeforeClass
    public void setUp(){
        warehouse = Warehouse.getInstance();
        service = new CalculateServiceImpl();
        factory = new ShapeFactory();
    }
    @AfterClass
    public void tearDown(){
        warehouse = null;
        service = null;
        factory = null;
    }
    @Test
    public void UpdateElementsInWarehouseTest() throws ShapeException {
        Rectangle rectangle = factory.createRectangle(new int[]{1, 1, 5, 5});
        rectangle.attach(new RectangleObserver());
        int perimeter = service.perimeter(rectangle);
        int square = service.square(rectangle);
        long id = rectangle.getId();
        warehouse.add(id, perimeter, square);
        rectangle.setWidth(6);
        int expectedPerimeter = 22;
        int actualPerimeter = warehouse.getParameter(id).getPerimeter();
        assertEquals(actualPerimeter, expectedPerimeter);
    }
}
