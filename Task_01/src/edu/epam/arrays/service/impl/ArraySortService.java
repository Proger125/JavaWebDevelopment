package edu.epam.arrays.service.impl;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.service.IArraySortService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArraySortService implements IArraySortService {
    static Logger logger = LogManager.getLogger();
    @Override
    public void bubbleSort(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() <= 1){
            return;
        }
        boolean sorted = false;
        int temp;
        while (!sorted){
            sorted = true;
            for (int i = 0; i < array.getSize() - 1; i++){
                if (array.getElementAt(i) > array.getElementAt(i + 1)){
                    temp = array.getElementAt(i);
                    array.setElementAt(i, array.getElementAt(i + 1));
                    array.setElementAt(i + 1, temp);
                    sorted = false;
                }
            }
        }
        logger.log(Level.INFO, "Array was sorted");
    }

    @Override
    public void insertionSort(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() <= 1){
            return;
        }
        for (int i = 1; i < array.getSize(); i++){
            int current = array.getElementAt(i);
            int j = i - 1;
            while (j >= 0 && current < array.getElementAt(j)){
                array.setElementAt(j + 1, array.getElementAt(j));
                j--;
            }
            array.setElementAt(j + 1, current);
        }
        logger.log(Level.INFO, "Array was sorted");
    }

    @Override
    public void selectionSort(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() <= 1){
            return;
        }
        for (int i = 0; i < array.getSize(); i++){
            int min = array.getElementAt(i);
            int minId = i;
            for (int j = i + 1; j < array.getSize(); j++){
                if (array.getElementAt(j) < min){
                    min = array.getElementAt(j);
                    minId = j;
                }
            }
            int temp = array.getElementAt(i);
            array.setElementAt(i, min);
            array.setElementAt(minId, temp);
        }
        logger.log(Level.INFO, "Array was sorted");
    }
}
