package edu.epam.array.service.impl;

import edu.epam.array.entity.IntArray;
import edu.epam.array.exception.ArrayCustomException;
import edu.epam.array.service.ArrayFindService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class ArrayFindServiceImpl implements ArrayFindService {
    static Logger logger = LogManager.getLogger();
    public Integer findMin(IntArray array) throws ArrayCustomException {
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayCustomException("Array is empty");
        }
        Integer min = array.getElementAt(0);
        for (int i = 1; i < array.getSize(); i++){
            int element = array.getElementAt(i);
            min = Math.min(element, min);
        }
        logger.log(Level.INFO, "Min element is " + min);
        return min;
    }
    public Integer findMinStream(IntArray array) throws ArrayCustomException{
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayCustomException("Array is empty");
        }
        Integer min = Arrays.stream(array.getIntData()).min().getAsInt();
        logger.log(Level.INFO, "Min element is " + min);
        return min;
    }
    public Integer findMax(IntArray array) throws ArrayCustomException {
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            throw new ArrayCustomException("Array is empty");
        }
        Integer max = array.getElementAt(0);
        for (int i = 1; i < array.getSize(); i++){
            int element = array.getElementAt(i);
            max = Math.max(element, max);
        }
        logger.log(Level.INFO, "Max element is " + max);
        return max;
    }
    public Integer findMaxStream(IntArray array) throws ArrayCustomException{
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayCustomException("Array is empty");
        }
        Integer max = Arrays.stream(array.getIntData()).max().getAsInt();
        logger.log(Level.INFO, "Max element is " + max);
        return max;
    }
}
