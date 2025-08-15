package library.cli;

import library.models.Book;
import library.models.Member;
import library.services.LibrarySystem;
import library.exceptions.LibraryException;

import java.util.List;
import java.util.Scanner;

/**
 * Command Line Interface for the Library Management System
 * Demonstrates user interaction and system integration
 */
public class LibraryCLI {
    private LibrarySystem librarySystem;
    private Scanner scanner;

    public LibraryCLI() {
        this.librarySystem = new LibrarySystem();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== WELCOME TO LIBRARY MANAGEMENT SYSTEM ===");
        System.out.println("Developed using Object-Oriented Programming Principles");
        System.out.println("===========================================\n");

        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        bookManagementMenu();
                        break;
                    case 2:
                        memberManagementMenu();
                        break;
                    case 3:
                        issueReturnMenu();
                        break;
                    case 4:
                        searchMenu();
                        break;
                    case 5:
                        reportsMenu();
                        break;
                    case 6:
                        librarySystem.displayStatistics();
                        break;
                    case 0:
                        System.out.println("Thank you for using Library Management System!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Book Management");
        System.out.println("2. Member Management");
        System.out.println("3. Issue/Return Books");
        System.out.println("4. Search");
        System.out.println("5. Reports");
        System.out.println("6. Statistics");
        System.out.println("0. Exit");
        System.out.println("================");
    }

