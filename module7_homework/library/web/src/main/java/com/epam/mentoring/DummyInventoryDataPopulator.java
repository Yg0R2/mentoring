package com.epam.mentoring;

import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.controller.InventoryRestController;
import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.api.request.InventoryRequest;
import com.epam.mentoring.api.response.BookResponse;
import com.epam.mentoring.api.response.UserResponse;
import com.epam.mentoring.api.utils.ModelMapperUtils;
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
    private ModelMapperUtils modelMapperUtils;

    public void populateInventories() {
        BookResponse bookA = bookRestController.getBook(1);
        UserResponse userB = userRestController.getUser(2);

        createInventoryItem(bookA, userB);
    }

    private void createInventoryItem(BookResponse bookResponse, UserResponse userRequest) {
        InventoryRequest inventoryRequest = new InventoryRequest();

        inventoryRequest.setBook(modelMapperUtils.map(bookResponse, ModelMapperUtils.BOOK_REQUEST_TYPE));
        inventoryRequest.setUserBorrowed(modelMapperUtils.map(userRequest, ModelMapperUtils.USER_REQUEST_TYPE));
        inventoryRequest.setReturnDate(Date.from(LocalDate.now().plus(1, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        inventoryRestController.createInventory(inventoryRequest);
    }

}
