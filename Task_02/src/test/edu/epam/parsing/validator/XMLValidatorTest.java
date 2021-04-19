package test.edu.epam.parsing.validator;

import edu.epam.parsing.gemException.GemException;
import edu.epam.parsing.validator.XmlValidator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class XMLValidatorTest {
    @Test
    public void validateXMLTestTrue() throws GemException {
        boolean actual = XmlValidator.validateXML("resources/gems.xml");
        assertTrue(actual);
    }
    @Test
    public void validateXMLTestFalse() throws GemException {
        boolean actual = XmlValidator.validateXML("resources/gems_incorrect.xml");
        assertFalse(actual);
    }
}
