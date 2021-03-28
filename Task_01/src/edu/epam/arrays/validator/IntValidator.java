package edu.epam.arrays.validator;

public final class IntValidator {
    private IntValidator(){}
    public static boolean isValidate(String data){
        try{
            Integer.parseInt(data);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
