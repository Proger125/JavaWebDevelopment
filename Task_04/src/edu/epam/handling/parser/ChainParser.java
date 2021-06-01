package edu.epam.handling.parser;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.exception.HandlerException;

public interface ChainParser {
    void setNext(ChainParser parser);
    void processData(String text, TextComponent textComponent) throws HandlerException;
}
