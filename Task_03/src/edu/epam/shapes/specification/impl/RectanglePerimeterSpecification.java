package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;
import edu.epam.shapes.specification.RectangleSpecification;

public class RectanglePerimeterSpecification implements RectangleSpecification {
    private int perimeter;
    private static final CalculateService service = new CalculateServiceImpl();

    public RectanglePerimeterSpecification(int perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public boolean specified(Rectangle rectangle) {
        return perimeter == service.perimeter(rectangle);
    }
}
