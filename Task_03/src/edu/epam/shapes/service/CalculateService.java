package edu.epam.shapes.service;

import edu.epam.shapes.entity.Rectangle;

public interface CalculateService {
    int square(Rectangle rectangle);
    int perimeter(Rectangle rectangle);
}
