package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.entity.TextComponent;
import by.epam.vladlitvin.entity.TextElement;
import by.epam.vladlitvin.type.TextElementType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SentenceParser extends AbstractParser {
    private final static String SENTENCE_REGEX =
            "(([\\'\\\"(]*)(([a-hk-zA-Z]*)([i|j]*)([a-hk-zA-Z]+)([-]?)([ij]*))+([)\\.\\?\\!,\\:\\;\\'\\\"]*))(([\\d\\+\\-\\*\\/()ij ]{3,})*)";

    private final static LexemeParser LEXEME_PARSER = new LexemeParser();
    private final static SymbolParser SYMBOL_PARSER = new SymbolParser();

    private final static int MATH_EXP_GROUP = 10;
    private final static int LEXEME_GROUP = 1;

    @Override
    public TextComponent handleParse(String sentence) {

        Pattern pattern = Pattern.compile(SENTENCE_REGEX, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(sentence);
        TextComponent component = new TextElement();

        while (matcher.find()) {
            String lexeme = matcher.group(LEXEME_GROUP);

            if(!lexeme.isEmpty()) {
                TextComponent lexemeComponent = LEXEME_PARSER.handleParse(lexeme);
                lexemeComponent.setElementType(TextElementType.LEXEME);
                component.add(lexemeComponent);
            }
            String mathExp = matcher.group(MATH_EXP_GROUP);

            if(!mathExp.isEmpty()) {
                TextComponent mathExpCompnent = SYMBOL_PARSER.handleParse(mathExp.trim());
                mathExpCompnent.setElementType(TextElementType.MATH_EXP);
                component.add(mathExpCompnent);
            }
        }
        return component;

    }

}
