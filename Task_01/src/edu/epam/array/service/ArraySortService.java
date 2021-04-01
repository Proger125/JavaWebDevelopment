package edu.epam.array.service;

import edu.epam.array.entity.IntArray;
import edu.epam.array.exception.ArrayCustomException;

public interface ArraySortService {
    void bubbleSort(IntArray array) throws ArrayCustomException;
    void insertionSort(IntArray array) throws ArrayCustomException;
    void selectionSort(IntArray array) throws ArrayCustomException;
}
