package by.epam.vladlitvin.parser;

import by.epam.vladlitvin.entity.TextComponent;

/**
 * Created by vlad_ on 4/17/2017.
 */
public abstract class AbstractParser {


    public abstract TextComponent handleParse(String inPut);

    public TextComponent chain(String inPut) {
        return handleParse(inPut);

    }

}
