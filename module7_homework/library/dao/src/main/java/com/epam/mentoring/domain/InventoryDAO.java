package com.epam.mentoring.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inventories")
public final class InventoryDAO implements Serializable {

    @Column(name = "id", nullable = false, updatable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @JoinColumn(name = "book_id", nullable = false)
    @OneToOne(targetEntity = BookDAO.class)
    private BookDAO book;

    @Column(name = "available_copies_number", nullable = false)
    private int availableCopiesNumber;

    @JoinTable(
        name = "user_inventory_borrowed",
        joinColumns = @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id", nullable = false, unique = true)
    )
    @OneToMany(targetEntity = UserDAO.class)
    private List<UserDAO> usersBorrowed;

    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @JoinTable(
        name = "user_inventory_requested",
        joinColumns = @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "id", nullable = false, unique = true)
    )
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

    public int getAvailableCopiesNumber() {
        return availableCopiesNumber;
    }

    public void setAvailableCopiesNumber(int availableCopiesNumber) {
        this.availableCopiesNumber = availableCopiesNumber;
    }

    public List<UserDAO> getUsersBorrowed() {
        return usersBorrowed;
    }

    public void setUsersBorrowed(List<UserDAO> usersBorrowed) {
        this.usersBorrowed = usersBorrowed;
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
