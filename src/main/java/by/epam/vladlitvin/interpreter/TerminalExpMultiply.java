package by.epam.vladlitvin.interpreter;

/**
 * Created by vlad_ on 4/22/2017.
 */
public class TerminalExpMultiply extends AbstractMathExp {

    public TerminalExpMultiply() {
        setPriority(2);
    }

    @Override
    public int interpret(int p1, int p2) {
        return (p1 * p2);
    }
}
