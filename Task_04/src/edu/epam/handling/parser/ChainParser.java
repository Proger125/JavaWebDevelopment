package edu.epam.handling.parser;

import edu.epam.handling.composite.Component;

public interface ChainParser {
    void setNext(ChainParser parser);
    void processData(String text, Component component);
}
