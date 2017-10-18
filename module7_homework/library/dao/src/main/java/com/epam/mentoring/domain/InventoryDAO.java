package com.epam.mentoring.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "inventories")
public class InventoryDAO {

    @Column(name = "id", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @JoinColumn(name = "book_id", nullable = false)
    @OneToOne(targetEntity = BookDAO.class)
    private BookDAO book;

    @JoinColumn(name = "user_id", nullable = false)
    @OneToOne(targetEntity = UserDAO.class)
    private UserDAO userBorrowed;

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @JoinColumn(name = "request_for_borrow")
    @OneToMany(targetEntity = UserDAO.class)
    private List<UserDAO> requestedForBorrow;

    public InventoryDAO() {
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

    public List<UserDAO> getRequestedForBorrow() {
        return requestedForBorrow;
    }

    public void setRequestedForBorrow(List<UserDAO> requestedForBorrow) {
        this.requestedForBorrow = requestedForBorrow;
    }
}
