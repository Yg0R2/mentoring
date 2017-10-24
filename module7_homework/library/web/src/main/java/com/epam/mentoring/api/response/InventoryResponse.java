package com.epam.mentoring.api.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public final class InventoryResponse implements Serializable {

    private long id;
    private BookResponse book;
    private int availableCopiesNumber;
    private List<UserResponse> usersBorrowed;
    private Date returnDate;
    private List<UserResponse> requestedForBorrow;

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

    public List<UserResponse> getUsersBorrowed() {
        return usersBorrowed;
    }

    public void setUsersBorrowed(List<UserResponse> usersBorrowed) {
        this.usersBorrowed = usersBorrowed;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public List<UserResponse> getRequestedForBorrow() {
        return requestedForBorrow;
    }

    public void setRequestedForBorrow(List<UserResponse> requestedForBorrow) {
        this.requestedForBorrow = requestedForBorrow;
    }

}
