package com.epam.mentoring.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "borrows")
public final class BorrowDAO {

    @Column(name = "id", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @JoinColumn(name = "book_id", nullable = false)
    @OneToOne(targetEntity = BookDAO.class)
    private BookDAO book;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(targetEntity = UserDAO.class)
    private UserDAO userBorrowed;

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Column(name = "ongoing", nullable = false)
    private boolean ongoing;

    public BorrowDAO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookDAO getBook() {
        return book;
    }

    public void setBook(BookDAO book) {
        this.book = book;
    }

    public UserDAO getUserBorrowed() {
        return userBorrowed;
    }

    public void setUserBorrowed(UserDAO userBorrowed) {
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
