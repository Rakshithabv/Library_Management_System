package library.exceptions;

/**
 * Custom exception class for library operations
 * Demonstrates inheritance from Exception class
 */
public class LibraryException extends Exception {
    
    public LibraryException(String message) {
        super(message);
    }
    
    public LibraryException(String message, Throwable cause) {
        super(message, cause);
    }
}