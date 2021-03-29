package edu.epam.arrays.service;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exception.ArrayCustomException;

public interface IArraySortService {
    void bubbleSort(Array array) throws ArrayCustomException;
    void insertionSort(Array array) throws ArrayCustomException;
    void selectionSort(Array array) throws ArrayCustomException;
}
