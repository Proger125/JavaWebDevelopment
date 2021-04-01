package edu.epam.array.service;

import edu.epam.array.entity.IntArray;
import edu.epam.array.exception.ArrayCustomException;
import edu.epam.array.expression.ArrayFunctionExpression;

public interface ArrayReplaceService {
    void replaceElementsByExpression(IntArray array, ArrayFunctionExpression expression, Integer replacement) throws ArrayCustomException;
}
