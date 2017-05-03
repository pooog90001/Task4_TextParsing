package by.epam.vladlitvin.interpreter;

/**
 * Created by vlad_ on 4/22/2017.
 */
public class MathExpressionMultiply extends AbstractMathExpression {

    public MathExpressionMultiply() {
        setPriority(2);
    }

    @Override
    public int interpret(int operand1, int operand2) {
        return (operand1 * operand2);
    }
}
