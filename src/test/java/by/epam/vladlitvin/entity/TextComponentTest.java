package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.calculater.ExpCalculater;
import by.epam.vladlitvin.parser.AbstractParser;
import by.epam.vladlitvin.parser.TextParser;
import by.epam.vladlitvin.reader.TextReader;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
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
        String result = component.getElement();

        new ExpCalculater().calcuateMathExpInText(component, 2, 5);
        System.out.println(component.getElement());
    }

}