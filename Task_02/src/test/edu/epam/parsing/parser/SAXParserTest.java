package test.edu.epam.parsing.parser;

import edu.epam.parsing.entity.ArtificialGem;
import edu.epam.parsing.entity.Gem;
import edu.epam.parsing.entity.Preciousness;
import edu.epam.parsing.gemException.GemException;
import edu.epam.parsing.parser.GemBuilderFactory;
import edu.epam.parsing.parser.GemSAXBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.YearMonth;
import java.util.Set;

import static org.testng.Assert.*;

public class SAXParserTest {
    private final static String FILE_NAME = "resources/gems.xml";
    private Set<Gem> gems;
    @BeforeClass
    public void setUp() throws GemException {
        GemBuilderFactory factory = new GemBuilderFactory();
        GemSAXBuilder builder = (GemSAXBuilder) factory.createGemBuilder("SAX");
        builder.buildSetGems(FILE_NAME);
        gems = builder.getGems();
    }
    @AfterClass
    public void tearDown(){
        gems = null;
    }
    @Test
    public void GemSAXParserLengthTest() throws GemException {
        GemBuilderFactory factory = new GemBuilderFactory();
        GemSAXBuilder builder = (GemSAXBuilder) factory.createGemBuilder("SAX");
        builder.buildSetGems(FILE_NAME);
        int expected = 16;
        int actual = builder.getGems().size();
        assertEquals(actual, expected);
    }
    @Test
    public void GemSAXParserContainsTestTrue(){
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
    public void GemSAXParserContainsTestFalse(){
        ArtificialGem gem = new ArtificialGem();
        gem.setId("g5");
        gem.setName("Diamond");
        gem.setPreciousness(Preciousness.SEMIPRECIOUS);
        gem.getParameters().setColor("Green");
        gem.getParameters().setTransparency(97);
        gem.getParameters().setEdgeAmount(4);
        gem.setCreationDate(YearMonth.parse("2018-06"));
        gem.setGrowingTime(500);
        boolean actual = gems.contains(gem);
        assertFalse(actual);
    }
}
