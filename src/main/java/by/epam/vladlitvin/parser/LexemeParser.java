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
            "([\\'\\\"]*)(\\w+)([\\.\\?\\!,\\:\\;\\'\\\"-]*)";
    private final static SymbolParser SYMBOL_PARSER = new SymbolParser();

    private final static int START_SYMBOLS_GROUP = 1;
    private final static int WORD_GROUP = 2;
    private final static int END_SYMBOLS_GROUP = 3;


    @Override
    public TextComponent handleParse(String inPut) {

        Pattern pattern = Pattern.compile(LEXEME_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(inPut);
        TextComponent component = new TextElement();

        while (matcher.find()) {
            String startSymbols = matcher.group(START_SYMBOLS_GROUP);

            if(!startSymbols.isEmpty()) {
                TextComponent startComponent = SYMBOL_PARSER.handleParse(startSymbols);
                startComponent.setElementType(TextElementType.GROUP_SYMBOLS);
                component.add(startComponent);
            }
            String word = matcher.group(WORD_GROUP);

            if(!word.isEmpty()) {
                TextComponent wordComponent = SYMBOL_PARSER.handleParse(word);
                wordComponent.setElementType(TextElementType.WORD);
                component.add(wordComponent);
            }
            String endSymbols = matcher.group(END_SYMBOLS_GROUP);

            if(!endSymbols.isEmpty()) {
                TextComponent endComponent = SYMBOL_PARSER.handleParse(endSymbols);
                endComponent.setElementType(TextElementType.GROUP_SYMBOLS);
                component.add(endComponent);
            }

        }
        return component;

    }


}
