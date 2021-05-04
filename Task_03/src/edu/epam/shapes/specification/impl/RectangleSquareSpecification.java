package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import edu.epam.shapes.specification.RectangleSpecification;

public class RectangleSquareSpecification implements RectangleSpecification {
    private int square;
    private static final CalculateService service = new CalculateServiceImpl();

    public RectangleSquareSpecification(int square) {
        this.square = square;
    }

    @Override
    public boolean specified(Rectangle rectangle) {
        return square == service.square(rectangle);
    }
}
