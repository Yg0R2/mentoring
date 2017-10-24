package com.epam.mentoring.api.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public final class InventoryResponse implements Serializable {

    private long id;
    private BookResponse book;
    private int availableCopiesNumber;

    public InventoryResponse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookResponse getBook() {
        return book;
    }

    public void setBook(BookResponse book) {
        this.book = book;
    }

    public int getAvailableCopiesNumber() {
        return availableCopiesNumber;
    }

    public void setAvailableCopiesNumber(int availableCopiesNumber) {
        this.availableCopiesNumber = availableCopiesNumber;
    }

}
