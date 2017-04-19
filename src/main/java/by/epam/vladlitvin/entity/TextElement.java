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
        // УЗНАТЬ КАКОЙ ЭЛЕМЕНТ УДАЛИТЬСЯ И ИЩЕТ ЛИ ОН ВСЕ ТАКИЕ ЭЛЕМЕТЫ И УДАЛЯТ ЛИ ОН ВСЕ!!!
        components.remove(component);
    }

    @Override
    public TextComponent getChild() {
        return null;
    }
}
