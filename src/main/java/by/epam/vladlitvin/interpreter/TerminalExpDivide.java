package by.epam.vladlitvin.interpreter;

/**
 * Created by vlad_ on 4/22/2017.
 */
public class TerminalExpDivide extends AbstractMathExp {


    public TerminalExpDivide() {
        setPriority(2);
    }

    @Override
    public int interpret(int p1, int p2) {
        return (p1 / p2);
    }
}
