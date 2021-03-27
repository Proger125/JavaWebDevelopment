package edu.epam.arrays.services.impl;

import edu.epam.arrays.entity.Array;
import edu.epam.arrays.exceptions.ArrayCustomException;
import edu.epam.arrays.expressions.ArrayFunctionExpression;
import edu.epam.arrays.services.IArrayReplaceService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayReplaceService implements IArrayReplaceService {
    static Logger logger = LogManager.getLogger();
    public void replaceElementsByExpression(Array array, ArrayFunctionExpression expression, Integer replacement) throws ArrayCustomException {
        if (array == null){
            throw new ArrayCustomException("Array is null");        }
        if (array.getSize() > 0){
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
