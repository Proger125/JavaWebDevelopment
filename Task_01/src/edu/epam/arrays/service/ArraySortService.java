package edu.epam.arrays.service;

import edu.epam.arrays.entity.IntArray;
import edu.epam.arrays.exception.ArrayCustomException;

public interface ArraySortService {
    void bubbleSort(IntArray array) throws ArrayCustomException;
    void insertionSort(IntArray array) throws ArrayCustomException;
    void selectionSort(IntArray array) throws ArrayCustomException;
}
