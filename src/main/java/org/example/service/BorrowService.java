package org.example.service;

import org.example.Inventory;
import org.example.model.Book;
import org.example.model.BorrowRecord;
import org.example.model.Patron;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowService {

    private Inventory inventory;
    private List<BorrowRecord> borrowRecords;

    public BorrowService(Inventory inventory) {
        this.inventory = inventory;
        this.borrowRecords = new ArrayList<>();
    }

    public boolean borrowBook(Patron patron, String isbn, int days) {
        Book book = inventory.getBookByISBN(isbn);
        if (book == null) {
            System.out.println("Book not found.");
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is currently unavailable.");
            return false;
        }


        book.setAvailable(false);
        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plusDays(days);
        BorrowRecord record = new BorrowRecord(patron, book, today, dueDate);
        borrowRecords.add(record);
        patron.addBorrowRecord(record);

        System.out.println("Book borrowed successfully by " + patron.getName() + ". Due on " + dueDate + ".");
        return true;
    }

    public boolean returnBook(Patron patron, String isbn) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getPatron().equals(patron) &&
                    record.getBook().getIsbn().equals(isbn) &&
                    !record.isReturned()) {

                record.markReturned(LocalDate.now());
                System.out.println("Book returned successfully.");
                return true;
            }
        }
        System.out.println("No active borrow record found for this book and patron.");
        return false;
    }


    public void showAllBorrowedBooks() {
        for (BorrowRecord record : borrowRecords) {
            if (!record.isReturned()) {
                System.out.println(record);
            }
        }
    }


    public void showBorrowHistory() {
        for (BorrowRecord record : borrowRecords) {
            System.out.println(record);
        }
    }
}
