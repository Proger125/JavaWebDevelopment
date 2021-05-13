package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.Component;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.Composite;
import edu.epam.handling.parser.ChainParser;

public class SentenceParser implements ChainParser{
    private ChainParser nextChain;
    private static final String LEXEME_DELIMITER = "\s";

    @Override
    public void setNext(ChainParser parser) {
        this.nextChain = parser;
    }

    @Override
    public void processData(String text, Component component) {
        ComponentType type = component.getType();
        if (type == ComponentType.SENTENCE){
            String[] lexemes = text.split(LEXEME_DELIMITER);
            for (var lexeme : lexemes){
                if (!lexeme.isBlank()){
                    Composite lexemeComposite = new Composite(ComponentType.LEXEME);
                    component.add(lexemeComposite);
                    nextChain.processData(lexeme, lexemeComposite);
                }
            }
        }else{
            nextChain.processData(text, component);
        }
    }
}
