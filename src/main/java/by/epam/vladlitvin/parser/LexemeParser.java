package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.entity.Symbol;
import by.epam.vladlitvin.entity.TextComponent;
import by.epam.vladlitvin.entity.TextElement;
import by.epam.vladlitvin.type.TextElementType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vlad_ on 4/17/2017.
 */
public class LexemeParser extends AbstractParser {
    private static final String LEXEME_REGEX =
            "([\\'\\\"]*)(([a-hk-zA-Z]*)(\\w)+([\\.\\?\\!,\\:\\;\\'\\\"-]*)";
    @Override
    public TextComponent handleParse(String inPut) {

        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(inPut);
        TextComponent component = new TextElement();

        while (matcher.find()) {
            String group1 = matcher.group(1);

            if(!group1.isEmpty()) {
                TextComponent component1 = new SymbolParser().handleParse(group1);
                component1.setElementType(TextElementType.GROUP_SYMBOLS);
                component.add(component1);
            }
            String group2 = matcher.group(2);

            if(!group2.isEmpty()) {
                TextComponent component2 = new SymbolParser().handleParse(group2);
                component2.setElementType(TextElementType.WORD);
                component.add(component2);
            }
            String group7 = matcher.group(7);

            if(!group7.isEmpty()) {
                TextComponent component7 = new SymbolParser().handleParse(group7);
                component7.setElementType(TextElementType.GROUP_SYMBOLS);
                component.add(component7);
            }

        }
        return component;

    }


}
