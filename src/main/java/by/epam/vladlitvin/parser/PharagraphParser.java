package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.entity.TextComponent;
import by.epam.vladlitvin.entity.TextElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.vladlitvin.type.TextElementType.SENTENCE;
import static by.epam.vladlitvin.type.TextElementType.TEXT;


public class PharagraphParser extends AbstractParser {
    private final static String SENTENCE_REGEX = ".+?(\\.|\\?|\\!)";
    private final static SentenceParser SENTENCE_PARSER = new SentenceParser();
    @Override
    public TextComponent handleParse(String pharagraph) {

        Pattern pattern = Pattern.compile(SENTENCE_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(pharagraph);
        TextComponent component = new TextElement();

        while (matcher.find()) {
            String string = matcher.group();
            TextComponent subComponent = SENTENCE_PARSER.handleParse(string);
            subComponent.setElementType(SENTENCE);
            component.add(subComponent);
        }

        return component;
    }

}
