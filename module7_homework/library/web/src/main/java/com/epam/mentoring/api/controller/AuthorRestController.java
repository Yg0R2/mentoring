package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.api.exception.NoSuchEntryException;
import com.epam.mentoring.api.request.AuthorRequest;
import com.epam.mentoring.api.response.AuthorResponse;
import com.epam.mentoring.api.utils.ModelMapperUtils;
import com.epam.mentoring.domain.AuthorDAO;
import com.epam.mentoring.service.AuthorService;
import com.google.gson.Gson;
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
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AuthorRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorRestController.class);

    @Autowired
    private AuthorService authorService;
    @Autowired
    private Gson gson;
    @Autowired
    private ModelMapperUtils modelMapperUtils;

    @PostMapping(path = "/author", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorResponse createAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("AuthorRequest: {}", gson.toJson(authorRequest));
        }

        AuthorDAO author = new AuthorDAO(authorRequest.getFirstName(), authorRequest.getLastName());

        return mapToResponse(authorService.createAuthor(author));
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

        AuthorDAO author = authorService.getAuthorById(id);

        if (author == null) {
            throw new NoSuchEntryException("Author doesn't exist with the requested id=" + id);
        }

        return mapToResponse(author);
    }

    @GetMapping(path = "/authors")
    public List<AuthorResponse> getAuthors() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all authors.");
        }

        return mapToResponse(authorService.getAuthors());
    }

    private AuthorResponse mapToResponse(AuthorDAO author) {
        return modelMapperUtils.map(author, ModelMapperUtils.AUTHOR_RESPONSE_TYPE);
    }

    private List<AuthorResponse> mapToResponse(List<AuthorDAO> authors) {
        return modelMapperUtils.map(authors, ModelMapperUtils.AUTHOR_RESPONSE_LIST_TYPE);
    }

}
