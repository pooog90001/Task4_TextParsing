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
    private final String PHARAGRAPH_REGEX = "(\\t| {4}).+?($|\\n)";

    @Override
    public TextComponent handleParse(String inPut) {
        Pattern pattern = Pattern.compile(PHARAGRAPH_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        TextComponent component = new TextElement();
        while (matcher.find()) {
            String string = matcher.group();
            TextComponent subComponent = new PharagraphParser().handleParse(string);
            subComponent.setElementType(TextElementType.PARAGRAPH);
            component.add(subComponent);
        }
        return component;
    }


}