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

public class InformationHandlingServiceTest {
    private static final String FILENAME = "src/test/resources/data.txt";
    private TextComponent root = new TextComposite(ComponentType.TEXT);
    private TextService service = new TextServiceImpl();
    private String text;
    @BeforeClass
    public void readData() throws HandlerException {
        TextReader reader = new TextReader();
        text = reader.read(FILENAME);

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
    public void AmountOfConsonantsTest() throws HandlerException {
        TextComponent sentence = root.getChild(2).getChild(0);
        int actual = service.amountOfConsonants(sentence);
        int expected = 33;
        assertEquals(actual, expected);
    }
    @Test(expectedExceptions = HandlerException.class)
    public void AmountOfConsonantsTestException() throws HandlerException {
        TextComponent paragraph = root.getChild(1);
        int actual = service.amountOfConsonants(paragraph);
    }
    @Test
    public void AmountOfVowelsTest() throws HandlerException {
        TextComponent sentence = root.getChild(2).getChild(0);
        int actual = service.amountOfVowels(sentence);
        int expected = 21;
        assertEquals(actual, expected);
    }
    @Test
    public void AmountOfVowelsTestFalse() throws HandlerException {
        TextComponent sentence = root.getChild(2).getChild(1);
        int actual = service.amountOfVowels(sentence);
        int expected = 21;
        assertNotEquals(actual, expected);
    }
    @Test
    public void deleteSentencesTest() throws HandlerException {
        service.deleteSentencesWithAmountOfWordsLessThan(root, 3);
        String expected = "The Right Honourable Sir Winston Leonard Spencer-Churchill, FRS (November 30, 1874 — January 24, 1965) was a British politician, best known as Prime Minister of the United Kingdom during World War II. At various times an author, soldier, journalist, legislator and painter, Churchill is generally regarded as one of the most important leaders in British and world history.\n" +
                "Winston Churchill was born at Blenheim Palace, near Woodstock in Oxfordshire. Winston’s father, Lord Randolph Churchill, was a politician. Winston’s mother, Lady Randolph Churchill of Brooklyn, New York, was a daughter of American millionaire Leonard Jerome. As the son of a prominent politician, it was unsurprising that Churchill was soon drawn into politics himself.\n" +
                "He started speaking at a number of Conservative meetings in the 1890s. In the 1906 general election, Churchill won a seat in Manchester. He served as Under Secretary of State for the Colonies. Churchill soon became the most prominent member of the Government. At the outbreak of the Second World War Churchill was appointed First Lord of the Admiralty. He was an early supporter of the pan-Europeanism that led to the formation of the European Common market and later the European Union (for which one of the three main buildings of the European Parliament is named in his honour).\n" +
                "Miscellany — In 1953 he was awarded two major honours. He was knighted and became Sir Winston Churchill and he was awarded the Nobel Prize for Literature \"for his mastery of historical and biographical description as well as for brilliant oratory in defending exalted human values. He was named Time Magazine \"Man of the Halt-Century\" in the early 1950s. In 1959 Churchill inherited the title of Father of the House. He became the MP with the longest continuous service — since 1924.\n" +
                "Churchill College, a constituent college of the University of Cambridge, was founded in 1960 as the national and commonwealth memorial to Winston Churchill. Churchill was voted as \"The Greatest Briton\" in 2002 \"100 Greatest Britons\" poll sponsored by the BBC and voted for by the public.";
        assertEquals(root.toString(), expected);
    }
    @AfterClass
    public void tearDown(){
        text = null;
    }
}
