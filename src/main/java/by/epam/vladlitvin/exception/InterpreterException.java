package by.epam.vladlitvin.exception;

/**
 * Created by vlad_ on 3/28/2017.
 */
public class InterpreterException extends Exception {

    public InterpreterException() {
        super();
    }

    public InterpreterException(String message) {
        super(message);
    }

    public InterpreterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterpreterException(Throwable cause) {
        super(cause);
    }
}
