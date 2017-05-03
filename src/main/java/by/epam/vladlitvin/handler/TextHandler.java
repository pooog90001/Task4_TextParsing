package by.epam.vladlitvin.handler;

import by.epam.vladlitvin.entity.Symbol;
import by.epam.vladlitvin.entity.TextComponent;
import by.epam.vladlitvin.entity.TextElement;
import by.epam.vladlitvin.interpreter.Interpreper;
import by.epam.vladlitvin.type.TextElementType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vlad_ on 4/24/2017.
 */
public class TextHandler {
    private final static Logger LOGGER = LogManager.getLogger();

    private static final String MATH_EXPRESSION_REGEX = "((\\d+)|(\\+)|(-)|(\\*)|(\\/)|([(])|([)]))+";


    public void calcuateMathExpInText(TextComponent component, int i, int j) {

        ArrayDeque<TextComponent> mathExpressions = findExpressions(component);

        HashMap<String, Integer> params = new HashMap<String, Integer>(){{
            put("j", j);
            put("i", i);
        }};

        params.forEach((String k, Integer v) -> defineParamValue(mathExpressions, k, v));

        Interpreper interpreper = new Interpreper();

        for (TextComponent exp : mathExpressions) {
            String mathExpression = exp.getElement().replace(" ","");
            if (checkForValidExp(mathExpression)) {
                LOGGER.log(Level.INFO, mathExpression);
                Integer result = interpreper.calculateMathExp(mathExpression);
                replaceMathExp(exp, String.valueOf(result));
            }
        }


    }

    private ArrayDeque<TextComponent> findExpressions(TextComponent component) {

        ArrayDeque<TextComponent> mathExpressions = new ArrayDeque<>();

        for (TextComponent textComponent : component.getChildren()) {
            if (TextElementType.MATH_EXP.equals(textComponent.getElementType())) {
                mathExpressions.add(textComponent);

            } else if (!TextElementType.SYMBOL.equals(textComponent.getElementType())) {
                mathExpressions.addAll(findExpressions(textComponent));
            }
        }
        return mathExpressions;
    }

    private void defineParamValue(ArrayDeque<TextComponent> expressions, String paramName, int Value) {
        final String PARAMETER_REGEX = "(((\\+{2})|(-{2}))?(" + paramName + ")((\\+{2})|(-{2}))?)";

        Pattern pattern = Pattern.compile(PARAMETER_REGEX);

        for (TextComponent expression : expressions) {
            String stringExp = expression.getElement();
            Matcher matcher = pattern.matcher(stringExp);
            boolean flag = false;
            while (matcher.find()) {
                flag = true;

                String number = matcher.group(5);
                String prefixIncrement = matcher.group(3);
                String prefixDecrement = matcher.group(4);
                String postfixIncrement = matcher.group(7);
                String postfixDecrement = matcher.group(8);

                String increment = "[//+]{2}";
                String decrement = "[-]{2}";

                if (prefixIncrement != null) {
                    stringExp = stringExp.replaceFirst((increment + number), String.valueOf(++Value));

                } else if (prefixDecrement != null) {
                    stringExp = stringExp.replaceFirst((decrement + number), String.valueOf((--Value)));

                } else if (postfixIncrement != null) {
                    stringExp = stringExp.replaceFirst((number + increment), String.valueOf((Value++)));

                } else if (postfixDecrement != null) {
                    stringExp = stringExp.replaceFirst((number + decrement), String.valueOf((Value--)));

                } else  {
                    stringExp = stringExp.replaceFirst(number, String.valueOf((Value)));
                }
            }
            if (flag) {
                replaceMathExp(expression, stringExp);
            }

        }
    }

    private void replaceMathExp(TextComponent component, String replaceString) {
        component.removeAll();
        for (char symbol : replaceString.toCharArray()) {
            component.add(new Symbol(symbol));
        }
    }

    private boolean checkForValidExp(String exp) {
        Pattern pattern = Pattern.compile(MATH_EXPRESSION_REGEX);
        Matcher matcher = pattern.matcher(exp);
        return matcher.matches();
    }

