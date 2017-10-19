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
import java.util.Date;

@Component
public class DummyInventoryDataPopulator {

    @Autowired
    private BookRestController bookRestController;
    @Autowired
    private InventoryRestController inventoryRestController;
    @Autowired
    private UserRestController userRestController;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    public void populateInventories() {
        BookResponse bookA = bookRestController.getBook(1);
        UserResponse userB = userRestController.getUser(2);

        createInventoryItem(bookA, userB);
    }

    private void createInventoryItem(BookResponse bookResponse, UserResponse userResponse) {
        InventoryRequest inventoryRequest = new InventoryRequest();

        inventoryRequest.setBook(bookMapper.mapToRequest(bookResponse));
        inventoryRequest.setUserBorrowed(userMapper.mapToRequest(userResponse));
        inventoryRequest.setReturnDate(Date.from(LocalDate.now().plus(1, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        inventoryRestController.createInventory(inventoryRequest);
    }

}
