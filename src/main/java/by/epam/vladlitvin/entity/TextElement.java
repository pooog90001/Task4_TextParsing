package by.epam.vladlitvin.entity;

import by.epam.vladlitvin.type.TextElementType;

import java.util.ArrayList;

/**
 * Created by vlad_ on 4/17/2017.
 */
public class TextElement extends TextComponent {


    private ArrayList<TextComponent> components;

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public String getElement() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component: components) {
            result.append(component.getChild());

            if (getElementType().equals(TextElementType.LEXEME)) {
                result.append(" ");
            }
        }

        return result.toString();

    }

    @Override
    public ArrayList<TextComponent> getChild() {
        return components;
    }


}
