package com.epam.mentoring.service;

import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BookDAO createBook(BookDAO book) {
        return bookRepository.save(book);
    }

    public BookDAO getBookById(long id) {
        return bookRepository.findById(id);
    }

    public List<BookDAO> getBooks() {
        return bookRepository.findAll();
    }

}
