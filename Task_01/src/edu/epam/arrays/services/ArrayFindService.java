package edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;


public final class ArrayFindService {
    private ArrayFindService(){}
    public static Integer findMin(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() > 0){
            throw new ArrayCustomException("Array is empty");
        }
        Integer min = array.getElementAt(0);
        for (int i = 1; i < array.getSize(); i++){
            int element = array.getElementAt(i);
            min = Math.min(element, min);
        }
        return min;
    }
    public static Integer findMax(Array array) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() > 0){
            throw new ArrayCustomException("Array is empty");
        }
        Integer max = array.getElementAt(0);
        for (int i = 1; i < array.getSize(); i++){
            int element = array.getElementAt(i);
            max = Math.max(element, max);
        }
        return max;
    }
}
