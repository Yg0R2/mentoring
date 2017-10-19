package com.epam.mentoring.api.response;

import java.io.Serializable;
import java.util.List;

public final class BookResponse implements Serializable {

    private long id;
    private String title;
    private List<AuthorResponse> authors;

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

    public List<AuthorResponse> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorResponse> authors) {
        this.authors = authors;
    }

}
