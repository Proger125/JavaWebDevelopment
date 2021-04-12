package edu.epam.shapes.service.impl;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Quadrangle;
import edu.epam.shapes.service.CalculateService;

public class CalculateServiceImpl implements CalculateService {
    @Override
    public double square(Quadrangle quadrangle) {
        //TODO
        Point upperLeft = quadrangle.getUpperLeft();
        Point lowerLeft = quadrangle.getLowerLeft();
        Point upperRight = quadrangle.getUpperRight();
        int width = upperRight.getX() - upperLeft.getX();
        int height = upperLeft.getY() - lowerLeft.getY();
        int square =  width * height;
        return square;
    }

    @Override
    public double perimeter(Quadrangle quadrangle) {
        Point upperLeft = quadrangle.getUpperLeft();
        Point lowerLeft = quadrangle.getLowerLeft();
        Point upperRight = quadrangle.getUpperRight();
        Point lowerRight = quadrangle.getLowerRight();
        double a = Math.hypot(upperLeft.getX() - upperRight.getY(), upperLeft.getY() - upperRight.getY());
        double b = Math.hypot(upperRight.getX() - lowerRight.getY(), upperRight.getY() - lowerRight.getY());
        double c = Math.hypot(lowerRight.getX() - lowerLeft.getY(), lowerRight.getY() - lowerLeft.getY());
        double d = Math.hypot(lowerLeft.getX() - upperLeft.getY(), lowerLeft.getY() - upperLeft.getY());
        return a + b + c + d;
    }
}
