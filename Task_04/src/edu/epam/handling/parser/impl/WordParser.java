package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.Leaf;
import edu.epam.handling.exception.HandlerException;
import edu.epam.handling.parser.ChainParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WordParser implements ChainParser {
    private static Logger logger = LogManager.getLogger();
    @Override
    public void setNext(ChainParser parser) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void processData(String text, TextComponent textComponent) throws HandlerException {
        ComponentType type = textComponent.getType();
        if (type == ComponentType.WORD){
            char[] chars = text.toCharArray();
            for (var c : chars){
                textComponent.add(new Leaf(c, ComponentType.LETTER));
            }
            logger.log(Level.INFO, "Word was parsed");
        }else{
            throw new HandlerException("Incorrect composite");
        }
    }
}
