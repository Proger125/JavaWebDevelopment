package edu.epam.arrays.service;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.expression.ArrayFunctionExpression;

public interface ArrayReplaceService {
    void replaceElementsByExpression(Array array, ArrayFunctionExpression expression, Integer replacement) throws ArrayCustomException;
}
