package edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.expressions.ArrayFunctionExpression;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public final class ArrayDefineService {
    static Logger logger = LogManager.getLogger();
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
        logger.log(Level.INFO, "Sum of elements is " + sum);
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
        logger.log(Level.INFO, "Count of elements by expression is " + count);
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
        logger.log(Level.INFO, "Average elements is " + array.getElementAt(array.getSize() / 2));
        return array.getElementAt(array.getSize() / 2);
    }
}
