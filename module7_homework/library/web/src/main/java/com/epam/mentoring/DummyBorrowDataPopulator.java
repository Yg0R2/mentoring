package com.epam.mentoring;

import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.controller.BorrowRestController;
import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.request.BorrowRequest;
import com.epam.mentoring.response.BookResponse;
import com.epam.mentoring.response.UserResponse;
import com.epam.mentoring.mapper.BookMapper;
import com.epam.mentoring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class DummyBorrowDataPopulator {

    @Autowired
    private BookRestController bookRestController;
    @Autowired
    private BorrowRestController borrowRestController;
    @Autowired
    private UserRestController userRestController;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    public void populateBorrows() {
        BookResponse bookA = bookRestController.getBook(1);
        BookResponse bookB = bookRestController.getBook(2);
        UserResponse userB = userRestController.getUser(2);

        createBorrowItem(bookA, userB, Date.from(LocalDate.now().plus(1, ChronoUnit.MONTHS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        createBorrowItem(bookB, userB, Date.from(LocalDate.now().minus(1, ChronoUnit.DAYS).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
    }

    private void createBorrowItem(BookResponse bookResponse, UserResponse userResponse, Date returnDate) {
        BorrowRequest borrowRequest = new BorrowRequest();

        borrowRequest.setBook(bookMapper.mapToRequest(bookResponse));
        borrowRequest.setUserBorrowed(userMapper.mapToRequest(userResponse));
        borrowRequest.setReturnDate(returnDate);
        borrowRequest.setOngoing(true);

        borrowRestController.createBorrow(borrowRequest);
    }

}
