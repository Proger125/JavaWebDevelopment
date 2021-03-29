package edu.epam.arrays.parser;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.validator.IntValidator;

public class ArrayParser {
    public Array parseToArray(String[] data, String separator) throws ArrayCustomException {
        if (data == null){
            throw new ArrayCustomException("String is null");
        }
        if (separator == null){
            throw new ArrayCustomException("Separator is null");
        }
        for (int k = 0; k < data.length; k++){
            String[] tokens = data[k].split(separator);
            Integer[] array = new Integer[tokens.length];
            boolean isCorrectString = true;
            for (int i = 0; i < tokens.length; i++){
                if (IntValidator.isValidate(tokens[i])){
                    array[i] = parseToInt(tokens[i]);
                }else{
                    isCorrectString = false;
                    break;
                }
            }
            if (isCorrectString){
                return new Array(array);
            }else{
                continue;
            }
        }
        throw new ArrayCustomException("There is no correct string in the data");
    }
    private Integer parseToInt(String data){
        return Integer.parseInt(data);
    }
}
