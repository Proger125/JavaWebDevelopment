package edu.epam.webproject.exception;

/**
 * Custom exception class, whose objects are thrown by DAO layer
 */
public class DaoException extends Exception{

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public DaoException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message - detail message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause
     * @param message - detail message
     * @param cause - the cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of {@code null}
     * @param cause - the cause
     */
    public DaoException(Throwable cause) {
        super(cause);
    }
}
