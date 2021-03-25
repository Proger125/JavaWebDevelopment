package edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.expressions.ArrayFunctionExpression;

import java.util.Arrays;

public final class ArrayDefineService {
    private ArrayDefineService(){}
    public static Integer sum(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() > 0){
            throw new ArrayCustomException("Array is empty");
        }
        Integer sum = 0;
        for (int i = 0; i < array.getSize(); i++){
            sum += array.getElementAt(i);
        }
        return sum;
    }
    public static Integer countElementsByExpression(Array array, ArrayFunctionExpression expression) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() > 0){
            throw new ArrayCustomException("Array is empty");
        }
        Integer count = 0;
        for (int i = 0; i < array.getSize(); i++){
            if (expression.isEqual(array.getElementAt(i))){
                count++;
            }
        }
        return count;
    }
    public static Integer defineAverageElement(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() > 0){
            throw new ArrayCustomException("Array is empty");
        }
        Arrays.sort(array.getData());
        return array.getElementAt(array.getSize() / 2);
    }
}
