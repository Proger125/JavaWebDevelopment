package edu.epam.handling.parser;

import edu.epam.handling.composite.Component;
import edu.epam.handling.exception.HandlerException;

public interface ChainParser {
    void setNext(ChainParser parser);
    void processData(String text, Component component) throws HandlerException;
}
