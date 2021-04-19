package edu.epam.parsing.parser;

import edu.epam.parsing.gemException.GemException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GemBuilderFactory {
    static Logger logger = LogManager.getLogger();
    public GemBuilder createGemBuilder(String type) throws GemException {
        GemBuilder builder;
        switch (type){
            case "SAX":
                builder = new GemSAXBuilder();
                logger.log(Level.INFO, "SAX builder was created");
                break;
            case "DOM":
                builder =  new GemDOMBuilder();
                logger.log(Level.INFO, "DOM builder was created");
                break;
            case "Stax":
                builder = new GemStaxBuilder();
                logger.log(Level.INFO, "Stax builder was created");
                break;
            default:
                logger.log(Level.ERROR, "Incorrect parser type");
                throw new GemException("Incorrect parser type");
        }
        return builder;
    }
}
