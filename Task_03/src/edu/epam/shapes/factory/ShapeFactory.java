package edu.epam.shapes.factory;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.exception.ShapeException;
import edu.epam.shapes.util.IdGenerator;

public class ShapeFactory {
    public Rectangle createRectangle(int[] array) throws ShapeException {
        if (array == null || array.length != 4){
            throw new ShapeException("Incorrect input data");
        }
        long id = IdGenerator.createId();
        Point point = new Point(array[0], array[1]);
        int width = array[2];
        int height = array[3];
        return new Rectangle(id, point, width, height);
    }
}
