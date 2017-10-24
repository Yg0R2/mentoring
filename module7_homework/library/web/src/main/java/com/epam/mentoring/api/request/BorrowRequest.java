package com.epam.mentoring.api.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class BorrowRequest implements Serializable {

    private long id;
    private BookRequest book;
    private UserRequest userBorrowed;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private boolean ongoing;

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

    public boolean isOngoing() {
        return ongoing;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

}
