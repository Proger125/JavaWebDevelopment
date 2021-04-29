package edu.epam.shapes.service.impl;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.service.CalculateService;

public class CalculateServiceImpl implements CalculateService {
    @Override
    public int square(Rectangle rectangle) {
        return rectangle.getHeight() * rectangle.getWidth();
    }

    @Override
    public int perimeter(Rectangle rectangle) {
        int halfPerimeter = rectangle.getHeight() + rectangle.getWidth();
        return halfPerimeter * 2;
    }
}
