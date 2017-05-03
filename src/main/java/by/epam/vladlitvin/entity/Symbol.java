package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.reader.TextReader;
import by.epam.vladlitvin.type.TextElementType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

/**
 * Created by vlad_ on 4/17/2017.
 */
public class Symbol extends TextComponent {
    private char symbol;

    public Symbol(char symbol) {
        setElementType(TextElementType.SYMBOL);
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException(getClass().toString());
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException(getClass().toString());
    }

    @Override
    public void removeAll() {
        throw new UnsupportedOperationException(getClass().toString());
    }

    @Override
    public String getElement() {
        return String.valueOf(symbol);
    }

    @Override
    public LinkedList<TextComponent> getChildren() {
        throw new UnsupportedOperationException(getClass().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o.getClass().equals(Symbol.class) )) {
            return false;
        }

        Symbol symbol1 = (Symbol) o;

        return symbol == symbol1.symbol;
    }

    @Override
    public int hashCode() {
        return (int) symbol;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbol=" + symbol +
                '}';
    }
}
