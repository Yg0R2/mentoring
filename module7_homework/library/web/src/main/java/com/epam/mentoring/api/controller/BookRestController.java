package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.request.BookRequest;
import com.epam.mentoring.mapper.BookMapper;
import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.response.BookResponse;
import com.epam.mentoring.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class BookRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @PostMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponse createBook(@RequestBody @Valid BookRequest bookRequest) {
        BookDAO book = bookMapper.mapToDao(bookRequest);

        BookDAO storedBook = bookService.createBook(book);

        return bookMapper.mapToResponse(storedBook);
    }

    @DeleteMapping(path = "/book")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBook(@RequestParam long id) {
        bookService.deleteBook(id);
    }

    @GetMapping(path = "/book")
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BookResponse getBook() {
        throw new MissingRequestParameterException("Missing request parameter.");
    }

    @GetMapping(path = "/book", params = {"id"})
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponse getBook(@RequestParam long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get book by Id: {}", id);
        }

        BookDAO storedBook = bookService.getBookById(id);

        return bookMapper.mapToResponse(storedBook);
    }

    @GetMapping(path = "/book", params = {"title"})
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponse getBook(@RequestParam String title) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get book by Title: {}", title);
        }

        BookDAO storedBook = bookService.getBookByTitle(title);

        return bookMapper.mapToResponse(storedBook);
    }

    @GetMapping(path = "/books")
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookResponse> getBooks() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all books.");
        }

        List<BookDAO> storedBooks = bookService.getBooks();

        return bookMapper.mapToResponse(storedBooks);
    }

    @PutMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponse updateBook(@RequestBody @Valid BookRequest bookRequest) {
        BookDAO book = bookMapper.mapToDao(bookRequest);

        BookDAO storedBook = bookService.updateBook(book);

        return bookMapper.mapToResponse(storedBook);
    }

}
