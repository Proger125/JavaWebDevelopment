package edu.epam.parsing.parser;

import edu.epam.parsing.gemException.GemException;

public class GemBuilderFactory {
    public GemBuilder createGemBuilder(String type) throws GemException {
        GemBuilder builder;
        switch (type){
            case "SAX":
                builder = new GemSAXBuilder();
                break;
            case "DOM":
                builder =  new GemDOMBuilder();
                break;
            case "Stax":
                builder = new GemStaxBuilder();
                break;
            default:
                throw new GemException("Incorrect parser type");
        }
        return builder;
    }
}
