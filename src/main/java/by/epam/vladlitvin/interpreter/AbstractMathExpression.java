package by.epam.vladlitvin.interpreter;

/**
 * Created by vlad_ on 4/22/2017.
 */
public abstract class AbstractMathExpression {
    private int priority;

    public abstract int interpret(int operand1, int operand2);

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
