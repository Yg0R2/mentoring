package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.request.AuthorRequest;
import com.epam.mentoring.response.AuthorResponse;
import com.epam.mentoring.mapper.AuthorMapper;
import com.epam.mentoring.domain.AuthorDAO;
import com.epam.mentoring.service.AuthorService;
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
public class AuthorRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRestController.class);

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private AuthorService authorService;


    @PostMapping(path = "/author", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorResponse createAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        AuthorDAO author = authorMapper.mapToDao(authorRequest);

        AuthorDAO storedAuthor = authorService.createAuthor(author);

        return authorMapper.mapToResponse(storedAuthor);
    }

    @DeleteMapping(path = "/author")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAuthor(@RequestParam long id) {
        authorService.deleteAuthor(id);
    }

    @GetMapping(path = "/author")
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AuthorResponse getAuthor() {
        throw new MissingRequestParameterException("Missing request parameter");
    }

    @GetMapping(path = "/author", params = {"id"})
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorResponse getAuthor(@RequestParam long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get author by Id: {}", id);
        }

        AuthorDAO storedAuthor = authorService.getAuthorById(id);

        return authorMapper.mapToResponse(storedAuthor);
    }

    @GetMapping(path = "/authors")
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuthorResponse> getAuthors() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all authors.");
        }

        List<AuthorDAO> authors = authorService.getAuthors();

        return authorMapper.mapToResponse(authors);
    }

    @PutMapping(path = "/author", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorResponse updateAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        AuthorDAO author = authorMapper.mapToDao(authorRequest);

        AuthorDAO storedAuthor = authorService.updateAuthor(author);

        return authorMapper.mapToResponse(storedAuthor);
    }

}
