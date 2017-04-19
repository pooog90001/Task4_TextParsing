package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.type.TextElementType;

/**
 * Created by vlad_ on 4/17/2017.
 */
public class Symbol extends TextComponent {

    private Character symbol;

    public Symbol(Character symbol) {
        super.elementType = TextElementType.SYMBOL;
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent component) {

    }

    @Override
    public void remove(TextComponent component) {

    }

    @Override
    public TextComponent getChild() {
        return null;
    }
}
