package library.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a library member
 * Demonstrates encapsulation and composition
 */
public class Member {
    private String memberId;
    private String name;
    private String email;
    private String phone;
    private LocalDate membershipDate;
    private List<String> issuedBooks;
    private static final int MAX_BOOKS_ALLOWED = 5;

    // Constructor
    public Member(String memberId, String name, String email, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.membershipDate = LocalDate.now();
        this.issuedBooks = new ArrayList<>();
    }

    // Getters
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public List<String> getIssuedBooks() {
        return new ArrayList<>(issuedBooks); // Return copy to maintain encapsulation
    }

    public int getIssuedBooksCount() {
        return issuedBooks.size();
    }

    // Setters with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone;
        }
    }

    // Business methods
    public boolean canIssueMoreBooks() {
        return issuedBooks.size() < MAX_BOOKS_ALLOWED;
    }

    public void addIssuedBook(String bookId) {
        if (canIssueMoreBooks() && !issuedBooks.contains(bookId)) {
            issuedBooks.add(bookId);
        }
    }

    public void removeIssuedBook(String bookId) {
        issuedBooks.remove(bookId);
    }

    public boolean hasIssuedBook(String bookId) {
        return issuedBooks.contains(bookId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Member member = (Member) obj;
        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }

    @Override
    public String toString() {
        return String.format("Member{ID='%s', Name='%s', Email='%s', Books Issued=%d}",
                memberId, name, email, issuedBooks.size());
    }

    public String getDetailedInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Member ID: ").append(memberId).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Phone: ").append(phone).append("\n");
        sb.append("Membership Date: ").append(membershipDate).append("\n");
        sb.append("Books Issued: ").append(issuedBooks.size()).append("/").append(MAX_BOOKS_ALLOWED).append("\n");
        
        if (!issuedBooks.isEmpty()) {
            sb.append("Issued Book IDs: ").append(String.join(", ", issuedBooks)).append("\n");
        }
        
        return sb.toString();
    }

    public static int getMaxBooksAllowed() {
        return MAX_BOOKS_ALLOWED;
    }
}