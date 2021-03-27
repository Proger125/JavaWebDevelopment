package edu.epam.arrays.services;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.expressions.ArrayFunctionExpression;

public interface IArrayReplaceService {
    void replaceElementsByExpression(Array array, ArrayFunctionExpression expression, Integer replacement) throws ArrayCustomException;
}
