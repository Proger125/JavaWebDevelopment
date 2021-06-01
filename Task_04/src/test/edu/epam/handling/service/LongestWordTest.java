package test.edu.epam.handling.service;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.HandlerException;
import edu.epam.handling.parser.impl.*;
import edu.epam.handling.reader.TextReader;
import edu.epam.handling.service.TextService;
import edu.epam.handling.service.impl.TextServiceImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class LongestWordTest {
    private static final String FILENAME = "src/test/resources/words.txt";
    private String text;
    private TextComponent root;
    private TextService service;
    @BeforeClass
    public void setUp() throws HandlerException {
        service = new TextServiceImpl();
        TextReader reader = new TextReader();
        text = reader.read(FILENAME);

        root = new TextComposite(ComponentType.TEXT);
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();

        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(wordParser);

        textParser.processData(text, root);
    }
    @Test
    public void LongestWordTestTrue() throws HandlerException {
        String expected = "Hello Aleksandr! ";
        String actual = service.getSentencesWithLongestWord(root).get(0).toString();
        assertEquals(actual, expected);
    }
    @Test
    public void LongestWordTestFalse() throws HandlerException {
        String expected = " Hello Aleksandr!";
        String actual = service.getSentencesWithLongestWord(root).get(0).toString();
        assertNotEquals(actual, expected);
    }
    @Test(expectedExceptions = HandlerException.class)
    public void LongestWordTestException() throws HandlerException {
        String actual = service.getSentencesWithLongestWord(root.getChild(0)).get(0).toString();
    }
    @AfterClass
    public void tearDown(){
        text = null;
    }
}
