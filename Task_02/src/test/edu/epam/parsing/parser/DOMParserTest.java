package test.edu.epam.parsing.parser;

import edu.epam.parsing.entity.*;
import edu.epam.parsing.gemException.GemException;
import edu.epam.parsing.parser.GemBuilderFactory;
import edu.epam.parsing.parser.GemDOMBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.Set;

import static org.testng.Assert.*;

public class DOMParserTest {
    private Set<Gem> gems;
    private final static String FILE_NAME = "resources/gems.xml";
    @BeforeClass
    public void setUp() throws GemException {
        GemBuilderFactory factory = new GemBuilderFactory();
        GemDOMBuilder builder = (GemDOMBuilder) factory.createGemBuilder("DOM");
        builder.buildSetGems(FILE_NAME);
        gems = builder.getGems();
    }
    @AfterClass
    public void tearDown(){
        gems = null;
    }
    @Test
    public void GemDOMParserLengthTest(){
        int actual = gems.size();
        int expected = 16;
        assertEquals(actual, expected);
    }
    @Test
    public void GemDOMParserContainsTestTrue(){
        ArtificialGem gem = new ArtificialGem();
        gem.setId("g4");
        gem.setName("Artificial Diamond");
        gem.setPreciousness(Preciousness.PRECIOUS);
        gem.getParameters().setColor("Blue");
        gem.getParameters().setTransparency(97);
        gem.getParameters().setEdgeAmount(4);
        gem.setCreationDate(YearMonth.parse("2018-06"));
        gem.setGrowingTime(500);
        gem.setWeight(8);
        boolean actual = gems.contains(gem);
        assertTrue(actual);
    }
    @Test
    public void GemDomParserContainsTest2True(){
        NaturalGem gem = new NaturalGem();
        gem.setId("g1");
        gem.setName("Diamond");
        gem.setPreciousness(Preciousness.PRECIOUS);
        gem.getParameters().setColor("Yellow");
        gem.getParameters().setTransparency(80);
        gem.getParameters().setEdgeAmount(6);
        gem.setCreationDate(YearMonth.parse("2021-03"));
        gem.setPlace(ExtractionPlace.AFRICA);
        gem.setWeight(5);
        boolean actual = gems.contains(gem);
        assertTrue(actual);
    }
    @Test
    public void GemDOMParserContainsTestFalse(){
        ArtificialGem gem = new ArtificialGem();
        gem.setId("g4");
        gem.setName("Artificial Diamond");
        gem.setPreciousness(Preciousness.PRECIOUS);
        gem.getParameters().setColor("Yellow");
        gem.getParameters().setTransparency(97);
        gem.getParameters().setEdgeAmount(4);
        gem.setCreationDate(YearMonth.parse("2018-06"));
        gem.setGrowingTime(500);
        boolean actual = gems.contains(gem);
        assertFalse(actual);
    }
}
