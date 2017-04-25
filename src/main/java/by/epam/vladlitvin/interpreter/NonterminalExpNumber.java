package by.epam.vladlitvin.interpreter;

import by.epam.vladlitvin.reader.TextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by vlad_ on 4/22/2017.
 */
public class NonterminalExpNumber extends AbstractMathExp {
    private final static Logger LOGGER = LogManager.getLogger(TextReader.class.getName());


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
        LOGGER.log(Level.FATAL, getClass().getSimpleName() + " Can't realize \"interpret(int p1, int p2)\" method");
        throw new UnsupportedOperationException(getClass().toString());
    }
}
