package edu.epam.arrays.service;

import edu.epam.arrays.entity.IntArray;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.expression.ArrayFunctionExpression;

public interface ArrayReplaceService {
    void replaceElementsByExpression(IntArray array, ArrayFunctionExpression expression, Integer replacement) throws ArrayCustomException;
}
