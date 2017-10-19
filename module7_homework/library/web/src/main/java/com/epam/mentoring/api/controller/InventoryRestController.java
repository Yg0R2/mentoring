package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.api.response.InventoryResponse;
import com.epam.mentoring.mapper.InventoryMapper;
import com.epam.mentoring.domain.InventoryDAO;
import com.epam.mentoring.service.InventoryService;
import com.epam.mentoring.api.request.InventoryRequest;
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
    private InventoryMapper inventoryMapper;
    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path = "/inventory", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public InventoryResponse createInventory(@RequestBody @Valid InventoryRequest inventoryRequest) {
        InventoryDAO inventory = inventoryMapper.mapToDao(inventoryRequest);

        InventoryDAO storedInventory = inventoryService.createInventory(inventory);

        return inventoryMapper.mapToResponse(storedInventory);
    }

    @DeleteMapping(path = "inventory")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteInventory(@RequestParam long id) {
        inventoryService.deleteInventory(id);
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

        InventoryDAO storedInventory = inventoryService.getInventoryById(id);

        return inventoryMapper.mapToResponse(storedInventory);
    }

    @GetMapping(path = "/inventories")
    @ResponseStatus(value = HttpStatus.OK)
    public List<InventoryResponse> getLibraries() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all inventories.");
        }

        List<InventoryDAO> storedInventories = inventoryService.getInventories();

        return inventoryMapper.mapToResponse(storedInventories);
    }

    @PutMapping(path = "/inventory", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public InventoryResponse updateInventory(@RequestBody @Valid InventoryRequest inventoryRequest) {
        InventoryDAO inventory = inventoryMapper.mapToDao(inventoryRequest);

        InventoryDAO storedInventory = inventoryService.updateInventory(inventory);

        return inventoryMapper.mapToResponse(storedInventory);
    }

}
