package com.epam.mentoring.api.request;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.List;

public final class BookRequest implements Serializable {

    private long id;
    private String title;
    @JsonManagedReference
    private List<AuthorRequest> authors;

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

    public List<AuthorRequest> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorRequest> authors) {
        this.authors = authors;
    }

}
