package by.epam.vladlitvin.interpreter;

import by.epam.vladlitvin.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by vlad_ on 4/22/2017.
 */
public class MathExpressionNumber extends AbstractMathExpression {
    private final static Logger LOGGER = LogManager.getLogger(TextReader.class.getName());


    private int number;

    public MathExpressionNumber(int number) {
        this.number = number;
        setPriority(0);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int interpret(int operand1, int operand2) {
        throw new UnsupportedOperationException(getClass().toString());
    }
}
