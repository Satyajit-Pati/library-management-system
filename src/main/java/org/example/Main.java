package org.example;

import org.example.model.Book;
import org.example.model.Patron;
import org.example.service.BorrowService;
import org.example.service.InventoryManager;
import org.example.service.SearchService;

public class Main {
    public static void main(String[] args) {
        // Initialization
        Inventory inventory = new Inventory();
        InventoryManager inventoryManager = new InventoryManager(inventory);
        SearchService searchService = new SearchService();
        BorrowService borrowService = new BorrowService(inventory);

        // Create patrons
        Patron patron1 = new Patron("P001", "Deepak", "deepak@example.com", "1234567890");
        Patron patron2 = new Patron("P002", "Kamal", "kamal@example.com", "9876543210");

        // Add books
        inventoryManager.addBook("Fiction", new Book("Harry Potter", "J.K. Rowling", 1997, "HP123"));
        inventoryManager.addBook("Fiction", new Book("The Hobbit", "J.R.R. Tolkien", 1937,  "HB456"));
        inventoryManager.addBook("Science", new Book("A Brief History of Time", "Stephen Hawking", 1988, "BH789"));

        System.out.println();

        // Show all books
        System.out.println("All Books:");
        inventoryManager.showAllBooks();

        System.out.println();

        // Search
        System.out.println("Search by Author 'J.K. Rowling':");
        for (Book b : searchService.searchByAuthor(inventory.getAllBooks(), "J.K. Rowling")) {
            System.out.println(b);
        }

        System.out.println();

        // Borrow a book
        System.out.println("Borrowing Book (HP123) by Deepak:");
        borrowService.borrowBook(patron1, "HP123", 7);

        System.out.println();

        // Try to borrow again (should fail)
        System.out.println("Attempting to borrow HP123 again by Kamal:");
        borrowService.borrowBook(patron2, "HP123", 5);

        System.out.println();

        // Show borrowed books
        System.out.println("Currently Borrowed Books:");
        borrowService.showAllBorrowedBooks();

        System.out.println();

        // Return the book
        System.out.println("Returning Book (HP123) by Deepak:");
        borrowService.returnBook(patron1, "HP123");

        System.out.println();

        // Borrow again (should succeed now)
        System.out.println("Borrowing Book (HP123) by Kamal:");
        borrowService.borrowBook(patron2, "HP123", 10);

        System.out.println();

        // Final Borrow History
        System.out.println("Borrow History:");
        borrowService.showBorrowHistory();
    }
}
