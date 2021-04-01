package edu.epam.array.service;

import edu.epam.array.entity.IntArray;
import edu.epam.array.exception.ArrayCustomException;
import edu.epam.array.expression.ArrayFunctionExpression;

import java.util.function.IntPredicate;

public interface ArrayDefineService {
    Integer sum(IntArray array) throws ArrayCustomException;
    Integer sumStream(IntArray array) throws ArrayCustomException;
    Integer countElementsByExpression(IntArray array, ArrayFunctionExpression expression) throws ArrayCustomException;
    Long countElementsByExpressionStream(IntArray array, IntPredicate predicate) throws ArrayCustomException;
    Integer defineAverageElement(IntArray array) throws ArrayCustomException;
    Double defineAverageInArrayStream(IntArray array) throws ArrayCustomException;
}
