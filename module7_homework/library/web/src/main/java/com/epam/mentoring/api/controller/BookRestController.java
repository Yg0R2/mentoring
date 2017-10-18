package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.api.exception.NoSuchEntryException;
import com.epam.mentoring.api.request.BookRequest;
import com.epam.mentoring.domain.AuthorDAO;
import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.api.response.BookResponse;
import com.epam.mentoring.service.BookService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class BookRestController {

    private static final Type AUTHOR_LIST_TYPE = new TypeToken<List<AuthorDAO>>() {}.getType();
    private static final Type BOOK_RESPONSE_LIST_TYPE = new TypeToken<List<BookResponse>>() {}.getType();
    private static final Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);

    @Autowired
    private BookService bookService;
    @Autowired
    private Gson gson;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/book", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponse createBook(@RequestBody @Valid BookRequest bookRequest) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("BookRequest: {}", gson.toJson(bookRequest));
        }

        BookDAO book = new BookDAO(bookRequest.getTitle());

        book.setAuthors(modelMapper.map(bookRequest.getAuthors(), AUTHOR_LIST_TYPE));

        return mapToResponse(bookService.createBook(book));
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

        BookDAO book = bookService.getBookById(id);

        if (book == null) {
            throw new NoSuchEntryException("Book doesn't exist with the requested id=" + id);
        }

        return mapToResponse(book);
    }

    @GetMapping(path = "/books")
    public List<BookResponse> getBooks() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all books.");
        }

        return mapToResponse(bookService.getBooks());
    }

    private BookResponse mapToResponse(BookDAO bookDAO) {
        BookResponse bookResponse = modelMapper.map(bookDAO, BookResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("BookResponse: {}", gson.toJson(bookResponse));
        }

        return bookResponse;
    }

    private List<BookResponse> mapToResponse(List<BookDAO> books) {
        List<BookResponse> bookResponses = modelMapper.map(books, BOOK_RESPONSE_LIST_TYPE);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("List<BookResponse>: {}", gson.toJson(bookResponses));
        }

        return bookResponses;
    }

}
