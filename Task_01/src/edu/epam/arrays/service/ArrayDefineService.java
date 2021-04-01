package edu.epam.arrays.service;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exception.ArrayCustomException;
import edu.epam.arrays.expression.ArrayFunctionExpression;

import java.util.function.IntPredicate;

public interface ArrayDefineService {
    Integer sum(Array array) throws ArrayCustomException;
    Integer sumStream(Array array) throws ArrayCustomException;
    Integer countElementsByExpression(Array array, ArrayFunctionExpression expression) throws ArrayCustomException;
    Long countElementsByExpressionStream(Array array, IntPredicate predicate) throws ArrayCustomException;
    Integer defineAverageElement(Array array) throws ArrayCustomException;
    Double defineAverageInArrayStream(Array array) throws ArrayCustomException;
}
