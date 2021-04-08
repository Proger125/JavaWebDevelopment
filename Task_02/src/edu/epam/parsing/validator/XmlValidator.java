package edu.epam.parsing.validator;

import edu.epam.parsing.GemException.GemException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public final class XmlValidator {
    private static final String SCHEMA_FILE_NAME = "resources/gems.xsd";
    private XmlValidator(){}
    public static boolean validateXML(String fileName) throws GemException {
        File schemaFile = new File(SCHEMA_FILE_NAME);
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try{
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
        } catch (IOException exception) {
            throw new GemException("Problems with file...");
        } catch (SAXException e) {
            return false;
        }
        return true;
    }
}
