package by.epam.vladlitvin.exception;

/**
 * Created by vlad_ on 3/21/2017.
 */
public class FileReadException extends Exception {

    public FileReadException() {
        super();
    }

    public FileReadException(String message) {
        super(message);
    }

    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileReadException(Throwable cause) {
        super(cause);
    }
}
