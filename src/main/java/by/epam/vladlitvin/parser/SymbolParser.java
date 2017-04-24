package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.entity.TextComponent;
import by.epam.vladlitvin.entity.Symbol;
import by.epam.vladlitvin.entity.TextElement;
import by.epam.vladlitvin.type.TextElementType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vlad_ on 4/17/2017.
 */
public class SymbolParser extends AbstractParser {
    @Override
    public TextComponent handleParse(String inPut) {
        TextComponent component = new TextElement();

        for (char sumbol: inPut.toCharArray()) {
            component.add(new Symbol(sumbol));
        }
        return component;
    }

}