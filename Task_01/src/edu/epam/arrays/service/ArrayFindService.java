package edu.epam.arrays.service;

import edu.epam.arrays.entity.IntArray;
import edu.epam.arrays.exception.ArrayCustomException;

public interface ArrayFindService {
    Integer findMin(IntArray array) throws ArrayCustomException;
    Integer findMax(IntArray array) throws ArrayCustomException;
    Integer findMinStream(IntArray array) throws ArrayCustomException;
    Integer findMaxStream(IntArray array) throws ArrayCustomException;
}
