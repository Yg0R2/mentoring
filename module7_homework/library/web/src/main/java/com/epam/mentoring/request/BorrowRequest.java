package com.epam.mentoring.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class BorrowRequest implements Serializable {

    private long id;
    private BookRequest book;
    private UserRequest userBorrowed;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
    private boolean returned;

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

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

}
