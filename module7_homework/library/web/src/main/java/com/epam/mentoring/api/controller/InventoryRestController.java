package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.api.exception.NoSuchEntryException;
import com.epam.mentoring.api.request.InventoryRequest;
import com.epam.mentoring.api.response.InventoryResponse;
import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.domain.InventoryDAO;
import com.epam.mentoring.service.InventoryService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InventoryRestController {

    private static final Type INVENTORY_RESPONSE_LIST_TYPE = new TypeToken<List<InventoryResponse>>() {}.getType();
    private static final Type USER_LIST_TYPE = new TypeToken<List<UserDAO>>() {}.getType();
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryRestController.class);

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private Gson gson;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/inventory", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public InventoryResponse createInventory(@RequestBody @Valid InventoryRequest inventoryRequest) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InventoryRequest: {}", gson.toJson(inventoryRequest));
        }

        InventoryDAO inventory = new InventoryDAO();

        inventory.setBook(modelMapper.map(inventoryRequest.getBook(), BookDAO.class));
        inventory.setRequestedForBorrow(modelMapper.map(inventoryRequest.getRequestedForBorrow(), USER_LIST_TYPE));
        inventory.setReturnDate(inventoryRequest.getReturnDate());
        inventory.setUserBorrowed(modelMapper.map(inventoryRequest.getUserBorrowed(), UserDAO.class));

        return mapToResponse(inventoryService.createInventory(inventory));
    }

    @GetMapping(path = "/inventory")
    public InventoryResponse getInventory() {
        throw new MissingRequestParameterException("Missing request parameter.");
    }

    @GetMapping(path = "/inventory", params = {"id"})
    @ResponseStatus(value = HttpStatus.OK)
    public InventoryResponse getInventory(@RequestParam long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get inventory by Id: {}", id);
        }

        InventoryDAO inventory =inventoryService.getInventoryById(id);

        if (inventory == null) {
            throw new NoSuchEntryException("Inventory doesn't exist with the requested id=" + id);
        }

        return  mapToResponse(inventory);
    }

    @GetMapping(path = "/inventories")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InventoryResponse> getInventories() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all inventories.");
        }

        return mapToResponse(inventoryService.getInventories());
    }

    private InventoryResponse mapToResponse(InventoryDAO inventory) {
        InventoryResponse inventoryResponse = modelMapper.map(inventory, InventoryResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InventoryResponse: {}", gson.toJson(inventoryResponse));
        }

        return inventoryResponse;
    }

    private List<InventoryResponse> mapToResponse(List<InventoryDAO> inventory) {
        List<InventoryResponse> inventoryResponses = modelMapper.map(inventory, INVENTORY_RESPONSE_LIST_TYPE);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InventoryResponse: {}", gson.toJson(inventoryResponses));
        }

        return inventoryResponses;
    }

}
