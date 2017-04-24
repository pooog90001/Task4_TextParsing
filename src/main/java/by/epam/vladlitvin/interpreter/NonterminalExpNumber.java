package by.epam.vladlitvin.interpreter;

/**
 * Created by vlad_ on 4/22/2017.
 */
public class NonterminalExpNumber extends AbstractMathExp {

    private int number;

    public NonterminalExpNumber(int number) {
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
    public int interpret(int p1, int p2) {
        throw new RuntimeException(getClass().toString()); ///Как тут поступить??!?
    }
}
