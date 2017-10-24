package com.epam.mentoring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class MainController {

    private static final String HOME_VIEW = "ftl/home";
    private static final String LINKS_VIEW = "ftl/links";
    private static final String LOGIN_VIEW = "ftl/login";
    private static final String LOGOUT_VIEW = "ftl/logout";

    @RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView home() {
        return new ModelAndView(HOME_VIEW);
    }

    @GetMapping(path = "/links")
    public ModelAndView links() {
        return new ModelAndView(LINKS_VIEW);
    }

    @GetMapping(path = "/login")
    public ModelAndView login(@RequestParam Optional<String> error) {
        return new ModelAndView(LOGIN_VIEW, "error", error);
    }

}
