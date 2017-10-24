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

    @JoinColumn(name = "borrowed_user_id")
    @ManyToOne(targetEntity = UserDAO.class)
    private UserDAO userBorrowed;

    @JoinColumn(name = "booked_user_id")
    @ManyToOne(targetEntity = UserDAO.class)
    private UserDAO userBooked;

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @Column(name = "returned", nullable = false)
    private boolean returned;

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

    public UserDAO getUserBooked() {
        return userBooked;
    }

    public void setUserBooked(UserDAO userBooked) {
        this.userBooked = userBooked;
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
