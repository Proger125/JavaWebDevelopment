package edu.epam.shapes.service.impl;

import edu.epam.shapes.entity.Point;
import edu.epam.shapes.entity.Quadrangle;
import edu.epam.shapes.service.CheckService;

public class CheckServiceImpl implements CheckService {
    @Override
    public boolean isConvex(Quadrangle quadrangle) {
        int direction1 = calculateDirection(quadrangle.getUpperLeft(), quadrangle.getUpperRight(), quadrangle.getLowerRight());
        int direction2 = calculateDirection(quadrangle.getUpperRight(), quadrangle.getLowerRight(), quadrangle.getLowerLeft());
        int direction3 = calculateDirection(quadrangle.getLowerRight(), quadrangle.getLowerLeft(), quadrangle.getUpperLeft());
        int direction4 = calculateDirection(quadrangle.getLowerRight(), quadrangle.getUpperLeft(), quadrangle.getUpperRight());
        if (direction1 * direction2 * direction3 * direction4 > 0){
            return true;
        }
        return false;
    }

    private int calculateDirection(Point a, Point b, Point c){
        Point ab = new Point(b.getX() - a.getX(), b.getY() - a.getY());
        Point bc = new Point(c.getX() - b.getX(), c.getY() - b.getY());
        return ab.getX() * bc.getY() - ab.getY() * bc.getX();
    }
    @Override
    public boolean isSquare(Quadrangle quadrangle) {
        return false;
    }

    @Override
    public boolean isRhombus(Quadrangle quadrangle) {
        return false;
    }

    @Override
    public boolean isTrapezoid(Quadrangle quadrangle) {
        return false;
    }
    private boolean isSideParallels(Quadrangle quadrangle){

    }
    private boolean isDiagonalEquals(Quadrangle quadrangle){
        Point upperLeft = quadrangle.getUpperLeft();
        Point upperRight = quadrangle.getUpperRight();
        Point lowerRight = quadrangle.getLowerRight();
        Point lowerLeft = quadrangle.getLowerLeft();
        double d1 = Math.hypot(upperLeft.getX() - lowerRight.getX(), upperLeft.getY() - lowerRight.getY());
        double d2 = Math.hypot(upperRight.getX() - lowerLeft.getX(), upperRight.getY() - lowerLeft.getY());
        if (d1 == d2){
            return true;
        }
        return false;
    }
}
