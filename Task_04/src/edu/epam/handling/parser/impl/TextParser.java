package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.Component;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.Composite;
import edu.epam.handling.parser.ChainParser;

public class TextParser implements ChainParser {
    private ChainParser nextChain;
    private final static String PARAGRAPH_REGEXP = "\t|\s{4}";
    private final static String ENTER_REGEXP = "\n";
    @Override
    public void setNext(ChainParser parser) {
        this.nextChain = parser;
    }

    @Override
    public void processData(String text, Component component) {
        ComponentType type = component.getType();
        if (type == ComponentType.TEXT){
            String[] paragraphs = text.split(PARAGRAPH_REGEXP);
            for (var paragraph : paragraphs){
                if (!paragraph.isEmpty()) {
                    paragraph = paragraph.replace(ENTER_REGEXP, " ");
                    Composite paragraphComposite = new Composite(ComponentType.PARAGRAPH);
                    component.add(paragraphComposite);
                    nextChain.processData(paragraph, paragraphComposite);
                }
            }
        }else{
            nextChain.processData(text, component);
        }
    }
}
