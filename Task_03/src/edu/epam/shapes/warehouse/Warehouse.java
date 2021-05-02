package edu.epam.shapes.warehouse;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.exception.ShapeException;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static final Warehouse instance = new Warehouse();
    private final Map<Long, RectangleParameter> map = new HashMap<>();

    public static Warehouse getInstance(){
        return instance;
    }
    public void add(long id, int perimeter, int square){
        RectangleParameter parameter = new RectangleParameter(perimeter, square);
        map.put(id, parameter);
    }
    public RectangleParameter getParameter(long id) throws ShapeException {
        RectangleParameter parameter = map.get(id);
        if (parameter == null){
            throw new ShapeException("Wrong id. There is no such element in warehouse");
        }
        return parameter;
    }
    public void updateParameter(long id, int newPerimeter, int newSquare) throws ShapeException {
        RectangleParameter parameter = map.get(id);
        if (parameter == null){
            throw new ShapeException("Wrong id. There is no such element in warehouse");
        }
        parameter.setPerimeter(newPerimeter);
        parameter.setSquare(newSquare);
    }
}
