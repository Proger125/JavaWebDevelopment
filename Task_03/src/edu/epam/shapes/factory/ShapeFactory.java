package edu.epam.shapes.factory;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.util.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ShapeFactory {
    private static final Logger logger = LogManager.getLogger();
    public List<Rectangle> createRectangles(List<int[]> list) throws ShapeException {
        if (list == null){
            throw new ShapeException("List is null");
        }
        List<Rectangle> rectangles = new ArrayList<>();
        for (int[] array : list){
            rectangles.add(createRectangle(array));
        }

        return rectangles;
    }
    public Rectangle createRectangle(int[] array) throws ShapeException {
        if (array == null || array.length != 4){
            throw new ShapeException("Incorrect input data");
        }
        long id = IdGenerator.createId();
        Point point = new Point(array[0], array[1]);
        int width = array[2];
        int height = array[3];
        logger.log(Level.INFO, "Rectangle was created");
        return new Rectangle(id, point, width, height);
    }
}
