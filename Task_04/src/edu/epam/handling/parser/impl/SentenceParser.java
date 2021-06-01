package edu.epam.handling.parser.impl;

import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.HandlerException;
import edu.epam.handling.parser.ChainParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser implements ChainParser{
    private static Logger logger = LogManager.getLogger();
    private ChainParser nextChain;
    private static final String LEXEME_DELIMITER = "\s";

    @Override
    public void setNext(ChainParser parser) {
        this.nextChain = parser;
    }

    @Override
    public void processData(String text, TextComponent textComponent) throws HandlerException {
        ComponentType type = textComponent.getType();
        if (type == ComponentType.SENTENCE){
            String[] lexemes = text.split(LEXEME_DELIMITER);
            for (var lexeme : lexemes){
                if (!lexeme.isBlank()){
                    TextComposite lexemeTextComposite = new TextComposite(ComponentType.LEXEME);
                    textComponent.add(lexemeTextComposite);
                    nextChain.processData(lexeme, lexemeTextComposite);
                }
            }
            logger.log(Level.INFO, "Sentence was parsed");
        }else{
            nextChain.processData(text, textComponent);
        }
    }
}
