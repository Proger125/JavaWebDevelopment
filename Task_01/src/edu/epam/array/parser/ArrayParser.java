package edu.epam.array.parser;

import edu.epam.array.entity.IntArray;
import edu.epam.array.exception.ArrayCustomException;
import edu.epam.array.validator.IntValidator;

public class ArrayParser {
    public IntArray parseToArray(String[] data, String separator) throws ArrayCustomException {
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
            int i = 0;
            while (i < tokens.length){
                if (IntValidator.isIntValid(tokens[i])){
                    array[i] = parseToInt(tokens[i]);
                    i++;
                }else{
                    isCorrectString = false;
                    break;
                }
            }
            if (isCorrectString){
                return new IntArray(array);
            }
        }
        throw new ArrayCustomException("There is no correct string in the data");
    }
    private Integer parseToInt(String data){
        return Integer.parseInt(data);
    }
}
