package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.entity.TextComponent;
import by.epam.vladlitvin.entity.TextElement;
import by.epam.vladlitvin.type.TextElementType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SentenceParser extends AbstractParser {
    private final static String SENTENCE_REGEX =
            "(([\\'\\\"]*)(([a-hk-zA-Z]*)([i|j]*)([a-hk-zA-Z]+)" +
                    "([i|j]*))+([\\.\\?\\!,\\:\\;\\'\\\"-]*))" +
                    "([\\d\\+\\-\\*\\/()ij ]{3,})*";

    @Override
    public TextComponent handleParse(String inPut) {

        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        TextComponent component = new TextElement();

        while (matcher.find()) {
            String group1 = matcher.group(1);

            if(!group1.isEmpty()) {
                TextComponent component1 = new LexemeParser().handleParse(group1);
                component1.setElementType(TextElementType.LEXEME);
                component.add(component1);
            }
            String group9 = matcher.group(7);

            if(!group9.isEmpty()) {
                TextComponent component9 = new SymbolParser().handleParse(group9);
                component9.setElementType(TextElementType.MATH_EXP);
                component.add(component9);
            }
        }
        return component;

    }

}
