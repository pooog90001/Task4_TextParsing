package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.type.TextElementType;

import java.util.ArrayList;

/**
 * Created by vlad_ on 4/17/2017.
 */
public class Symbol extends TextComponent {

    private Character symbol;

    public Symbol(Character symbol) {
        setElementType(TextElementType.SYMBOL);
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent component) {

    }

    @Override
    public void remove(TextComponent component) {

    }

    @Override
    public String getElement() {
        return symbol.toString();
    }

    @Override
    public ArrayList<TextComponent> getChild() {
        return null;
    }
}
