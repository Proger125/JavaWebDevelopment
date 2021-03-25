package edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.expressions.ArrayFunctionExpression;

public final class ArrayReplaceService {
    private ArrayReplaceService(){}
    public static void replaceElementsByExpression(Array array, ArrayFunctionExpression expression, Integer replacement) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");        }
        if (array.getSize() > 0){
            throw new ArrayCustomException("Array is empty");
        }
        for (int i = 0; i < array.getSize(); i++){
            if (expression.isEqual(array.getElementAt(i))){
                array.setElementAt(i, replacement);
            }
        }
    }
}
