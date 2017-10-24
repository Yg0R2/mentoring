package com.epam.mentoring.web.controller;

import com.epam.mentoring.api.controller.AuthorRestController;
import com.epam.mentoring.request.AuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthorController {

    private static final String AUTHOR_CREATE_VIEW = "ftl/author/author_create_view";
    private static final String AUTHOR_LIST_VIEW = "ftl/author/author_list_view";
    private static final String AUTHOR_SEARCH_VIEW = "ftl/author/author_search_view";
    private static final String AUTHOR_VIEW = "ftl/author/author_view";

    @Autowired
    private AuthorRestController authorRestController;

    @GetMapping(path = "/create-author")
    public ModelAndView createAuthor() {
        ModelMap modelMap = new ModelMap();

        modelMap.put("createAuthorForm", new AuthorRequest());

        return new ModelAndView(AUTHOR_CREATE_VIEW, modelMap);
    }

    @PostMapping(path = "/create-author")
    public ModelAndView createAuthor(@ModelAttribute("createAuthorForm") AuthorRequest authorRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(AUTHOR_CREATE_VIEW, "errors", result.getAllErrors());
        }

        ModelMap modelMap = new ModelMap();

        modelMap.put("displaySuccessMessage", true);
        modelMap.put("author", authorRestController.createAuthor(authorRequest));

        return new ModelAndView(AUTHOR_VIEW, modelMap);
    }

    @GetMapping(path = "/author")
    public ModelAndView getAuthor() {
        return new ModelAndView(AUTHOR_SEARCH_VIEW);
    }

    @PostMapping(path = "/author", params = "id")
    public ModelAndView getAuthor(@RequestParam long id) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("author", authorRestController.getAuthor(id));

        return new ModelAndView(AUTHOR_VIEW, modelMap);
    }

    @GetMapping(path = "/authors")
    public ModelAndView getAuthors() {
        ModelMap modelMap = new ModelMap();

        modelMap.put("authors", authorRestController.getAuthors());

        return new ModelAndView(AUTHOR_LIST_VIEW, modelMap);
    }

}
