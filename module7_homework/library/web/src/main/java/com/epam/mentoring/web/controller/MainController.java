package com.epam.mentoring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private static final String INDEX_VIEW = "ftl/index";

    @GetMapping(path = "/")
    public ModelAndView index() {
        return new ModelAndView(INDEX_VIEW);
    }

}
