package com.epam.mentoring.response;

import java.io.Serializable;
import java.util.Date;

public class BorrowResponse implements Serializable {

    private long id;
    private BookResponse book;
    private UserResponse userBorrowed;
    private Date returnDate;
    private boolean ongoing;

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

    public boolean isOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

}