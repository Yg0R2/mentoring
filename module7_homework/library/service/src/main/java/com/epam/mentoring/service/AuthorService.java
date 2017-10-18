package com.epam.mentoring.service;

import com.epam.mentoring.domain.AuthorDAO;
import com.epam.mentoring.repository.AuthorRepository;
import com.epam.mentoring.service.exception.NoSuchEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public AuthorDAO createAuthor(AuthorDAO author) {
        return authorRepository.save(author);
    }

    public AuthorDAO getAuthorById(long id) {
        AuthorDAO author = authorRepository.findById(id);

        if (author == null) {
            throw new NoSuchEntryException("Author doesn't exist with the requested id=" + id);
        }

        return author;
    }

    @Transactional
    public AuthorDAO updateAuthor(AuthorDAO author) {
        AuthorDAO storedAuthor = getAuthorById(author.getId());

        if (Objects.nonNull(author.getFirstName())) {
            storedAuthor.setFirstName(author.getFirstName());
        }

        if (Objects.nonNull(author.getLastName())) {
            storedAuthor.setLastName(author.getLastName());
        }

        if (!CollectionUtils.isEmpty(author.getBooks())) {
            storedAuthor.setBooks(author.getBooks());
        }

        return storedAuthor;
    }

    public List<AuthorDAO> getAuthors() {
        return authorRepository.findAll();
    }

}
