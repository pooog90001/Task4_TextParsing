package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.handler.TextHandler;
import by.epam.vladlitvin.parser.TextParser;
import by.epam.vladlitvin.reader.TextReader;
import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


/**
 * Created by vlad_ on 4/19/2017.
 */
public class TextComponentTest extends TestCase {
    public void testAdd() throws Exception {
        String file = TextReader.readTextFromFile("resource//inPut.txt");
        TextParser parser = new TextParser();
        TextComponent component = parser.chain(file);
        TextHandler handler = new TextHandler();
        handler.calcuateMathExpInText(component, 54, 5);
        System.out.println(component.getElement());
    }

}