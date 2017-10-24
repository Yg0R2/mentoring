package com.epam.mentoring.web.controller;

import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.request.UserRequest;
import com.epam.mentoring.domain.UserRole;
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
public class UserController {

    private static final String USER_CREATE_VIEW = "ftl/user/user_create_view";
    private static final String USER_EDIT_VIEW = "ftl/user/user_edit_view";
    private static final String USER_LIST_VIEW = "ftl/user/user_list_view";
    private static final String USER_SEARCH_VIEW = "ftl/user/user_search_view";
    private static final String USER_VIEW = "ftl/user/user_view";

    @Autowired
    private UserRestController userRestController;

    @GetMapping(path = "/create-user")
    public ModelAndView createUser() {
        ModelMap modelMap = new ModelMap();

        modelMap.put("createUserForm", new UserRequest());
        modelMap.put("availableUserRoles", UserRole.values());

        return new ModelAndView(USER_CREATE_VIEW, modelMap);
    }

    @PostMapping(path = "/create-user")
    public ModelAndView createUser(@ModelAttribute("createUserForm") UserRequest userRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(USER_CREATE_VIEW, "errors", result.getAllErrors());
        }

        ModelMap modelMap = new ModelMap();

        modelMap.put("displaySuccessMessage", true);
        modelMap.put("user", userRestController.createUser(userRequest));

        return new ModelAndView(USER_VIEW, modelMap);
    }

    @PostMapping(path = "/user-edit", params = "id")
    public ModelAndView editUser(@RequestParam long id) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("availableUserRoles", UserRole.values());
        modelMap.put("user", userRestController.getUser(id));

        return new ModelAndView(USER_EDIT_VIEW, modelMap);
    }

    @GetMapping(path = "/user")
    public ModelAndView getUser() {
        return new ModelAndView(USER_SEARCH_VIEW);
    }

    @PostMapping(path = "/user")
    public ModelAndView getUser(@RequestParam long id) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("user", userRestController.getUser(id));

        return new ModelAndView(USER_VIEW, modelMap);
    }

    @GetMapping(path = "/users")
    public ModelAndView getUsers() {
        ModelMap modelMap = new ModelMap();

        modelMap.put("users", userRestController.getUsers());

        return new ModelAndView(USER_LIST_VIEW, modelMap);
    }

    @PostMapping(path = "/user-update")
    public ModelAndView updateUser(@ModelAttribute("updateUserForm") UserRequest userRequest, BindingResult result) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("user", userRestController.updateUser(userRequest));

        return new ModelAndView(USER_VIEW, modelMap);
    }

}
