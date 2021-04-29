package edu.epam.shapes.parser;

import java.util.StringTokenizer;

public class ShapeParser {
    private static final int AMOUNT_OF_ELEMENTS = 4;
    private static final int DEFAULT_VALUE = 1;
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
        return result;
    }
}