    public void deleteLexeme(TextComponent component, char symbol, int length) {

        Iterator sentenceIterator = component.getChildren().iterator();

        while (sentenceIterator.hasNext()) {
            TextComponent sentence = (TextComponent) sentenceIterator.next();

            if (TextElementType.SENTENCE.equals(sentence.getElementType())) {
                Iterator<TextComponent> lexemeIterator = sentence.getChildren().iterator();

                while (lexemeIterator.hasNext()) {
                  TextComponent lexeme = lexemeIterator.next();

                  if (TextElementType.LEXEME.equals(lexeme.getElementType()) &&
                          (lexeme.getElement().toCharArray()[0] == symbol) &&
                          (lexeme.getElement().length() == length)) {
                      sentence.remove(lexeme);
                  }
                }
            } else if (!TextElementType.SYMBOL.equals(sentence.getElementType())) {
               deleteLexeme(sentence, symbol, length);
            }
        }
    }

    public void replaceLexeme(TextComponent component) {
        Iterator sentenceIterator = component.getChildren().iterator();
        int temporaryIndex = 0;
        TextComponent temporaryComponent = new TextElement();
        while (sentenceIterator.hasNext()) {
            TextComponent sentence = (TextComponent) sentenceIterator.next();

            if (TextElementType.SENTENCE.equals(sentence.getElementType())) {
                Iterator<TextComponent> lexemeIterator = sentence.getChildren().iterator();

                while (lexemeIterator.hasNext()) {
                    TextComponent lexeme = lexemeIterator.next();

                    if (TextElementType.LEXEME.equals(lexeme.getElementType())) {
                        temporaryIndex = sentence.getChildren().indexOf(lexeme);
                        temporaryComponent = lexeme;
                        break;
                    }
                }
                lexemeIterator = sentence.getChildren().descendingIterator();
                while (lexemeIterator.hasNext()) {
                    TextComponent lexeme = lexemeIterator.next();

                    if (TextElementType.LEXEME.equals(lexeme.getElementType())) {
                        int temporaryIndex2;
                        temporaryIndex2 = sentence.getChildren().indexOf(lexeme);
                        TextComponent temporaryComponent2 = sentence.getChildren().get(temporaryIndex);
                        sentence.getChildren().set(temporaryIndex, temporaryComponent);
                        sentence.getChildren().set(temporaryIndex2, temporaryComponent2);
                        break;
                    }
                }
            } else if (!TextElementType.SYMBOL.equals(sentence.getElementType())) {
                replaceLexeme(sentence);
            }
        }
    }

    public int countSentenceWithRepeatWord(TextComponent component) {

        ArrayList<TextComponent> sentences = new ArrayList<>();
        Iterator sentenceIterator = sentences.iterator();
        int result = 0;
        while (sentenceIterator.hasNext()) {
            TextComponent sentence = (TextComponent) sentenceIterator.next();
            ArrayList<TextComponent> words = findWords(sentence);
            result += hasRepeatWord(words)? 1 : 0;
        }
        return result;
    }

    public ArrayList<TextComponent> findSentence(TextComponent component) {
        ArrayList<TextComponent> result = new ArrayList<>();
        Iterator sentenceIterator = component.getChildren().iterator();
        while (sentenceIterator.hasNext()) {
            TextComponent sentence = (TextComponent) sentenceIterator.next();

            if (TextElementType.SENTENCE.equals(sentence.getElementType())) {
                result.add(sentence);

            } else if (!TextElementType.SYMBOL.equals(sentence.getElementType())) {
                result.addAll(findSentence(sentence));
            }
        }
        return result;
    }

    private ArrayList<TextComponent> findWords(TextComponent sentence) {
        Iterator sentenceIterator = sentence.getChildren().iterator();
        ArrayList<TextComponent> result = new ArrayList<>();
        while (sentenceIterator.hasNext()) {
            TextComponent word = (TextComponent) sentenceIterator.next();

            if (TextElementType.WORD.equals(word.getElementType())) {
                result.add(word);

            } else if (!TextElementType.SYMBOL.equals(word.getElementType())) {
                result.addAll(findWords(word));
            }
        }
        return result;
    }

    private boolean hasRepeatWord(ArrayList<TextComponent> words) {
        Iterator iterator = words.iterator();
        while (iterator.hasNext()) {
            String word = ((TextComponent) iterator.next()).getElement().toLowerCase();
            Iterator iterator2 = words.iterator();
            int count = 0;
            while (iterator2.hasNext()) {
                String word2 = ((TextComponent) iterator2.next()).getElement().toLowerCase();
                count += word.equals(word2)? 1 : 0;
                if (count >= 2) {
                    return true;
                }
            }
        }
        return false;
    }


}
