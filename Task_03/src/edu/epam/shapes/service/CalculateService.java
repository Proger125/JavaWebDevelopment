package edu.epam.shapes.service;

import edu.epam.shapes.entity.Quadrangle;

public interface CalculateService {
    double square(Quadrangle quadrangle);
    double perimeter(Quadrangle quadrangle);
}
