package com.epam.mentoring.api.request;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public final class InventoryRequest implements Serializable {

    private long id;
    private BookRequest book;
    private int availableCopiesNumber;
    private List<UserRequest> usersBorrowed;
    private Date returnDate;
    private List<UserRequest> requestedForBorrow;

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

    public List<UserRequest> getUsersBorrowed() {
        return usersBorrowed;
    }

    public void setUsersBorrowed(List<UserRequest> usersBorrowed) {
        this.usersBorrowed = usersBorrowed;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public List<UserRequest> getRequestedForBorrow() {
        return requestedForBorrow;
    }

    public void setRequestedForBorrow(List<UserRequest> requestedForBorrow) {
        this.requestedForBorrow = requestedForBorrow;
    }

}
