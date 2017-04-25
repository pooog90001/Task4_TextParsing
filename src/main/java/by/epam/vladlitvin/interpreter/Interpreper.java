package by.epam.vladlitvin.interpreter;

import by.epam.vladlitvin.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.MapMessage;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vlad_ on 4/19/2017.
 */
public class Interpreper {
    private final static Logger LOGGER = LogManager.getLogger(TextReader.class.getName());

    private final static String BRACKETS_REGEX = "(\\()+([\\d\\-\\+\\*\\/()]+)(\\))+";
    private final static String OPERANDS_AND_OPERATORS_REGEX = "([-]?\\d+)|(\\*)|(\\/)";

    public int calculateMathExp(String exp) {
        LinkedList<AbstractMathExp> mathExps = new LinkedList<>();

        Pattern pattern = Pattern.compile(BRACKETS_REGEX);
        Matcher matcher = pattern.matcher(exp);

        while (matcher.find()) {
            String str = matcher.group();
            int number = calculateMathExp(matcher.group(2));
            exp = exp.replace(str, String.valueOf(number));
        }
        pattern = Pattern.compile(OPERANDS_AND_OPERATORS_REGEX);
        matcher = pattern.matcher(exp);
        boolean isLastNumber = false;

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                if (isLastNumber) {
                    mathExps.add(new TerminalExpPlus());
                }
                int number = Integer.valueOf(matcher.group(1));
                mathExps.add(new NonterminalExpNumber(number));
                isLastNumber = true;

            } else if (matcher.group(2) != null) {
                mathExps.add(new TerminalExpMultiply());
                isLastNumber = false;

            } else if (matcher.group(3) != null) {
                mathExps.add(new TerminalExpDivide());
                isLastNumber = false;
            }
        }

        return calculateSubExp(mathExps);

    }

    private int calculateSubExp(LinkedList<AbstractMathExp> mathExps) {

        while (mathExps.size() >= 3) {
            Integer index = findMaxPriorityIndex(mathExps);

            if (index != null) {
                AbstractMathExp p1 = mathExps.get((index - 1));
                AbstractMathExp x = mathExps.remove(index.intValue());
                AbstractMathExp p2 = mathExps.remove(index.intValue());

                if (NonterminalExpNumber.class.equals(p1.getClass()) &&
                        NonterminalExpNumber.class.equals(p2.getClass())) {

                    int number1 = ((NonterminalExpNumber) p1).getNumber();
                    int number2 = ((NonterminalExpNumber) p2).getNumber();
                    int result = x.interpret(number1, number2);
                    ((NonterminalExpNumber) p1).setNumber(result);
                } else {
                    LOGGER.log(Level.FATAL, p1 + " " + p2);
                    throw new RuntimeException(getClass().toString() + " 1");
                }
            } else {
                throw new RuntimeException(getClass().toString() + " 2");
            }
        }
        if (mathExps.size() == 1) {
            AbstractMathExp result = mathExps.get(0);
            return ((NonterminalExpNumber) result).getNumber();
        } else {
            throw new RuntimeException(getClass().toString() + " 3");
        }
    }

    private Integer findMaxPriorityIndex(LinkedList<AbstractMathExp> mathExps) {
        int priority = defineMaxPriority(mathExps);

        if (priority != 0) {
            for (int i = 0; i < mathExps.size(); i++) {
                if (mathExps.get(i).getPriority() == priority) {
                    return i;
                }
            }
        }
        return null;
    }

    private int defineMaxPriority(LinkedList<AbstractMathExp> mathExps) {
        int maxPriority = mathExps.getFirst().getPriority();
        for (AbstractMathExp exp : mathExps) {
            if (maxPriority < exp.getPriority()) {
                maxPriority = exp.getPriority();
            }
        }
        return maxPriority;
    }




}
