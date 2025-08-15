package library.exceptions;

/**
 * Specific exception for member not found scenarios
 */
public class MemberNotFoundException extends LibraryException {
    
    public MemberNotFoundException(String memberId) {
        super("Member not found with ID: " + memberId);
    }
}