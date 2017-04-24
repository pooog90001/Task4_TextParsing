package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.entity.TextComponent;
import by.epam.vladlitvin.entity.TextElement;
import by.epam.vladlitvin.type.TextElementType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vlad_ on 4/17/2017.
 */
public class TextParser extends AbstractParser {
    private final static String PHARAGRAPH_REGEX = "(\\t)(.+?)(\\n|$)";
    private final static PharagraphParser PHARAGRAPH_PARSER = new PharagraphParser();

    @Override
    public TextComponent handleParse(String text) {
        Pattern pattern = Pattern.compile(PHARAGRAPH_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        TextComponent component = new TextElement(TextElementType.TEXT);
        while (matcher.find()) {
            String string = matcher.group();
            TextComponent subComponent = PHARAGRAPH_PARSER.handleParse(string);
            subComponent.setElementType(TextElementType.PARAGRAPH);
            component.add(subComponent);
        }
        return component;
    }


}
