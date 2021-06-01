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

import java.util.HashMap;

import static org.testng.Assert.assertEquals;

public class AmountOfSimilarWordsTest {
    private static final String FILENAME = "src/test/resources/words.txt";
    private String text;
    private TextComponent root;
    private TextService service;
    private HashMap<String, Integer> map;
    @BeforeClass
    public void setUp() throws HandlerException {
        service = new TextServiceImpl();
        map = new HashMap<>();
        map.put("hello", 3);
        map.put("world", 2);
        map.put("alex", 1);
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
    public void SimilarWordsTest() throws HandlerException {
        HashMap<String, Integer> actual = service.amountOfSimilarWords(root);
        assertEquals(actual, map);
    }
    @Test(expectedExceptions = HandlerException.class)
    public void SimilarWordsTestException() throws HandlerException {
        HashMap<String, Integer> actual = service.amountOfSimilarWords(root.getChild(0));
    }
    @AfterClass
    public void tearDown(){
        text = null;
    }
}
