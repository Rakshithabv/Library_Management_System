package library.exceptions;

/**
 * Specific exception for book not found scenarios
 * Demonstrates inheritance hierarchy
 */
public class BookNotFoundException extends LibraryException {
    
    public BookNotFoundException(String bookId) {
        super("Book not found with ID: " + bookId);
    }
}