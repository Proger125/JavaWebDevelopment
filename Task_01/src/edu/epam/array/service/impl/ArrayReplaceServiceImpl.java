package edu.epam.array.service.impl;

import edu.epam.array.entity.IntArray;
import edu.epam.array.exception.ArrayCustomException;
import edu.epam.array.expression.ArrayFunctionExpression;
import edu.epam.array.service.ArrayReplaceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayReplaceServiceImpl implements ArrayReplaceService {
    static Logger logger = LogManager.getLogger();
    public void replaceElementsByExpression(IntArray array, ArrayFunctionExpression expression, Integer replacement) throws ArrayCustomException {
        if (array == null){
            logger.log(Level.ERROR, "Array is null");
            throw new ArrayCustomException("Array is null");
        }
        if (array.getSize() == 0){
            logger.log(Level.ERROR, "Array is empty");
            throw new ArrayCustomException("Array is empty");
        }
        for (int i = 0; i < array.getSize(); i++){
            if (expression.isEqual(array.getElementAt(i))){
                array.setElementAt(i, replacement);
            }
        }
        logger.log(Level.INFO, "All replacements have been made");
    }
}
