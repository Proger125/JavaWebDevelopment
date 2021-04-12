package edu.epam.parsing.parser;

import edu.epam.parsing.GemException.GemException;
import edu.epam.parsing.entity.Gem;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class GemsSAXBuilder extends GemBuilder{
    private GemHandler handler;
    private XMLReader reader;
    public GemsSAXBuilder() throws GemException {
        handler = new GemHandler();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        }catch (SAXException e){
            throw new GemException("Problems with parsing...");
        }
    }
    public void buildSetGems(String filename) throws GemException {
        try {
            reader.parse(filename);
        }catch (SAXException e){
            throw new GemException("Problems with parsing...");
        }catch (IOException e){
            throw new GemException("Problems with file...");
        }
        gems = handler.getGems();
    }
}
