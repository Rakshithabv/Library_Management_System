package library.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a book in the library system
 * Demonstrates encapsulation with private fields and public methods
 */
public class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String issuedTo;

    // Constructor
    public Book(String bookId, String title, String author, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.issueDate = null;
        this.dueDate = null;
        this.issuedTo = null;
    }

    // Getters
    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    // Setters with validation
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
    }

    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author;
        }
    }

    public void setIsbn(String isbn) {
        if (isbn != null && !isbn.trim().isEmpty()) {
            this.isbn = isbn;
        }
    }

    // Business methods
    public void issueBook(String memberId, int issueDays) {
        if (isAvailable) {
            this.isAvailable = false;
            this.issuedTo = memberId;
            this.issueDate = LocalDate.now();
            this.dueDate = issueDate.plusDays(issueDays);
        }
    }

    public void returnBook() {
        this.isAvailable = true;
        this.issuedTo = null;
        this.issueDate = null;
        this.dueDate = null;
    }

    public boolean isOverdue() {
        return !isAvailable && dueDate != null && LocalDate.now().isAfter(dueDate);
    }

    public long getDaysOverdue() {
        if (isOverdue()) {
            return LocalDate.now().toEpochDay() - dueDate.toEpochDay();
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    @Override
    public String toString() {
        return String.format("Book{ID='%s', Title='%s', Author='%s', ISBN='%s', Available=%s%s}",
                bookId, title, author, isbn, isAvailable,
                !isAvailable ? ", Due: " + dueDate : "");
    }

    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book ID: ").append(bookId).append("\n");
        sb.append("Title: ").append(title).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("ISBN: ").append(isbn).append("\n");
        sb.append("Status: ").append(isAvailable ? "Available" : "Issued").append("\n");
        
        if (!isAvailable) {
            sb.append("Issued to: ").append(issuedTo).append("\n");
            sb.append("Issue Date: ").append(issueDate).append("\n");
            sb.append("Due Date: ").append(dueDate).append("\n");
            if (isOverdue()) {
                sb.append("OVERDUE by ").append(getDaysOverdue()).append(" days").append("\n");
            }
        }
        
        return sb.toString();
    }
}