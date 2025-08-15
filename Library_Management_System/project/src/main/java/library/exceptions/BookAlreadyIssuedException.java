package library.exceptions;

/**
 * Exception for attempting to issue an already issued book
 */
public class BookAlreadyIssuedException extends LibraryException {
    
    public BookAlreadyIssuedException(String bookId) {
        super("Book is already issued: " + bookId);
    }
}