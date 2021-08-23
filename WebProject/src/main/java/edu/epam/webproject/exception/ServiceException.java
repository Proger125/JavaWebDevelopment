package edu.epam.webproject.exception;

/**
 * Custom exception class, whose objects are thrown by Service layer
 */
public class ServiceException extends Exception{
    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public ServiceException() { }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message - detail message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause
     * @param message - detail message
     * @param cause - the cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of {@code null}
     * @param cause - the cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
