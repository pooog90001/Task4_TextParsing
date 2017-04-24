package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.exception.SymbolException;
import by.epam.vladlitvin.type.TextElementType;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
        return;
    } //надо ли здеь писать логи или бросать исключения?

    @Override
    public void remove(TextComponent component) {
        return;
    }

    @Override
    public void removeAll() {
        return;
    }

    @Override
    public String getElement() {
        return String.valueOf(symbol);
    }

    @Override
    public ArrayDeque<TextComponent> getChild() {
        return null;
    }
}
