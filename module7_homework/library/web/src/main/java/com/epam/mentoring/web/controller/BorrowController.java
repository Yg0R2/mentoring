package com.epam.mentoring.web.controller;

import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.controller.BorrowRestController;
import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.api.request.BorrowRequest;
import com.epam.mentoring.api.response.BookResponse;
import com.epam.mentoring.api.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BorrowController {

    private static final String BORROW_CREATE_VIEW = "ftl/borrow/borrow_create_view";
    private static final String BORROW_LIST_VIEW = "ftl/borrow/borrow_list_view";
    private static final String BORROW_SEARCH_VIEW = "ftl/borrow/borrow_search_view";
    private static final String BORROW_VIEW = "ftl/borrow/borrow_view";

    @Autowired
    private BookRestController bookRestController;
    @Autowired
    private UserRestController userRestController;
    @Autowired
    private BorrowRestController borrowRestController;

    @GetMapping(path = "/create-borrow")
    public ModelAndView createBorrow() {
        ModelMap modelMap = new ModelMap();

        modelMap.put("createBorrowForm", new BorrowRequest());
        modelMap.put("books", bookRestController.getBooks());
        modelMap.put("users", userRestController.getUsers());

        return new ModelAndView(BORROW_CREATE_VIEW, modelMap);
    }

    @PostMapping(path = "/create-borrow")
    public ModelAndView createBorrow(@ModelAttribute("createBorrowForm") BorrowRequest borrowRequest, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView defaultCreateForm = createBorrow();
            defaultCreateForm.getModelMap().put("errors", result.getAllErrors());

            return defaultCreateForm;
        }

        ModelMap modelMap = new ModelMap();

        modelMap.put("displaySuccessMessage", true);
        modelMap.put("borrow", borrowRestController.createBorrow(borrowRequest));

        return new ModelAndView(BORROW_VIEW, modelMap);
    }

    @GetMapping(path = "/borrow")
    public ModelAndView getBorrow() {
        return new ModelAndView(BORROW_SEARCH_VIEW);
    }

    @PostMapping(path = "/borrow")
    public ModelAndView getBorrow(@RequestParam long id) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("borrow", borrowRestController.getBorrow(id));

        return new ModelAndView(BORROW_VIEW, modelMap);
    }

    @GetMapping(path = "/borrows")
    public ModelAndView getBorrows() {
        ModelMap modelMap = new ModelMap();

        modelMap.put("borrows", borrowRestController.getBorrows());

        return new ModelAndView(BORROW_LIST_VIEW, modelMap);
    }

}
