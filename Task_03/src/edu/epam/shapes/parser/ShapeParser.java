package edu.epam.shapes.parser;

import edu.epam.shapes.exception.ShapeException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ShapeParser {
    private static Logger logger = LogManager.getLogger();
    private static final int AMOUNT_OF_ELEMENTS = 4;
    private static final int DEFAULT_VALUE = 1;
    public List<int[]> parseAllCorrectStrings(List<String> list) throws ShapeException {
        if (list == null){
            throw new ShapeException("List is null");
        }
        List<int[]> result = new ArrayList<>();
        for (String str : list){
            result.add(parseCorrectString(str));
        }
        return result;
    }
    public int[] parseCorrectString(String str){
        StringTokenizer tokenizer = new StringTokenizer(str);
        int count = tokenizer.countTokens();
        int[] result = new int[AMOUNT_OF_ELEMENTS];
        result[0] = Integer.parseInt(tokenizer.nextToken());
        result[1] = Integer.parseInt(tokenizer.nextToken());
        if (count == 2){
            result[2] = DEFAULT_VALUE;
            result[3] = DEFAULT_VALUE;
        }else if (count == 3){
            result[2] = Integer.parseInt(tokenizer.nextToken());
            result[3] = DEFAULT_VALUE;
        }else{
            result[2] = Integer.parseInt(tokenizer.nextToken());
            result[3] = Integer.parseInt(tokenizer.nextToken());
        }
        logger.log(Level.INFO, "String was parsed");
        return result;
    }
}
