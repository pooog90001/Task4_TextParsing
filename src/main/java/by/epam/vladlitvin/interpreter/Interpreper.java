package by.epam.vladlitvin.interpreter;

import by.epam.vladlitvin.exception.InterpreterException;
import by.epam.vladlitvin.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vlad_ on 4/19/2017.
 */
public class Interpreper {
    private final static Logger LOGGER = LogManager.getLogger(TextReader.class.getName());

    private final static String MATH_EXPRESSION_REGEX = "(\\()+([\\d\\-\\+\\*\\/()]+)(\\))+";
    private final static String OPERANDS_AND_OPERATORS_REGEX = "([-]?\\d+)|(\\*)|(\\/)";

    /* groups of regular expression MATH_EXPRESSION_REGEX */
    private final static int BRACKETS_GROUP = 1;

    /* groups of regular expression OPERANDS_AND_OPERATORS_REGEX */
    private final static int NUMBER_GROUP = 1;
    private final static int MULTIPLY_GROUP = 2;
    private final static int DIVIDE_GROUP = 3;

    public Integer calculateMathExp(String exp) {
        LinkedList<AbstractMathExpression> mathExps = new LinkedList<>();
        Integer subExpressionResult = null;

        Pattern pattern = Pattern.compile(MATH_EXPRESSION_REGEX);
        Matcher matcher = pattern.matcher(exp);

        while (matcher.find()) {
            String str = matcher.group();
            Integer number = calculateMathExp(matcher.group(2));
            exp = exp.replace(str, String.valueOf(number));
        }
        pattern = Pattern.compile(OPERANDS_AND_OPERATORS_REGEX);
        matcher = pattern.matcher(exp);
        boolean isLastNumber = false;

        try {
            while (matcher.find()) {

                if (matcher.group(BRACKETS_GROUP) != null) {

                    if (isLastNumber) {
                        mathExps.add(new MathExpressionPlus());
                    }
                    int number = Integer.valueOf(matcher.group(NUMBER_GROUP));
                    mathExps.add(new MathExpressionNumber(number));
                    isLastNumber = true;
                } else if (matcher.group(MULTIPLY_GROUP) != null) {
                    mathExps.add(new MathExpressionMultiply());
                    isLastNumber = false;
                } else if (matcher.group(DIVIDE_GROUP) != null) {
                    mathExps.add(new MathExpressionDivide());
                    isLastNumber = false;
                }
            }
            subExpressionResult = calculateSubExp(mathExps);

        } catch (InterpreterException e) {
            LOGGER.log(Level.ERROR, "Error in evaluating a " +
                    "mathematical expression" + e.getMessage());

        } catch (NumberFormatException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }

        return subExpressionResult;
    }

    private Integer calculateSubExp(LinkedList<AbstractMathExpression> mathExpression)
            throws InterpreterException {

        while (mathExpression.size() >= 3) {
            Integer index = findMaxPriorityIndex(mathExpression);

            if (index != null) {
                AbstractMathExpression operand1 = mathExpression.get((index - 1));
                AbstractMathExpression operator = mathExpression.remove(index.intValue());
                AbstractMathExpression operand2 = mathExpression.remove(index.intValue());

                if (MathExpressionNumber.class.equals(operand1.getClass()) &&
                        MathExpressionNumber.class.equals(operand2.getClass())) {

                    int number1 = ((MathExpressionNumber) operand1).getNumber();
                    int number2 = ((MathExpressionNumber) operand2).getNumber();
                    int result = operator.interpret(number1, number2);
                    ((MathExpressionNumber) operand1).setNumber(result);
                } else {
                    throw new InterpreterException();
                }
            } else {
                throw new InterpreterException();
            }
        }
        if (mathExpression.size() == 1) {
            AbstractMathExpression result = mathExpression.get(0);
            return ((MathExpressionNumber) result).getNumber();

        } else {
            throw new InterpreterException();
        }
    }

    private Integer findMaxPriorityIndex(LinkedList<AbstractMathExpression> mathExpression) {
        int priority = defineMaxPriority(mathExpression);

        if (priority != 0) {
            for (int i = 0; i < mathExpression.size(); i++) {
                if (mathExpression.get(i).getPriority() == priority) {
                    return i;
                }
            }
        }
        return null;
    }

    private int defineMaxPriority(LinkedList<AbstractMathExpression> mathExpression) {
        int maxPriority = mathExpression.getFirst().getPriority();
        for (AbstractMathExpression exp : mathExpression) {
            if (maxPriority < exp.getPriority()) {
                maxPriority = exp.getPriority();
            }
        }
        return maxPriority;
    }




}
