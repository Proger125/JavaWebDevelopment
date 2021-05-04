package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;

import java.util.Comparator;

public class RectangleSortSpecificationBySquare implements Comparator<Rectangle> {
    private static final CalculateService service = new CalculateServiceImpl();
    @Override
    public int compare(Rectangle o1, Rectangle o2) {
        int square1 = service.square(o1);
        int square2 = service.square(o2);
        return Integer.compare(square1, square2);
    }
}
