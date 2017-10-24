package com.epam.mentoring;

import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.controller.InventoryRestController;
import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.api.request.InventoryRequest;
import com.epam.mentoring.api.response.BookResponse;
import com.epam.mentoring.api.response.UserResponse;
import com.epam.mentoring.mapper.BookMapper;
import com.epam.mentoring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DummyInventoryDataPopulator {

    @Autowired
    private BookRestController bookRestController;
    @Autowired
    private InventoryRestController inventoryRestController;

    @Autowired
    private BookMapper bookMapper;

    public void populateInventories() {
        BookResponse bookA = bookRestController.getBook(1);

        createInventoryItem(bookA);
    }

    private void createInventoryItem(BookResponse bookResponse) {
        InventoryRequest inventoryRequest = new InventoryRequest();

        inventoryRequest.setBook(bookMapper.mapToRequest(bookResponse));
        inventoryRequest.setAvailableCopiesNumber(1);

        inventoryRestController.createInventory(inventoryRequest);
    }

}
