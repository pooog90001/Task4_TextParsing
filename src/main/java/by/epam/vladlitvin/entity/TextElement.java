package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.type.TextElementType;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by vlad_ on 4/17/2017.
 */
public class TextElement extends TextComponent {


    private LinkedList<TextComponent> components;

    public TextElement() {
        this.components = new LinkedList<>();
    }

    public TextElement(TextElementType elementType) {
        this.components = new LinkedList<>();
        setElementType(elementType);
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public void removeAll() {
        components.removeAll(components);
    }

    @Override
    public String getElement() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component: components) {

            if (TextElementType.TEXT.equals(getElementType())) {
                result.append("\t");
                result.append(component.getElement());
                if (component != components.getLast()) {
                    result.append("\r\n");
                }
            } else if (TextElementType.SENTENCE.equals(getElementType()) &&
                    (component != components.getLast())) {
                result.append(component.getElement());
                result.append(" ");
            } else {
                result.append(component.getElement());
            }
        }

        return result.toString();

    }

    @Override
    public LinkedList<TextComponent> getChildren() {
        return components;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TextElement)) {
            return false;
        }

        TextElement that = (TextElement) o;

        return components != null ?
                components.equals(that.components) :
                that.components == null;
    }

    @Override
    public int hashCode() {
        return components != null ?
                components.hashCode() :
                0;
    }

    @Override
    public String toString() {
        return "TextElement {" +
                "type: " + getElementType() +
                "components=" + components +
                '}';
    }
}
