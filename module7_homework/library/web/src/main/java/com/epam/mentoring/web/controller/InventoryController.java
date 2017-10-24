package com.epam.mentoring.web.controller;

import com.epam.mentoring.api.controller.InventoryRestController;
import com.epam.mentoring.request.InventoryRequest;
import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.mapper.BookMapper;
import com.epam.mentoring.mapper.InventoryMapper;
import com.epam.mentoring.service.BookService;
import com.epam.mentoring.service.InventoryService;
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
public class InventoryController {

    private static final String INVENTORY_CREATE_VIEW = "ftl/inventory/inventory_create_view";
    private static final String INVENTORY_LIST_VIEW = "ftl/inventory/inventory_list_view";
    private static final String INVENTORY_SEARCH_VIEW = "ftl/inventory/inventory_search_view";
    private static final String INVENTORY_VIEW = "ftl/inventory/inventory_view";

    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private InventoryRestController inventoryRestController;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private InventoryMapper inventoryMapper;

    @GetMapping(path = "/create-inventory")
    public ModelAndView createInventory() {
        List<BookDAO> allBooks = bookService.getBooks();
        List<BookDAO> booksInInventory = inventoryService.getBooksFromInventory();

        allBooks.removeAll(booksInInventory);

        ModelMap modelMap = new ModelMap();

        modelMap.put("createInventoryForm", new InventoryRequest());
        modelMap.put("availableBooks", bookMapper.mapToRequest(allBooks));

        return new ModelAndView(INVENTORY_CREATE_VIEW, modelMap);
    }

    @PostMapping(path = "/create-inventory")
    public ModelAndView createInventory(@ModelAttribute("createInventoryForm") InventoryRequest inventoryRequest, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView(INVENTORY_CREATE_VIEW, "errors", result.getAllErrors());
        }

        ModelMap modelMap = new ModelMap();

        modelMap.put("displaySuccessMessage", true);
        modelMap.put("inventory", inventoryRestController.createInventory(inventoryRequest));

        return new ModelAndView(INVENTORY_VIEW, modelMap);
    }

    @GetMapping(path = "/inventory")
    public ModelAndView getInventory() {
        return new ModelAndView(INVENTORY_SEARCH_VIEW);
    }

    @PostMapping(path = "/inventory")
    public ModelAndView getInventory(@RequestParam long id) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("inventory", inventoryRestController.getInventory(id));

        return new ModelAndView(INVENTORY_VIEW, modelMap);
    }

    @GetMapping(path = "/inventories")
    public ModelAndView getInventories() {
        ModelMap modelMap = new ModelMap();

        modelMap.put("inventories", inventoryRestController.getInventories());

        return new ModelAndView(INVENTORY_LIST_VIEW, modelMap);
    }

}
