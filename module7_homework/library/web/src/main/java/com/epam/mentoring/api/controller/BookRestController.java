package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.api.request.BookRequest;
import com.epam.mentoring.api.utils.ModelMapperUtils;
import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.api.response.BookResponse;
import com.epam.mentoring.service.BookService;
import com.google.gson.Gson;
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
    private Gson gson;
    @Autowired
    private ModelMapperUtils modelMapperUtils;

    @PostMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponse createBook(@RequestBody @Valid BookRequest bookRequest) {
        BookDAO book = getBookFromRequest(bookRequest);

        return mapToResponse(bookService.createBook(book));
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

        return mapToResponse(bookService.getBookById(id));
    }

    @GetMapping(path = "/books")
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookResponse> getBooks() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all books.");
        }

        return mapToResponse(bookService.getBooks());
    }

    @PutMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponse updateBook(@RequestBody @Valid BookRequest bookRequest) {
        BookDAO book = getBookFromRequest(bookRequest);

        return mapToResponse(bookService.updateBook(book));
    }

    private BookDAO getBookFromRequest(BookRequest bookRequest) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("BookRequest: {}", gson.toJson(bookRequest));
        }

        BookDAO book = new BookDAO(bookRequest.getTitle());

        book.setId(bookRequest.getId());
        book.setAuthors(modelMapperUtils.map(bookRequest.getAuthors(), ModelMapperUtils.AUTHOR_LIST_TYPE));

        return book;
    }

    private BookResponse mapToResponse(BookDAO book) {
        return modelMapperUtils.map(book, ModelMapperUtils.BOOK_RESPONSE_TYPE);
    }

    private List<BookResponse> mapToResponse(List<BookDAO> books) {
        return modelMapperUtils.map(books, ModelMapperUtils.BOOK_RESPONSE_LIST_TYPE);
    }

}
