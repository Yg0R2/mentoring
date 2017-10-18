package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.api.response.InventoryResponse;
import com.epam.mentoring.api.utils.ModelMapperUtils;
import com.epam.mentoring.domain.InventoryDAO;
import com.epam.mentoring.service.InventoryService;
import com.epam.mentoring.api.request.InventoryRequest;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class InventoryRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryRestController.class);

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private Gson gson;
    @Autowired
    private ModelMapperUtils modelMapperUtils;

    @PostMapping(path = "/inventory", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public InventoryResponse createInventory(@RequestBody @Valid InventoryRequest inventoryRequest) {
        InventoryDAO inventory = getInventoryFromRequest(inventoryRequest);

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

        return  mapToResponse(inventoryService.getInventoryById(id));
    }

    @GetMapping(path = "/inventories")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InventoryResponse> getLibraries() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all inventories.");
        }

        return mapToResponse(inventoryService.getInventories());
    }

    @PutMapping(path = "/inventory", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public InventoryResponse updateInventory(@RequestBody @Valid InventoryRequest inventoryRequest) {
        InventoryDAO inventory = getInventoryFromRequest(inventoryRequest);

        return mapToResponse(inventoryService.updateInventory(inventory));
    }

    private InventoryDAO getInventoryFromRequest(InventoryRequest inventoryRequest) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("InventoryRequest: {}", gson.toJson(inventoryRequest));
        }

        InventoryDAO inventory = new InventoryDAO();

        inventory.setId(inventoryRequest.getId());
        inventory.setBook(modelMapperUtils.map(inventoryRequest.getBook(), ModelMapperUtils.BOOK_TYPE));
        inventory.setRequestedForBorrow(modelMapperUtils.map(inventoryRequest.getRequestedForBorrow(), ModelMapperUtils.USER_LIST_TYPE));
        inventory.setReturnDate(inventoryRequest.getReturnDate());
        inventory.setUserBorrowed(modelMapperUtils.map(inventoryRequest.getUserBorrowed(), ModelMapperUtils.USER_TYPE));

        return inventory;
    }

    private InventoryResponse mapToResponse(InventoryDAO inventory) {
        return modelMapperUtils.map(inventory, InventoryResponse.class);
    }

    private List<InventoryResponse> mapToResponse(List<InventoryDAO> inventories) {
        return modelMapperUtils.map(inventories, ModelMapperUtils.INVENTORY_RESPONSE_LIST_TYPE);
    }

}
