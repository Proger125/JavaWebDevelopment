package edu.epam.shapes.validator;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {
    private static final String NATURAL_REGEXP = "^(\\+?[1-9][0-9]*)$";

    public static boolean validateString(String str, String separator){
        StringTokenizer tokenizer = new StringTokenizer(str, separator);
        Pattern naturalPattern = Pattern.compile(NATURAL_REGEXP);
        int count = tokenizer.countTokens();
        boolean flag = true;
        if (count == 1) {
            flag =  false;
        }else if (count == 2){
            if (!PointValidator.validatePoint(tokenizer.nextToken(), tokenizer.nextToken())){
                flag = false;
            }
        }else if (count == 3){
            boolean isPointValidated = PointValidator.validatePoint(tokenizer.nextToken(), tokenizer.nextToken());
            Matcher matcher = naturalPattern.matcher(tokenizer.nextToken());
            if (!isPointValidated || !matcher.matches()){
                flag = false;
            }
        }else if (count == 4){
            boolean isPointValidated = PointValidator.validatePoint(tokenizer.nextToken(), tokenizer.nextToken());
            Matcher matcher1 = naturalPattern.matcher(tokenizer.nextToken());
            Matcher matcher2 = naturalPattern.matcher(tokenizer.nextToken());
            if (!isPointValidated || !matcher1.matches() || !matcher2.matches()){
                flag = false;
            }
        }else{
            flag = false;
        }
        return flag;
    }
}
