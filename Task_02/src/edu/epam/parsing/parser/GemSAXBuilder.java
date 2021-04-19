package edu.epam.parsing.parser;

import edu.epam.parsing.gemException.GemException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class GemSAXBuilder extends GemBuilder{
    private GemHandler handler;
    private XMLReader reader;
    public GemSAXBuilder() throws GemException {
        handler = new GemHandler();
        try{
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        }catch (SAXException e){
            throw new GemException("Problems with parsing...");
        }
    }
    @Override
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
