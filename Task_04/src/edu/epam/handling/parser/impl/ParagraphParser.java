package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.HandlerException;
import edu.epam.handling.parser.ChainParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser implements ChainParser{
    private static Logger logger = LogManager.getLogger();
    private ChainParser nextChain;
    private final static String SENTENCE_DELIMITER ="[…!?\\.]";
    @Override
    public void setNext(ChainParser parser) {
        this.nextChain = parser;
    }

    @Override
    public void processData(String text, TextComponent textComponent) throws HandlerException {
        ComponentType type = textComponent.getType();
        if (type == ComponentType.PARAGRAPH){
            Pattern sentencePattern = Pattern.compile(SENTENCE_DELIMITER);
            Matcher sentenceMatcher = sentencePattern.matcher(text);
            int index = 0;
            while (sentenceMatcher.find()) {
                String sentence = text.substring(index, sentenceMatcher.end());
                index = sentenceMatcher.end();
                TextComposite sentenceTextComposite = new TextComposite(ComponentType.SENTENCE);
                textComponent.add(sentenceTextComposite);
                nextChain.processData(sentence, sentenceTextComposite);
            }
            logger.log(Level.INFO, "Paragraph was parsed");
        }else{
            nextChain.processData(text, textComponent);
        }
    }
}
