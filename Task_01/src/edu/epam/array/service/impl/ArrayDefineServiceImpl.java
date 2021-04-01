package edu.epam.array.service.impl;

import edu.epam.array.entity.IntArray;
import edu.epam.array.exception.ArrayCustomException;
import edu.epam.array.expression.ArrayFunctionExpression;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.epam.array.service.*;
import java.util.Arrays;
import java.util.function.IntPredicate;

public class ArrayDefineServiceImpl implements ArrayDefineService {
    static Logger logger = LogManager.getLogger();
    public Integer sum(IntArray array) throws ArrayCustomException {
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayCustomException("Array is empty");
        }
        Integer sum = 0;
        for (int i = 0; i < array.getSize(); i++){
            sum += array.getElementAt(i);
        }
        logger.log(Level.INFO, "Sum of elements is " + sum);
        return sum;
    }
    public Integer sumStream(IntArray array) throws ArrayCustomException{
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayCustomException("Array is empty");
        }
        Integer sum = Arrays.stream(array.getIntData()).sum();
        logger.log(Level.INFO, "Sum of element is " + sum);
        return sum;
    }
    public Integer countElementsByExpression(IntArray array, ArrayFunctionExpression expression) throws ArrayCustomException {
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
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
    public Long countElementsByExpressionStream(IntArray array, IntPredicate predicate) throws ArrayCustomException{
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayCustomException("Array is empty");
        }
        Long count = Arrays.stream(array.getIntData()).filter(predicate).count();
        logger.log(Level.INFO, "Count elements: " + count);
        return count;
    }
    public Integer defineAverageElement(IntArray array) throws ArrayCustomException {
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayCustomException("Array is empty");
        }
        Arrays.sort(array.getData());
        logger.log(Level.INFO, "Average elements is " + array.getElementAt(array.getSize() / 2));
        return array.getElementAt(array.getSize() / 2);
    }
    public Double defineAverageInArrayStream(IntArray array) throws ArrayCustomException{
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayCustomException("Array is empty");
        }
        Double averageElement = Arrays.stream(array.getIntData()).average().getAsDouble();
        logger.log(Level.INFO, "Average in array is " + averageElement);
        return averageElement;
    }
}
