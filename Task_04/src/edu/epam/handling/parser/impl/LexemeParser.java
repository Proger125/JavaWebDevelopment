package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.Component;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.Composite;
import edu.epam.handling.composite.impl.Leaf;
import edu.epam.handling.parser.ChainParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements ChainParser {
    private ChainParser nextChain;
    private final static String WORD_REGEXP = "[a-zA-Z]+";
    private final static String PUNCTUATION_REGEXP = "\\p{P}";
    @Override
    public void setNext(ChainParser parser) {
        this.nextChain = parser;
    }

    @Override
    public void processData(String text, Component component) {
        ComponentType componentType = component.getType();
        if (componentType == ComponentType.LEXEME) {
            Pattern wordPattern = Pattern.compile(WORD_REGEXP);
            Matcher wordMatcher = wordPattern.matcher(text);
            while (wordMatcher.find()) {
                String word = text.substring(wordMatcher.start(), wordMatcher.end());
                Composite wordComposite = new Composite(ComponentType.WORD);
                component.add(wordComposite);
                nextChain.processData(word, wordComposite);
            }
            char[] charArray = text.toCharArray();
            for (char character : charArray) {
                if (Pattern.matches(PUNCTUATION_REGEXP, String.valueOf(character))) {
                    component.add(new Leaf(character, ComponentType.PUNCTUATION));
                }
            }


        } else {
            nextChain.processData(text, component);
        }
    }
}
