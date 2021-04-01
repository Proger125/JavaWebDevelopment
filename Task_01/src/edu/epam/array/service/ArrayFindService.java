package edu.epam.array.service;

import edu.epam.array.entity.IntArray;
import edu.epam.array.exception.ArrayCustomException;

public interface ArrayFindService {
    Integer findMin(IntArray array) throws ArrayCustomException;
    Integer findMax(IntArray array) throws ArrayCustomException;
    Integer findMinStream(IntArray array) throws ArrayCustomException;
    Integer findMaxStream(IntArray array) throws ArrayCustomException;
}
