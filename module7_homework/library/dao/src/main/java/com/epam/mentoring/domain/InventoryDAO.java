package com.epam.mentoring.domain;

import javax.persistence.*;
import java.io.Serializable;

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

}
