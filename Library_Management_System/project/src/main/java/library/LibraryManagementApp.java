package library;

import library.cli.LibraryCLI;

/**
 * Main application class
 * Entry point for the Library Management System
 */
public class LibraryManagementApp {
    public static void main(String[] args) {
        try {
            LibraryCLI cli = new LibraryCLI();
            cli.start();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}