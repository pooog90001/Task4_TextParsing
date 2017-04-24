package by.epam.vladlitvin.interpreter;

import java.util.ArrayDeque;

/**
 * Created by vlad_ on 4/22/2017.
 */
public class Context {
    private ArrayDeque<Integer> contextValue = new ArrayDeque<>();

    public Integer popValue() {
        return contextValue.pop();
    }

    public void pushValue(Integer value) {
        contextValue.push(value);
    }
}
