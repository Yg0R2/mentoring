package com.epam.mentoring.service;

import com.epam.mentoring.domain.AuthorDAO;
import com.epam.mentoring.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public AuthorDAO createAuthor(AuthorDAO author) {
        return authorRepository.save(author);
    }

    public AuthorDAO getAuthorById(long id) {
        return authorRepository.findById(id);
    }

    public List<AuthorDAO> getAuthors() {
        return authorRepository.findAll();
    }

}
