package exception;

/**
 * Exception that indicates that there was a problem during accessing database.
 */
public class DataAccessException extends RuntimeException {

    /**
     * Creates a new {@code DataAccessException} object with a specified message and cause.
     *
     * @param message message of the exception
     * @param cause   cause of the exception
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
