package com.epam.mentoring.web.controller;

import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.controller.BorrowRestController;
import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.domain.BorrowDAO;
import com.epam.mentoring.request.BorrowRequest;
import com.epam.mentoring.response.BorrowResponse;
import com.epam.mentoring.service.exception.InvalidParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class BorrowController {

    private static final String BORROW_CREATE_VIEW = "ftl/borrow/borrow_create_view";
    private static final String BORROW_EDIT_VIEW = "ftl/borrow/borrow_edit_view";
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
            return getCreateModelAndViewWithErrors(result.getAllErrors());
        }

        BorrowResponse borrowResponse;
        try {
            borrowResponse = borrowRestController.createBorrow(borrowRequest);
        }
        catch (InvalidParameterException e) {
            return getCreateModelAndViewWithErrors(Arrays.asList(e.getMessage()));
        }

        ModelMap modelMap = new ModelMap();

        modelMap.put("displaySuccessMessage", true);
        modelMap.put("borrow", borrowResponse);

        return new ModelAndView(BORROW_VIEW, modelMap);
    }

    @PostMapping(path = "/borrow-edit", params = "id")
    public ModelAndView editBorrow(@RequestParam long id) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("borrow", borrowRestController.getBorrow(id));

        return new ModelAndView(BORROW_EDIT_VIEW, modelMap);
    }

    @GetMapping(path = "/borrow")
    public ModelAndView getBorrow() {
        return new ModelAndView(BORROW_SEARCH_VIEW);
    }

    @PostMapping(path = "/borrow", params = "id")
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

    @PostMapping(path = "/borrow-update")
    public ModelAndView updateBorrow(@ModelAttribute("updateBorrowForm") BorrowRequest borrowRequest, BindingResult result) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("borrow", borrowRestController.updateBorrow(borrowRequest));

        return new ModelAndView(BORROW_VIEW, modelMap);
    }

    private ModelAndView getCreateModelAndViewWithErrors(List<?> errors) {
        ModelAndView errorModelAndView = createBorrow();

        ModelMap modelMap = errorModelAndView.getModelMap();

        modelMap.put("errors", errors);

        return errorModelAndView;
    }

}
