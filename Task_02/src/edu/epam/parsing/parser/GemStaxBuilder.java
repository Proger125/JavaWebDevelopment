package edu.epam.parsing.parser;

import edu.epam.parsing.GemException.GemException;
import edu.epam.parsing.entity.ArtificialGem;
import edu.epam.parsing.entity.Gem;
import edu.epam.parsing.entity.NaturalGem;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GemStaxBuilder extends GemBuilder{
    private XMLInputFactory factory;
    public GemStaxBuilder(){
        factory = XMLInputFactory.newInstance();
    }
    public void buildSetGems(String filename) throws GemException {
        FileInputStream stream = null;
        XMLStreamReader reader = null;
        String name;
        try{
            stream = new FileInputStream(new File(filename));
            reader = factory.createXMLStreamReader(stream);
            while (reader.hasNext()){
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();
                    if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.NATURAL_GEM){
                        Gem gem = buildNaturalGem(reader);
                        gems.add(gem);
                    }else if (GemEnum.valueOf(name.toUpperCase()) == GemEnum.ARTIFICIAL_GEM){
                        Gem gem = buildArtificialGem(reader);
                        gems.add(gem);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            try {
                if (stream != null){
                    stream.close();
                }
            } catch (IOException exception) {
                throw new GemException("File not found...");
            }
            throw new GemException("Problems with file...");
        } catch (XMLStreamException e) {
            try {
                if (stream != null){
                    stream.close();
                }
            } catch (IOException exception) {
                throw new GemException("File not found...");
            }
            throw new GemException("Problems with parsing...");
        }
    }
    private NaturalGem buildNaturalGem(XMLStreamReader reader){

    }
    private ArtificialGem buildArtificialGem(XMLStreamReader reader){

    }
    private void buildGem(){

    }
}
