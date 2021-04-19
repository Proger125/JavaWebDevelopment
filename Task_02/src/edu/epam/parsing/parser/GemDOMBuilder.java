package edu.epam.parsing.parser;

import edu.epam.parsing.gemException.GemException;
import edu.epam.parsing.entity.ArtificialGem;
import edu.epam.parsing.entity.Gem;
import edu.epam.parsing.entity.NaturalGem;
import edu.epam.parsing.entity.ExtractionPlace;
import edu.epam.parsing.entity.Preciousness;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;

public class GemDOMBuilder extends GemBuilder{
    static Logger logger = LogManager.getLogger();
    private DocumentBuilder builder;
    public GemDOMBuilder() throws GemException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new GemException("Problems with parser");
        }
    }
    @Override
    public void buildSetGems(String filename) throws GemException {
        Document document = null;
        try{
            document = builder.parse(filename);
            Element root = document.getDocumentElement();
            NodeList naturalGemList = root.getElementsByTagName("natural-gem");
            for (int i = 0; i < naturalGemList.getLength(); i++){
                Element gemElement = (Element) naturalGemList.item(i);
                NaturalGem gem = buildNaturalGem(gemElement);
                gems.add(gem);
            }
            NodeList artificialGemList = root.getElementsByTagName("artificial-gem");
            for (int i = 0; i < artificialGemList.getLength(); i++){
                Element gemElement = (Element) artificialGemList.item(i);
                ArtificialGem gem = buildArtificialGem(gemElement);
                gems.add(gem);
            }
            logger.log(Level.INFO, "Set of gems was created by DOM parser");
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Problems with parsing in DOM parser");
            throw new GemException("Problems with parsing...");
        } catch (IOException exception) {
            logger.log(Level.ERROR, "Problems with file in DOM parser");
            throw new GemException("Problems with file...");
        }
    }
    private NaturalGem buildNaturalGem(Element gemElement){
        NaturalGem gem = new NaturalGem();
        buildGem(gem, gemElement);
        String extractionPlace = getElementTextContent(gemElement, "extraction-place").toUpperCase().replace('-', '_');
        gem.setPlace(ExtractionPlace.valueOf(extractionPlace));
        return gem;
    }
    private ArtificialGem buildArtificialGem(Element gemElement){
        ArtificialGem gem = new ArtificialGem();
        buildGem(gem, gemElement);
        gem.setGrowingTime(Integer.parseInt(getElementTextContent(gemElement, "growing-time")));
        return gem;
    }
    private void buildGem(Gem gem, Element gemElement){
        gem.setId(gemElement.getAttribute("id"));
        if (gemElement.hasAttribute("weight")){
            gem.setWeight(Integer.parseInt(gemElement.getAttribute("weight")));
        }
        gem.setName(getElementTextContent(gemElement, "name"));
        String preciousness = getElementTextContent(gemElement, "preciousness").toUpperCase();
        gem.setPreciousness(Preciousness.valueOf(preciousness));
        gem.setCreationDate(YearMonth.parse(getElementTextContent(gemElement, "creation-date")));
        Gem.VisualParameters parameters = gem.getParameters();
        Element parameter = (Element) gemElement.getElementsByTagName("visual-parameters").item(0);
        parameters.setColor(getElementTextContent(parameter, "color"));
        parameters.setEdgeAmount(Integer.parseInt(getElementTextContent(parameter, "edge-amount")));
        parameters.setTransparency(Integer.parseInt(getElementTextContent(parameter, "transparency")));
    }
    private static String getElementTextContent(Element element, String elementName){
        NodeList list = element.getElementsByTagName(elementName);
        Node node = list.item(0);
        String text = node.getTextContent();
        return text;
    }
}
