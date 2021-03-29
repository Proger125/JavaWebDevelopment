package edu.epam.arrays.service;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.expression.ArrayFunctionExpression;

public interface IArrayDefineService {
    Integer sum(Array array) throws ArrayCustomException;
    Integer countElementsByExpression(Array array, ArrayFunctionExpression expression) throws ArrayCustomException;
    Integer defineAverageElement(Array array) throws ArrayCustomException;
}
