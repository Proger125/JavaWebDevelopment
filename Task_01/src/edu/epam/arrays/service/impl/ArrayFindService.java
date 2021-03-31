package edu.epam.arrays.service.impl;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.service.IArrayFindService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;


public class ArrayFindService implements IArrayFindService {
    static Logger logger = LogManager.getLogger();
    public Integer findMin(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
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
    public Integer findMinStream(Array array) throws ArrayCustomException{
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            throw new ArrayCustomException("Array is empty");
        }
        int[] data = new int[array.getSize()];
        for (int i = 0; i < array.getSize(); i++){
            data[i] = array.getElementAt(i);
        }
        Integer min = Arrays.stream(data).min().getAsInt();
        return min;
    }
    public Integer findMax(Array array) throws ArrayCustomException {
        if (array == null){
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
}
