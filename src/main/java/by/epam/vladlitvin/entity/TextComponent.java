package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.type.TextElementType;

/**
 * Created by vlad_ on 4/17/2017.
 */
public abstract class TextComponent {

    private TextElementType elementType;

    public TextElementType getElementType() {
        return elementType;
    }

    public void setElementType(TextElementType elementType) {
        this.elementType = elementType;
    }

    public abstract void add(TextComponent component);
    public abstract void remove(TextComponent component);
    public abstract TextComponent getChild();
}
