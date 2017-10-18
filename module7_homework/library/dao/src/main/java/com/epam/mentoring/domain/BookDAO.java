package com.epam.mentoring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "books")
public class BookDAO implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.MERGE, targetEntity = AuthorDAO.class)
    @JoinTable(
        name = "authors_books",
        joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AuthorDAO> authors;

    /*@Column(name = "number_of_pages", nullable = false)
    private int numberOfPages;

    @Column(name = "publish_date", nullable = false)
    @Past
    @Temporal(TemporalType.DATE)
    private Date publishDate;*/

    private BookDAO() {
    }

    public BookDAO(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorDAO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDAO> authors) {
        this.authors = authors;
    }

    /*public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        BookDAO book = (BookDAO) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}
