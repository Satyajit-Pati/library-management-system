package org.example.service;

import org.example.Inventory;
import org.example.model.Book;

public class InventoryManager {
    private Inventory inventory;

    public InventoryManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean addBook(String category, Book book) {
        if (inventory.getBookByISBN(book.getIsbn()) != null) {
            System.out.println("Book with ISBN " + book.getIsbn() + " already exists in the inventory.");
            return false;
        }
        inventory.addBook(category,book);
        System.out.println("Book with ISBN " + book.getIsbn() + " added to the inventory.");
        return true;
    }

    public boolean removeBook(String isbn) {
        boolean removed = inventory.removeBook(isbn);
        if (removed) {
            System.out.println("Book with ISBN " + isbn + " removed successfully.");
        } else {
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
        return removed;
    }

    // Update book details (by ISBN)
    public boolean updateBook(String isbn, String newTitle, String newAuthor, int newYear) {
        Book book = inventory.getBookByISBN(isbn);
        if (book != null) {
            book.setAvailable(true);
            System.out.println("Book updated successfully.");
            return true;
        } else {
            System.out.println("Book not found.");
            return false;
        }
    }

    // Show all books
    public void showAllBooks() {
        for (Book book : inventory.getAllBooks()) {
            System.out.println(book);
        }
    }

    // Show books by category
    public void showBooksByCategory(String category) {
        for (Book book : inventory.getBooksByCategory(category)) {
            System.out.println(book);
        }
    }
}
