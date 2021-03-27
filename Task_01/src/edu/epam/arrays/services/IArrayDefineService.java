package edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.expressions.ArrayFunctionExpression;

public interface IArrayDefineService {
    Integer sum(Array array) throws ArrayCustomException;
    Integer countElementsByExpression(Array array, ArrayFunctionExpression expression) throws ArrayCustomException;
    Integer defineAverageElement(Array array) throws ArrayCustomException;
}
