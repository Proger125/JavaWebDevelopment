package edu.epam.parsing.parser;

import edu.epam.parsing.gemException.GemException;
import edu.epam.parsing.entity.ArtificialGem;
import edu.epam.parsing.entity.Gem;
import edu.epam.parsing.entity.NaturalGem;
import edu.epam.parsing.entity.ExtractionPlace;
import edu.epam.parsing.entity.Preciousness;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;

public class GemStaxBuilder extends GemBuilder{
    private XMLInputFactory factory;
    public GemStaxBuilder(){
        factory = XMLInputFactory.newInstance();
    }
    @Override
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
                    if (GemEnum.valueOf(name.toUpperCase().replace('-', '_')) == GemEnum.NATURAL_GEM){
                        Gem gem = buildNaturalGem(reader);
                        gems.add(gem);
                    }else if (GemEnum.valueOf(name.toUpperCase().replace('-', '_')) == GemEnum.ARTIFICIAL_GEM){
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
                throw new GemException("Impossible close file " + filename);
            }
            throw new GemException("Problems with file...");
        } catch (XMLStreamException e) {
            try {
                if (stream != null){
                    stream.close();
                }
            } catch (IOException exception) {
                throw new GemException("Impossible close file " + filename);
            }
            throw new GemException("Problems with parsing...");
        }
    }
    private NaturalGem buildNaturalGem(XMLStreamReader reader) throws XMLStreamException, GemException {
        NaturalGem gem = new NaturalGem();
        buildGem(gem, reader);
        String name;
        while (reader.hasNext()){
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT){
                name = reader.getLocalName();
                if(GemEnum.valueOf(name.toUpperCase().replace('-', '_')) == GemEnum.EXTRACTION_PLACE){
                    gem.setPlace(ExtractionPlace.valueOf(getXMLText(reader).toUpperCase().replace('-', '_')));
                }
            }else if (type == XMLStreamConstants.END_ELEMENT){
                name = reader.getLocalName();
                if (GemEnum.valueOf(name.toUpperCase().replace('-','_')) == GemEnum.NATURAL_GEM){
                    return gem;
                }
            }
        }
        throw new GemException("Unknown element in tag");
    }
    private ArtificialGem buildArtificialGem(XMLStreamReader reader) throws XMLStreamException, GemException {
        ArtificialGem gem = new ArtificialGem();
        buildGem(gem, reader);
        String name;
        while (reader.hasNext()){
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT){
                name = reader.getLocalName();
                if(GemEnum.valueOf(name.toUpperCase().replace('-', '_')) == GemEnum.GROWING_TIME){
                    gem.setGrowingTime(Integer.parseInt(getXMLText(reader)));
                }
            }else if (type == XMLStreamConstants.END_ELEMENT){
                name = reader.getLocalName();
                if (GemEnum.valueOf(name.toUpperCase().replace('-','_')) == GemEnum.ARTIFICIAL_GEM){
                    return gem;
                }
            }
        }
        throw new GemException("Unknown element in tag");
    }
    private void buildGem(Gem gem, XMLStreamReader reader) throws XMLStreamException, GemException {
        if (reader.getAttributeCount() == 1){
            gem.setId(reader.getAttributeValue(0));
        }else if ("id".equals(reader.getAttributeLocalName(0))){
            gem.setId(reader.getAttributeValue(0));
            gem.setWeight(Integer.parseInt(reader.getAttributeValue(1)));
        }else{
            gem.setId(reader.getAttributeValue(1));
            gem.setWeight(Integer.parseInt(reader.getAttributeValue(0)));
        }
        String name;
        while (reader.hasNext()){
            int type = reader.next();
            if (type == XMLStreamConstants.START_ELEMENT) {
                name = reader.getLocalName();
                boolean flag = false;
                switch (GemEnum.valueOf(name.toUpperCase().replace('-', '_'))) {
                    case NAME -> gem.setName(getXMLText(reader));
                    case PRECIOUSNESS -> gem.setPreciousness(Preciousness.valueOf(getXMLText(reader).toUpperCase()));
                    case CREATION_DATE -> {
                        gem.setCreationDate(YearMonth.parse(getXMLText(reader)));
                        flag = true;
                    }
                    case COLOR -> gem.getParameters().setColor(getXMLText(reader));
                    case TRANSPARENCY -> gem.getParameters().setTransparency(Integer.parseInt(getXMLText(reader)));
                    case EDGE_AMOUNT -> gem.getParameters().setEdgeAmount(Integer.parseInt(getXMLText(reader)));
                }
                if (flag){
                    break;
                }
            }
        }
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
