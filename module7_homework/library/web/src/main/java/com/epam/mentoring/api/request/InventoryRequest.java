package com.epam.mentoring.api.request;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public final class InventoryRequest implements Serializable {

    private long id;
    private BookRequest book;
    private int availableCopiesNumber;

    public InventoryRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookRequest getBook() {
        return book;
    }

    public void setBook(BookRequest book) {
        this.book = book;
    }

    public int getAvailableCopiesNumber() {
        return availableCopiesNumber;
    }

    public void setAvailableCopiesNumber(int availableCopiesNumber) {
        this.availableCopiesNumber = availableCopiesNumber;
    }

}
