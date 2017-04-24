package by.epam.vladlitvin.exception;

/**
 * Created by vlad_ on 3/28/2017.
 */
public class SymbolException extends Exception {

    public SymbolException() {
        super();
    }

    public SymbolException(String message) {
        super(message);
    }

    public SymbolException(String message, Throwable cause) {
        super(message, cause);
    }

    public SymbolException(Throwable cause) {
        super(cause);
    }
}
