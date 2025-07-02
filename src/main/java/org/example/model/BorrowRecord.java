package org.example.model;

import java.time.LocalDate;

public class BorrowRecord {
    private Patron patron;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public BorrowRecord(Patron patron, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.patron = patron;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null;
    }

    public Patron getPatron() {
        return patron;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void markReturned(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.book.setAvailable(true);
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    public boolean isOverdue(LocalDate today) {
        return !isReturned() && today.isAfter(dueDate);
    }

    @Override
    public String toString() {
        return "Book: " + book.getTitle() +
                " | Patron: " + patron.getName() +
                " | Borrowed: " + borrowDate +
                " | Due: " + dueDate +
                (isReturned() ? " | Returned: " + returnDate : " | Status: Borrowed");
    }
}
