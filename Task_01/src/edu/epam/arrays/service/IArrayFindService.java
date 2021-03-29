package edu.epam.arrays.service;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exception.ArrayCustomException;

public interface IArrayFindService {
    Integer findMin(Array array) throws ArrayCustomException;
    Integer findMax(Array array) throws ArrayCustomException;
}
