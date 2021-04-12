package edu.epam.shapes.validator;

import edu.epam.shapes.entity.Point;

public class QuadrangleValidator {
    public boolean validateQuadrangle(Point p1, Point p2, Point p3, Point p4){
        if (isOnOneLine(p1,p2,p3) || isOnOneLine(p1, p2, p4) || isOnOneLine(p2, p3, p4) || isOnOneLine(p1, p3, p4)){
            return false;
        }
        return true;
    }
    private boolean isOnOneLine(Point p1, Point p2, Point p3){
        if ((p3.getX() - p1.getX()) / (p2.getX() - p1.getX()) == (p3.getY() - p1.getY())/(p2.getY() - p1.getY())){
            return true;
        }
        return false;
    }
}
