package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.Component;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.Composite;
import edu.epam.handling.parser.ChainParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements ChainParser{
    private ChainParser nextChain;
    private final static String SENTENCE_DELIMITER ="[!?\\.]";
    @Override
    public void setNext(ChainParser parser) {
        this.nextChain = parser;
    }

    @Override
    public void processData(String text, Component component) {
        ComponentType type = component.getType();
        if (type == ComponentType.PARAGRAPH){
            Pattern sentencePattern = Pattern.compile(SENTENCE_DELIMITER);
            Matcher sentenceMatcher = sentencePattern.matcher(text);
            int index = 0;
            while (sentenceMatcher.find()) {
                String sentence = text.substring(index, sentenceMatcher.end());
                index = sentenceMatcher.end();
                Composite sentenceComposite = new Composite(ComponentType.SENTENCE);
                component.add(sentenceComposite);
                nextChain.processData(sentence, sentenceComposite);
            }
        }else{
            nextChain.processData(text, component);
        }
    }
}
