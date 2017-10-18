package com.epam.mentoring;

import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.domain.InventoryDAO;
import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.service.BookService;
import com.epam.mentoring.service.InventoryService;
import com.epam.mentoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class DummyInventoryDataPopulator {

    @Autowired
    private BookService bookService;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private UserService userService;

    public void populateInventories() {
        BookDAO bookA = bookService.getBookById(1);
        UserDAO userB = userService.getUserById(2);

        createInventoryItem(bookA, userB);
    }

    private void createInventoryItem(BookDAO book, UserDAO user) {
        InventoryDAO inventory = new InventoryDAO();

        inventory.setBook(book);
        inventory.setUserBorrowed(user);
        inventory.setReturnDate(Date.from(LocalDate.now().plus(1, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));

        inventoryService.createInventory(inventory);
    }

}
