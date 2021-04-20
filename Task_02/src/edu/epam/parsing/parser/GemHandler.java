package edu.epam.parsing.parser;

import edu.epam.parsing.entity.ArtificialGem;
import edu.epam.parsing.entity.Gem;
import edu.epam.parsing.entity.NaturalGem;
import edu.epam.parsing.entity.ExtractionPlace;
import edu.epam.parsing.entity.Preciousness;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class GemHandler extends DefaultHandler {
    private Set<Gem> gems;
    private Gem current = null;
    private GemEnum currentEnum = null;
    private EnumSet<GemEnum> withText;
    public GemHandler(){
        gems = new HashSet<>();
        withText = EnumSet.range(GemEnum.NAME, GemEnum.EDGE_AMOUNT);
    }
    public Set<Gem> getGems() {
        return gems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (GemEnum.NATURAL_GEM.getValue().equals(localName)){
            current = new NaturalGem();
            setAttributes(attributes, current);
        }else if (GemEnum.ARTIFICIAL_GEM.getValue().equals(localName)){
            current = new ArtificialGem();
            setAttributes(attributes, current);
        }else{
            GemEnum temp = GemEnum.valueOf(localName.toUpperCase().replace('-', '_'));
            if (withText.contains(temp)){
                currentEnum = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if (GemEnum.NATURAL_GEM.getValue().equals(localName) || GemEnum.ARTIFICIAL_GEM.getValue().equals(localName)){
            gems.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null){
            switch (currentEnum){
                case NAME -> current.setName(s);
                case PRECIOUSNESS -> current.setPreciousness(Preciousness.valueOf(s.toUpperCase()));
                case COLOR -> current.getParameters().setColor(s);
                case TRANSPARENCY -> current.getParameters().setTransparency(Integer.parseInt(s));
                case EDGE_AMOUNT -> current.getParameters().setEdgeAmount(Integer.parseInt(s));
                case CREATION_DATE -> current.setCreationDate(YearMonth.parse(s));
                case EXTRACTION_PLACE -> {
                    NaturalGem gem = (NaturalGem) current;
                    gem.setPlace(ExtractionPlace.valueOf(s.toUpperCase().replace('-', '_')));
                }
                case GROWING_TIME -> {
                    ArtificialGem gem = (ArtificialGem) current;
                    gem.setGrowingTime(Integer.parseInt(s));
                }
                default -> {
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
                }
            }
            currentEnum = null;
        }
    }

    private void setAttributes(Attributes attributes, Gem current){
        if (attributes.getLength() == 1){
            current.setId(attributes.getValue(0));
        }else{
            if (GemEnum.ID.getValue().equals(attributes.getLocalName(0))){
                current.setId(attributes.getValue(0));
                current.setWeight(Integer.parseInt(attributes.getValue(1)));
            }else{
                current.setWeight(Integer.parseInt(attributes.getValue(0)));
                current.setId(attributes.getValue(1));
            }
        }
    }
}
