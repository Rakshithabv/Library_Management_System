package library.services;

import library.models.Book;
import library.models.Member;
import library.exceptions.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Main service class for library operations
 * Demonstrates composition, encapsulation, and business logic
 */
public class LibrarySystem {
    private Map<String, Book> books;
    private Map<String, Member> members;
    private static final int DEFAULT_ISSUE_DAYS = 14;

    public LibrarySystem() {
        this.books = new HashMap<>();
        this.members = new HashMap<>();
        initializeSampleData();
    }

    // Initialize with some sample data
    private void initializeSampleData() {
        // Sample books
        addBook("B001", "The Great Gatsby", "F. Scott Fitzgerald", "978-0-7432-7356-5");
        addBook("B002", "To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4");
        addBook("B003", "1984", "George Orwell", "978-0-452-28423-4");
        addBook("B004", "Pride and Prejudice", "Jane Austen", "978-0-14-143951-8");
        addBook("B005", "The Catcher in the Rye", "J.D. Salinger", "978-0-316-76948-0");

        // Sample members
        try {
            addMember("M001", "John Doe", "john.doe@email.com", "123-456-7890");
            addMember("M002", "Jane Smith", "jane.smith@email.com", "098-765-4321");
            addMember("M003", "Bob Johnson", "bob.johnson@email.com", "555-123-4567");
        } catch (LibraryException e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
        }
    }

    // Book management methods
    public void addBook(String bookId, String title, String author, String isbn) {
        if (books.containsKey(bookId)) {
            throw new IllegalArgumentException("Book with ID " + bookId + " already exists");
        }
        Book book = new Book(bookId, title, author, isbn);
        books.put(bookId, book);
    }

    public Book getBook(String bookId) throws BookNotFoundException {
        Book book = books.get(bookId);
        if (book == null) {
            throw new BookNotFoundException(bookId);
        }
        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public List<Book> getAvailableBooks() {
        return books.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Book> getIssuedBooks() {
        return books.values().stream()
                .filter(book -> !book.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Book> getOverdueBooks() {
        return books.values().stream()
                .filter(Book::isOverdue)
                .collect(Collectors.toList());
    }

    // Member management methods
    public void addMember(String memberId, String name, String email, String phone) throws LibraryException {
        if (members.containsKey(memberId)) {
            throw new LibraryException("Member with ID " + memberId + " already exists");
        }
        Member member = new Member(memberId, name, email, phone);
        members.put(memberId, member);
    }

    public Member getMember(String memberId) throws MemberNotFoundException {
        Member member = members.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException(memberId);
        }
        return member;
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    // Book issue/return methods
    public void issueBook(String bookId, String memberId) throws LibraryException {
        Book book = getBook(bookId);
        Member member = getMember(memberId);

        if (!book.isAvailable()) {
            throw new BookAlreadyIssuedException(bookId);
        }

        if (!member.canIssueMoreBooks()) {
            throw new LibraryException("Member " + memberId + " has reached maximum book limit");
        }

        book.issueBook(memberId, DEFAULT_ISSUE_DAYS);
        member.addIssuedBook(bookId);
    }

    public void returnBook(String bookId, String memberId) throws LibraryException {
        Book book = getBook(bookId);
        Member member = getMember(memberId);

        if (book.isAvailable()) {
            throw new LibraryException("Book " + bookId + " is not currently issued");
        }

        if (!book.getIssuedTo().equals(memberId)) {
            throw new LibraryException("Book " + bookId + " was not issued to member " + memberId);
        }

        book.returnBook();
        member.removeIssuedBook(bookId);
    }

    // Search methods
    public List<Book> searchBooks(String query) {
        String lowerQuery = query.toLowerCase();
        return books.values().stream()
                .filter(book -> 
                    book.getTitle().toLowerCase().contains(lowerQuery) ||
                    book.getAuthor().toLowerCase().contains(lowerQuery) ||
                    book.getIsbn().toLowerCase().contains(lowerQuery) ||
                    book.getBookId().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchBooksByIsbn(String isbn) {
        return books.values().stream()
                .filter(book -> book.getIsbn().toLowerCase().contains(isbn.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Member> searchMembers(String query) {
        String lowerQuery = query.toLowerCase();
        return members.values().stream()
                .filter(member ->
                    member.getName().toLowerCase().contains(lowerQuery) ||
                    member.getEmail().toLowerCase().contains(lowerQuery) ||
                    member.getMemberId().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    // Statistics methods
    public int getTotalBooks() {
        return books.size();
    }

    public int getAvailableBooksCount() {
        return (int) books.values().stream().filter(Book::isAvailable).count();
    }

    public int getIssuedBooksCount() {
        return (int) books.values().stream().filter(book -> !book.isAvailable()).count();
    }

    public int getOverdueBooksCount() {
        return (int) books.values().stream().filter(Book::isOverdue).count();
    }

    public int getTotalMembers() {
        return members.size();
    }

    public void displayStatistics() {
        System.out.println("\n=== LIBRARY STATISTICS ===");
        System.out.println("Total Books: " + getTotalBooks());
        System.out.println("Available Books: " + getAvailableBooksCount());
        System.out.println("Issued Books: " + getIssuedBooksCount());
        System.out.println("Overdue Books: " + getOverdueBooksCount());
        System.out.println("Total Members: " + getTotalMembers());
        System.out.println("==========================");
    }
}