package test.edu.epam.parsing.parser;

import edu.epam.parsing.gemException.GemException;
import edu.epam.parsing.parser.GemBuilderFactory;
import edu.epam.parsing.parser.GemDOMBuilder;
import edu.epam.parsing.parser.GemSAXBuilder;
import edu.epam.parsing.parser.GemStaxBuilder;
import org.testng.annotations.Test;


public class ParserTests {
    private final static String FILE_NAME = "resources/gems.xml";
    private final static String INCORRECT_FILE_NAME ="res/gems.xml";
    @Test()
    public void GemDOMParserTest() throws GemException {
            GemBuilderFactory factory = new GemBuilderFactory();
            GemDOMBuilder builder = (GemDOMBuilder) factory.createGemBuilder("DOM");
            builder.buildSetGems(FILE_NAME);
    }
    @Test(expectedExceptions = GemException.class)
    public void GemDOMParserTestException() throws GemException {
        GemBuilderFactory factory = new GemBuilderFactory();
        GemDOMBuilder builder = (GemDOMBuilder) factory.createGemBuilder("DOM");
        builder.buildSetGems(INCORRECT_FILE_NAME);
    }
    @Test
    public void GemSAXParserTest() throws GemException {
            GemBuilderFactory factory = new GemBuilderFactory();
            GemSAXBuilder builder = (GemSAXBuilder) factory.createGemBuilder("SAX");
            builder.buildSetGems(FILE_NAME);
    }
    @Test(expectedExceptions = GemException.class)
    public void GemSAXParserTestException() throws GemException {
        GemBuilderFactory factory = new GemBuilderFactory();
        GemSAXBuilder builder = (GemSAXBuilder) factory.createGemBuilder("SAX");
        builder.buildSetGems(INCORRECT_FILE_NAME);
    }
    @Test
    public void GemStaxParserTest() throws GemException {
            GemBuilderFactory factory = new GemBuilderFactory();
            GemStaxBuilder builder = (GemStaxBuilder) factory.createGemBuilder("Stax");
            builder.buildSetGems(FILE_NAME);
    }
    @Test(expectedExceptions = GemException.class)
    public void GemStaxParserTestException() throws GemException {
        GemBuilderFactory factory = new GemBuilderFactory();
        GemStaxBuilder builder = (GemStaxBuilder) factory.createGemBuilder("Stax");
        builder.buildSetGems(INCORRECT_FILE_NAME);
    }
}
