package com.epam.mentoring.service;

import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.repository.BookRepository;
import com.epam.mentoring.service.exception.NoSuchEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public BookDAO createBook(BookDAO book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(long id) {
        try {
            bookRepository.delete(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoSuchEntryException("Book doesn't exist with the requested id=" + id);
        }
    }

    public BookDAO getBookById(long id) {
        BookDAO book = bookRepository.findById(id);

        if (book == null) {
            throw new NoSuchEntryException("Book doesn't exist with the requested id=" + id);
        }

        return book;
    }

    public List<BookDAO> getBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public BookDAO updateBook(BookDAO book) {
        BookDAO storedBook = getBookById(book.getId());

        if (Objects.nonNull(book.getTitle())) {
            storedBook.setTitle(book.getTitle());
        }

        if (!CollectionUtils.isEmpty(book.getAuthors())) {
            storedBook.setAuthors(book.getAuthors());
        }

        return storedBook;
    }

}
