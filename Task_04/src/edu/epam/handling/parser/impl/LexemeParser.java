package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.composite.impl.Leaf;
import edu.epam.handling.exception.HandlerException;
import edu.epam.handling.parser.ChainParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements ChainParser {
    private static Logger logger = LogManager.getLogger();
    private ChainParser nextChain;
    private final static String WORD_REGEXP = "[a-zA-Z’]+";
    private final static String PUNCTUATION_REGEXP = "\\p{P}";
    @Override
    public void setNext(ChainParser parser) {
        this.nextChain = parser;
    }

    @Override
    public void processData(String text, TextComponent textComponent) throws HandlerException {
        ComponentType componentType = textComponent.getType();
        if (componentType == ComponentType.LEXEME) {
            char[] charArray = text.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (var character : charArray){
                if (Pattern.matches(PUNCTUATION_REGEXP, String.valueOf(character))){
                    if (!builder.isEmpty()){
                        TextComposite composite = new TextComposite(ComponentType.WORD);
                        textComponent.add(composite);
                        nextChain.processData(builder.toString(), composite);
                        builder.delete(0, builder.length());
                    }
                    textComponent.add(new Leaf(character, ComponentType.PUNCTUATION));
                }else{
                    builder.append(character);
                }
            }
            if (!builder.isEmpty()){
                TextComposite composite = new TextComposite(ComponentType.WORD);
                textComponent.add(composite);
                nextChain.processData(builder.toString(), composite);
            }
            logger.log(Level.INFO, "Lexeme was parsed");
        } else {
            nextChain.processData(text, textComponent);
        }
    }
}
