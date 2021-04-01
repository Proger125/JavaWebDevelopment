package edu.epam.array.validator;

public final class IntValidator {
    private IntValidator(){}
    public static boolean isIntValid(String data){
        try{
            Integer.parseInt(data);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
