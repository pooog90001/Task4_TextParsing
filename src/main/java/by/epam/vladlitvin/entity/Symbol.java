package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.exception.SymbolException;
import by.epam.vladlitvin.reader.TextReader;
import by.epam.vladlitvin.type.TextElementType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by vlad_ on 4/17/2017.
 */
public class Symbol extends TextComponent {
    private final static Logger LOGGER = LogManager.getLogger(TextReader.class.getName());


    private char symbol;

    public Symbol(char symbol) {
        setElementType(TextElementType.SYMBOL);
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent component) {
        LOGGER.log(Level.FATAL, getClass().getSimpleName() +
                " Can't realize \"add(TextComponent component)\" method");
        throw new UnsupportedOperationException(getClass().toString());
    }

    @Override
    public void remove(TextComponent component) {
        LOGGER.log(Level.FATAL, getClass().getSimpleName() +
                " Can't realize \"remove(TextComponent component)\" method");
        throw new UnsupportedOperationException(getClass().toString());
    }

    @Override
    public void removeAll() {
        LOGGER.log(Level.FATAL, getClass().getSimpleName() +
                " Can't realize \"removeAll()\" method");
        throw new UnsupportedOperationException(getClass().toString());
    }

    @Override
    public String getElement() {
        return String.valueOf(symbol);
    }

    @Override
    public LinkedList<TextComponent> getChildren() {
        LOGGER.log(Level.FATAL, getClass().getSimpleName() +
                " Can't realize \"getChildren()\" method");
        throw new UnsupportedOperationException(getClass().toString());
    }
}
