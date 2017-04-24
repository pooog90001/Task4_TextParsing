package by.epam.vladlitvin.calculater;

import by.epam.vladlitvin.entity.Symbol;
import by.epam.vladlitvin.entity.TextComponent;
import by.epam.vladlitvin.interpreter.Interpreper;
import by.epam.vladlitvin.reader.TextReader;
import by.epam.vladlitvin.type.TextElementType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vlad_ on 4/20/2017.
 */
public class ExpCalculater {
    private final static Logger LOGGER = LogManager.getLogger(TextReader.class.getName());


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
            LOGGER.log(Level.INFO, mathExpression);
            if (checkForParam(mathExpression)) {
                LOGGER.log(Level.INFO, mathExpression + "  -------");
                int result = interpreper.calculateMathExp(mathExpression);
                replaceMathExp(exp, String.valueOf(result));
            }
        }


    }

    private ArrayDeque<TextComponent> findExpressions(TextComponent component) {

        ArrayDeque<TextComponent> mathExpressions = new ArrayDeque<>();

        for (TextComponent textComponent : component.getChild()) {
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

        final int PREFIX_INCREMENT_GROUP = 3;
        final int PREFIX_DECREMENT_GROUP = 4;
        final int POSTFIX_INCREMENT_GROUP = 7;
        final int POSTFIX_DECREMENT_GROUP = 8;

        Pattern pattern = Pattern.compile(PARAMETER_REGEX);

        for (TextComponent expression : expressions) {
            String stringExp = expression.getElement();
            Matcher matcher = pattern.matcher(stringExp);
            boolean flag = false;
            while (matcher.find()) {
                flag = true;
                String number = matcher.group(5);

                String prefixIncrement = matcher.group(PREFIX_INCREMENT_GROUP);
                String prefixDecrement = matcher.group(PREFIX_DECREMENT_GROUP);
                String postfixIncrement = matcher.group(POSTFIX_INCREMENT_GROUP);
                String postfixDecrement = matcher.group(POSTFIX_DECREMENT_GROUP);
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

    private boolean checkForParam(String exp) {
        Pattern pattern = Pattern.compile("((\\d+)|(\\+)|(-)|(\\*)|(\\/)|([(])|([)]))+");
        Matcher matcher = pattern.matcher(exp);
        return matcher.matches();
    }
}
