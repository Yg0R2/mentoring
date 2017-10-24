package com.epam.mentoring.web.controller;

import com.epam.mentoring.api.controller.AuthorRestController;
import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.request.BookRequest;
import com.epam.mentoring.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {

    private static final String BOOK_CREATE_VIEW = "ftl/book/book_create_view";
    private static final String BOOK_LIST_VIEW = "ftl/book/book_list_view";
    private static final String BOOK_SEARCH_VIEW = "ftl/book/book_search_view";
    private static final String BOOK_VIEW = "ftl/book/book_view";

    @Autowired
    private AuthorRestController authorRestController;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private BookRestController bookRestController;

    @GetMapping(path = "/create-book")
    public ModelAndView createBook() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setAuthors(authorMapper.mapToRequest(authorRestController.getAuthors()));

        ModelMap modelMap = new ModelMap();
        modelMap.put("createBookForm", bookRequest);

        return new ModelAndView(BOOK_CREATE_VIEW, modelMap);
    }

    @PostMapping(path = "/create-book")
    public ModelAndView createBook(@ModelAttribute("createBookForm") BookRequest bookRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(BOOK_CREATE_VIEW, "errors", result.getAllErrors());
        }

        ModelMap modelMap = new ModelMap();

        modelMap.put("displaySuccessMessage", true);
        modelMap.put("book", bookRestController.createBook(bookRequest));

        return new ModelAndView(BOOK_VIEW, modelMap);
    }

    @GetMapping(path = "/book")
    public ModelAndView getBook() {
        return new ModelAndView(BOOK_SEARCH_VIEW);
    }

    @PostMapping(path = "/book", params = "id")
    public ModelAndView getBook(@RequestParam long id) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("book", bookRestController.getBook(id));

        return new ModelAndView(BOOK_VIEW, modelMap);
    }

    @PostMapping(path = "/book", params = "title")
    public ModelAndView getBook(@RequestParam String title) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("book", bookRestController.getBook(title));

        return new ModelAndView(BOOK_VIEW, modelMap);
    }

    @GetMapping(path = "/books")
    public ModelAndView getBooks() {
        ModelMap modelMap = new ModelMap();

        modelMap.put("books", bookRestController.getBooks());

        return new ModelAndView(BOOK_LIST_VIEW, modelMap);
    }

}
