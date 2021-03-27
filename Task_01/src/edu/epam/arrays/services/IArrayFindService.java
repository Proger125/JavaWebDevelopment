package edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;

public interface IArrayFindService {
    Integer findMin(Array array) throws ArrayCustomException;
    Integer findMax(Array array) throws ArrayCustomException;
}
