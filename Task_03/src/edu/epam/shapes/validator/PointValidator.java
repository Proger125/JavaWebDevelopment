package edu.epam.shapes.validator;

public class PointValidator {
    public boolean validatePoint(String x, String y){
        try {
            Integer.parseInt(x);
            Integer.parseInt(y);
        }catch (NumberFormatException exception){
            return false;
        }
        return true;
    }
}
