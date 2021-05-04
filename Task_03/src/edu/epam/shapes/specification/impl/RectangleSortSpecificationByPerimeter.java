package edu.epam.shapes.specification.impl;

import edu.epam.shapes.entity.Rectangle;
import edu.epam.shapes.service.CalculateService;
import edu.epam.shapes.service.impl.CalculateServiceImpl;

import java.util.Comparator;

public class RectangleSortSpecificationByPerimeter implements Comparator<Rectangle> {
    private static final CalculateService service = new CalculateServiceImpl();
    @Override
    public int compare(Rectangle o1, Rectangle o2) {
        int perimeter1 = service.perimeter(o1);
        int perimeter2 = service.perimeter(o2);
        return Integer.compare(perimeter1, perimeter2);
    }
}
