package edu.epam.arrays.services.impl;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.expressions.ArrayFunctionExpression;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.epam.arrays.services.*;
import java.util.Arrays;

public class ArrayDefineService implements IArrayDefineService{
    static Logger logger = LogManager.getLogger();
    public Integer sum(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            throw new ArrayCustomException("Array is empty");
        }
        Integer sum = 0;
        for (int i = 0; i < array.getSize(); i++){
            sum += array.getElementAt(i);
        }
        logger.log(Level.INFO, "Sum of elements is " + sum);
        return sum;
    }
    public Integer countElementsByExpression(Array array, ArrayFunctionExpression expression) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
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
    public Integer defineAverageElement(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            throw new ArrayCustomException("Array is empty");
        }
        Arrays.sort(array.getData());
        logger.log(Level.INFO, "Average elements is " + array.getElementAt(array.getSize() / 2));
        return array.getElementAt(array.getSize() / 2);
    }
}
