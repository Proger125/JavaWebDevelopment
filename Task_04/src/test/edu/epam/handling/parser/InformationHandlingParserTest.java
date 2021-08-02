package test.edu.epam.handling.parser;

import edu.epam.handling.composite.ComponentType;
import edu.epam.handling.composite.TextComponent;
import edu.epam.handling.composite.impl.TextComposite;
import edu.epam.handling.exception.HandlerException;
import edu.epam.handling.parser.impl.*;
import org.testng.annotations.Test;

import java.io.File;

import static org.testng.Assert.assertEquals;

public class InformationHandlingParserTest {
    @Test
    public void parseTextTest() throws HandlerException {
        String string = "John and I have lived at our present home almost sixteen years and have had at least twelve thousand guests tor dinner. It’s not something I wish to brag about. It is something I have been privileged to do. From family dinners and festive occasions to bridal showers and wedding receptions, we have enjoyed them tremendously. The number of people at these occasions has ranged from one to three hundred at a time. Our goal has always been to make people feel welcomed when they arrive and satisfied when they leave.\n" +
                "The menus I’ve planned for various gatherings were simple crowd pleasers. I used lots of hamburger, spaghetti and meat sauce, tacos with trimmings, chili, and beans. These meals were economical and easy to prepare. I can repeat them as often as I want because we have had different people every time.";
        String expected = "John and I have lived at our present home almost sixteen years and have had at least twelve thousand guests tor dinner. It’s not something I wish to brag about. It is something I have been privileged to do. From family dinners and festive occasions to bridal showers and wedding receptions, we have enjoyed them tremendously. The number of people at these occasions has ranged from one to three hundred at a time. Our goal has always been to make people feel welcomed when they arrive and satisfied when they leave.\n" +
                "The menus I’ve planned for various gatherings were simple crowd pleasers. I used lots of hamburger, spaghetti and meat sauce, tacos with trimmings, chili, and beans. These meals were economical and easy to prepare. I can repeat them as often as I want because we have had different people every time.";
        TextComponent component = new TextComposite(ComponentType.TEXT);
        TextParser textParser = new TextParser();
        ParagraphParser paragraphParser = new ParagraphParser();
        SentenceParser sentenceParser = new SentenceParser();
        LexemeParser lexemeParser = new LexemeParser();
        WordParser wordParser = new WordParser();

        textParser.setNext(paragraphParser);
        paragraphParser.setNext(sentenceParser);
        sentenceParser.setNext(lexemeParser);
        lexemeParser.setNext(wordParser);

        textParser.processData(string, component);
        String actual = component.toString();
        assertEquals(actual, expected);
    }
    @Test
    public void test(){
        File file = new File("./");
        for (var sub : file.listFiles()){
            System.out.println(sub);
        }
    }
}
