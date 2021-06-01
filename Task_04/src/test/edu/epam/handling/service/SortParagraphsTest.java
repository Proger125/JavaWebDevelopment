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

public class SortParagraphsTest {
    private static final String FILENAME = "src/test/resources/paragraphs.txt";
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
    public void SortParagraphsTestTrue() throws HandlerException {
        TextComponent component = service.sortParagraphsByAmountOfSentences(root);
        String expected = "In a nutshell, King’s guitar work has had a strong influence on thousands of guitar soloists to this day. King remains the blues’ greatest ambassador.\n"
                + "King’s long and distinguished career includes many musical collaborations. Young rock musicians, in particular, appreciate his contributions to their genre. In 1988 he played guitar and sang on the hit song \"When Love Comes to Town\" by the Irish band U2. In 2001 he recorded an award-winning record with Eric Clapton called \"Riding with the King\"’.\n"
                + "America’s music culture would be incomplete without blues music. Thought it was created in the early decades of the 20th century, blues music has had a huge influence on American popular music up to the present days. In fact, many key elements we hear in pop, soul, rhythm and blues, rock and roll, have their beginnings in blues music. It has never been the leader in music sales. Blues music has retained a significant presence not only in concerts and festivals throughout the United States, but in the daily life of every person on the planet, as well. One can hear the sound of the blues in unexpected places, from a television commercial to a new country or western song.";
        assertEquals(component.toString(), expected);
    }
    @Test(expectedExceptions = HandlerException.class)
    public void SortParagraphsTestException() throws HandlerException {
        TextComponent component = service.sortParagraphsByAmountOfSentences(root.getChild(0));
    }
    @AfterClass
    public void tearDown(){
        text = null;
        root = null;
        service = null;
    }
}
