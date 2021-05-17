package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.Component;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.Leaf;
import edu.epam.handling.exception.HandlerException;
import edu.epam.handling.parser.ChainParser;



public class WordParser implements ChainParser {
    @Override
    public void setNext(ChainParser parser) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void processData(String text, Component component) throws HandlerException {
        ComponentType type = component.getType();
        if (type == ComponentType.WORD){
            char[] chars = text.toCharArray();
            for (var c : chars){
                component.add(new Leaf(c, ComponentType.LETTER));
            }
        }else{
            throw new HandlerException("Incorrect composite");
        }
    }
}
