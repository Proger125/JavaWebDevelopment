package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.HandlerException;
import edu.epam.handling.parser.ChainParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser implements ChainParser {
    private static Logger logger = LogManager.getLogger();
    private ChainParser nextChain;
    private final static String PARAGRAPH_REGEXP = "\n";
    private final static String ENTER_REGEXP = "\n";
    @Override
    public void setNext(ChainParser parser) {
        this.nextChain = parser;
    }

    @Override
    public void processData(String text, TextComponent textComponent) throws HandlerException {
        ComponentType type = textComponent.getType();
        if (type == ComponentType.TEXT){
            String[] paragraphs = text.split(PARAGRAPH_REGEXP);
            for (var paragraph : paragraphs){
                if (!paragraph.isEmpty()) {
                    //paragraph = paragraph.replace(ENTER_REGEXP, " ");
                    TextComposite paragraphTextComposite = new TextComposite(ComponentType.PARAGRAPH);
                    textComponent.add(paragraphTextComposite);
                    nextChain.processData(paragraph, paragraphTextComposite);
                }
            }
            logger.log(Level.INFO, "Text was parsed");
        }else{
            nextChain.processData(text, textComponent);
        }
    }
    public void parse(String text){
        int length = text.split(" ").length;
        int[] array = new int[length];
        int i = 0;
        String[] strings = text.split(" ");
        for (var element : strings){
            array[i] = Integer.parseInt(element);
            i++;
        }
    }
}
