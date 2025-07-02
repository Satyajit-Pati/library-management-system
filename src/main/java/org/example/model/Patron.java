package org.example.model;

import java.util.ArrayList;
import java.util.List;
import org.example.model.BorrowRecord;

public class Patron {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    private List<BorrowRecord> borrowHistory;

    public Patron(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.borrowHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<BorrowRecord> getBorrowHistory() {
        return borrowHistory;
    }

    public void addBorrowRecord(BorrowRecord record) {
        borrowHistory.add(record);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
