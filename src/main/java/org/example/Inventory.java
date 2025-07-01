package org.example;

import java.util.*;

public class Inventory {
    private Map<String , List<Book>> categoryToBooks = new HashMap<>();

    public void addBook(String category, Book book) {
        categoryToBooks.computeIfAbsent(category, k -> new java.util.ArrayList<>()).add(book);
    }

    public List<Book> getAllBooks(){
        List<Book> allBooks = new ArrayList<>();
        for(List<Book> books : categoryToBooks.values()) {
            allBooks.addAll(books);
        }
        return allBooks;
    }

    public List<Book> getBooksByCategory(String category) {
        return categoryToBooks.getOrDefault(category, new ArrayList<>());
    }

    public Book getBookByISBN(String isbn) {
        for (List<Book> books : categoryToBooks.values()) {
            for (Book book : books) {
                if (book.getIsbn().equals(isbn)) {
                    return book;
                }
            }
        }
        return null;
    }

    public boolean removeBook(String isbn) {
        for(List<Book> books : categoryToBooks.values()){
            Iterator<Book> iterator = books.iterator();
            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (book.getIsbn().equals(isbn)) {
                    iterator.remove();
                    return true;
                }
            }
        }
        return false;
    }

}
