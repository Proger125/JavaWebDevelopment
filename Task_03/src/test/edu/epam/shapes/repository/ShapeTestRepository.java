package test.edu.epam.shapes.repository;
import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.repository.RectangleRepository;
import edu.epam.shapes.repository.impl.RectangleRepositoryImpl;
import edu.epam.shapes.specification.impl.RectanglePerimeterSpecification;
import edu.epam.shapes.specification.impl.RectangleSortSpecificationBySquare;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
public class ShapeTestRepository {
    private RectangleRepository repository;
    private List<Rectangle> correctSquareList;
    private List<Rectangle> correctPerimeterList;
    @BeforeClass
    public void setUp(){
        repository = RectangleRepositoryImpl.getInstance();
        Rectangle rectangle1 = new Rectangle(1, new Point(1,1), 5, 5);
        Rectangle rectangle2 =new Rectangle(2, new Point(2, 2), 10, 3);
        Rectangle rectangle3 = new Rectangle(3, new Point(5, 3), 5, 4);
        repository.add(rectangle1);
        repository.add(rectangle2);
        repository.add(rectangle3);
        correctSquareList = new ArrayList<>();
        correctSquareList.add(rectangle3);
        correctSquareList.add(rectangle1);
        correctSquareList.add(rectangle2);
        correctPerimeterList = new ArrayList<>();
        correctPerimeterList.add(rectangle3);
    }
    @AfterClass
    public void tearDown(){
        repository = null;
    }
    @Test
    public void SortSquareTest(){
        List<Rectangle> actualList = repository.sort(new RectangleSortSpecificationBySquare());
        assertEquals(actualList, correctSquareList);
    }
    @Test
    public void QueryPerimeterTest(){
        List<Rectangle> actualList = repository.query(new RectanglePerimeterSpecification(18));
        assertEquals(actualList, correctPerimeterList);
    }
}
