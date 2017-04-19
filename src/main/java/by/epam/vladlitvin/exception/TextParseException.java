package by.epam.vladlitvin.exception;

/**
 * Created by vlad_ on 3/28/2017.
 */
public class TextParseException extends Exception {

    public TextParseException() {
        super();
    }

    public TextParseException(String message) {
        super(message);
    }

    public TextParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextParseException(Throwable cause) {
        super(cause);
    }
}
