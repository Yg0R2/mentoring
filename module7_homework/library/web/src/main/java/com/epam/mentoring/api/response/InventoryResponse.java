package com.epam.mentoring.api.response;

import java.util.Date;
import java.util.Set;

public class InventoryResponse {

    private long id;
    private BookResponse book;
    private UserResponse userBorrowed;
    private Date returnDate;
    private Set<UserResponse> requestedForBorrow;

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

    public UserResponse getUserBorrowed() {
        return userBorrowed;
    }

    public void setUserBorrowed(UserResponse userBorrowed) {
        this.userBorrowed = userBorrowed;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Set<UserResponse> getRequestedForBorrow() {
        return requestedForBorrow;
    }

    public void setRequestedForBorrow(Set<UserResponse> requestedForBorrow) {
        this.requestedForBorrow = requestedForBorrow;
    }

}