    private void bookManagementMenu() {
        System.out.println("\n=== BOOK MANAGEMENT ===");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. View Available Books");
        System.out.println("4. View Book Details");
        System.out.println("0. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                viewAllBooks();
                break;
            case 3:
                viewAvailableBooks();
                break;
            case 4:
                viewBookDetails();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void memberManagementMenu() {
        System.out.println("\n=== MEMBER MANAGEMENT ===");
        System.out.println("1. Add Member");
        System.out.println("2. View All Members");
        System.out.println("3. View Member Details");
        System.out.println("0. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                addMember();
                break;
            case 2:
                viewAllMembers();
                break;
            case 3:
                viewMemberDetails();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void issueReturnMenu() {
        System.out.println("\n=== ISSUE/RETURN BOOKS ===");
        System.out.println("1. Issue Book");
        System.out.println("2. Return Book");
        System.out.println("3. View Issued Books");
        System.out.println("4. View Overdue Books");
        System.out.println("0. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                issueBook();
                break;
            case 2:
                returnBook();
                break;
            case 3:
                viewIssuedBooks();
                break;
            case 4:
                viewOverdueBooks();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void searchMenu() {
        System.out.println("\n=== SEARCH ===");
        System.out.println("1. Search Books (All fields)");
        System.out.println("2. Search Books by Title");
        System.out.println("3. Search Books by Author");
        System.out.println("4. Search Books by ISBN");
        System.out.println("5. Search Members");
        System.out.println("0. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                searchBooks();
                break;
            case 2:
                searchBooksByTitle();
                break;
            case 3:
                searchBooksByAuthor();
                break;
            case 4:
                searchBooksByIsbn();
                break;
            case 5:
                searchMembers();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void reportsMenu() {
        System.out.println("\n=== REPORTS ===");
        System.out.println("1. Available Books Report");
        System.out.println("2. Issued Books Report");
        System.out.println("3. Overdue Books Report");
        System.out.println("4. Members Report");
        System.out.println("0. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                generateAvailableBooksReport();
                break;
            case 2:
                generateIssuedBooksReport();
                break;
            case 3:
                generateOverdueBooksReport();
                break;
            case 4:
                generateMembersReport();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Book management methods
    private void addBook() {
        System.out.println("\n=== ADD BOOK ===");
        String bookId = getStringInput("Enter Book ID: ");
        String title = getStringInput("Enter Title: ");
        String author = getStringInput("Enter Author: ");
        String isbn = getStringInput("Enter ISBN: ");

        try {
            librarySystem.addBook(bookId, title, author, isbn);
            System.out.println("Book added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    private void viewAllBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        List<Book> books = librarySystem.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void viewAvailableBooks() {
        System.out.println("\n=== AVAILABLE BOOKS ===");
        List<Book> books = librarySystem.getAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("No available books.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void viewBookDetails() {
        String bookId = getStringInput("Enter Book ID: ");
        try {
            Book book = librarySystem.getBook(bookId);
            System.out.println("\n=== BOOK DETAILS ===");
            System.out.println(book.getDetailedInfo());
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Member management methods
    private void addMember() {
        System.out.println("\n=== ADD MEMBER ===");
        String memberId = getStringInput("Enter Member ID: ");
        String name = getStringInput("Enter Name: ");
        String email = getStringInput("Enter Email: ");
        String phone = getStringInput("Enter Phone: ");

        try {
            librarySystem.addMember(memberId, name, email, phone);
            System.out.println("Member added successfully!");
        } catch (LibraryException e) {
            System.out.println("Error adding member: " + e.getMessage());
        }
    }

    private void viewAllMembers() {
        System.out.println("\n=== ALL MEMBERS ===");
        List<Member> members = librarySystem.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            members.forEach(System.out::println);
        }
    }

    private void viewMemberDetails() {
        String memberId = getStringInput("Enter Member ID: ");
        try {
            Member member = librarySystem.getMember(memberId);
            System.out.println("\n=== MEMBER DETAILS ===");
            System.out.println(member.getDetailedInfo());
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Issue/Return methods
    private void issueBook() {
        System.out.println("\n=== ISSUE BOOK ===");
        String bookId = getStringInput("Enter Book ID: ");
        String memberId = getStringInput("Enter Member ID: ");

        try {
            librarySystem.issueBook(bookId, memberId);
            System.out.println("Book issued successfully!");
        } catch (LibraryException e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }

    private void returnBook() {
        System.out.println("\n=== RETURN BOOK ===");
        String bookId = getStringInput("Enter Book ID: ");
        String memberId = getStringInput("Enter Member ID: ");

        try {
            librarySystem.returnBook(bookId, memberId);
            System.out.println("Book returned successfully!");
        } catch (LibraryException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }

    private void viewIssuedBooks() {
        System.out.println("\n=== ISSUED BOOKS ===");
        List<Book> books = librarySystem.getIssuedBooks();
        if (books.isEmpty()) {
            System.out.println("No books are currently issued.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void viewOverdueBooks() {
        System.out.println("\n=== OVERDUE BOOKS ===");
        List<Book> books = librarySystem.getOverdueBooks();
        if (books.isEmpty()) {
            System.out.println("No overdue books.");
        } else {
            books.forEach(book -> {
                System.out.println(book + " (Overdue by " + book.getDaysOverdue() + " days)");
            });
        }
    }

    // Search methods
    private void searchBooks() {
        String query = getStringInput("Enter search query: ");
        List<Book> books = librarySystem.searchBooks(query);
        System.out.println("\n=== SEARCH RESULTS ===");
        if (books.isEmpty()) {
            System.out.println("No books found matching the query.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void searchBooksByTitle() {
        String title = getStringInput("Enter title to search: ");
        List<Book> books = librarySystem.searchBooksByTitle(title);
        System.out.println("\n=== SEARCH RESULTS BY TITLE ===");
        if (books.isEmpty()) {
            System.out.println("No books found with that title.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void searchBooksByAuthor() {
        String author = getStringInput("Enter author to search: ");
        List<Book> books = librarySystem.searchBooksByAuthor(author);
        System.out.println("\n=== SEARCH RESULTS BY AUTHOR ===");
        if (books.isEmpty()) {
            System.out.println("No books found by that author.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void searchBooksByIsbn() {
        String isbn = getStringInput("Enter ISBN to search: ");
        List<Book> books = librarySystem.searchBooksByIsbn(isbn);
        System.out.println("\n=== SEARCH RESULTS BY ISBN ===");
        if (books.isEmpty()) {
            System.out.println("No books found with that ISBN.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void searchMembers() {
        String query = getStringInput("Enter member search query: ");
        List<Member> members = librarySystem.searchMembers(query);
        System.out.println("\n=== MEMBER SEARCH RESULTS ===");
        if (members.isEmpty()) {
            System.out.println("No members found matching the query.");
        } else {
            members.forEach(System.out::println);
        }
    }

    // Report methods
    private void generateAvailableBooksReport() {
        System.out.println("\n=== AVAILABLE BOOKS REPORT ===");
        List<Book> books = librarySystem.getAvailableBooks();
        System.out.println("Total Available Books: " + books.size());
        books.forEach(System.out::println);
    }

    private void generateIssuedBooksReport() {
        System.out.println("\n=== ISSUED BOOKS REPORT ===");
        List<Book> books = librarySystem.getIssuedBooks();
        System.out.println("Total Issued Books: " + books.size());
        books.forEach(System.out::println);
    }

    private void generateOverdueBooksReport() {
        System.out.println("\n=== OVERDUE BOOKS REPORT ===");
        List<Book> books = librarySystem.getOverdueBooks();
        System.out.println("Total Overdue Books: " + books.size());
        books.forEach(book -> {
            System.out.println(book + " (Overdue by " + book.getDaysOverdue() + " days)");
        });
    }

    private void generateMembersReport() {
        System.out.println("\n=== MEMBERS REPORT ===");
        List<Member> members = librarySystem.getAllMembers();
        System.out.println("Total Members: " + members.size());
        members.forEach(System.out::println);
    }

    // Utility methods
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}