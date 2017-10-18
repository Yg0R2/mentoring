package com.epam.mentoring.api.request;

import java.util.Date;
import java.util.Set;

public class InventoryRequest {

    private long id;
    private BookRequest book;
    private UserRequest userBorrowed;
    private Date returnDate;
    private Set<UserRequest> requestedForBorrow;

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

    public UserRequest getUserBorrowed() {
        return userBorrowed;
    }

    public void setUserBorrowed(UserRequest userBorrowed) {
        this.userBorrowed = userBorrowed;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Set<UserRequest> getRequestedForBorrow() {
        return requestedForBorrow;
    }

    public void setRequestedForBorrow(Set<UserRequest> requestedForBorrow) {
        this.requestedForBorrow = requestedForBorrow;
    }

}
