package com.epam.mentoring;

import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.controller.BorrowRestController;
import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.domain.BorrowDAO;
import com.epam.mentoring.repository.BorrowRepository;
import com.epam.mentoring.request.BorrowRequest;
import com.epam.mentoring.response.BookResponse;
import com.epam.mentoring.response.UserResponse;
import com.epam.mentoring.mapper.BookMapper;
import com.epam.mentoring.mapper.UserMapper;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DummyBorrowDataPopulator {

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRestController bookRestController;
    @Autowired
    private UserRestController userRestController;
    @Autowired
    private BorrowRestController borrowRestController;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    public void populateBorrows() {
        BookResponse bookA = bookRestController.getBook(1);
        BookResponse bookB = bookRestController.getBook(2);
        UserResponse userB = userRestController.getUser(2);

        createBorrowItem(bookA, userB);
        createExpiredBorrowItem(bookB, userB);
    }

    private void createBorrowItem(BookResponse bookResponse, UserResponse userResponse) {
        BorrowRequest borrowRequest = new BorrowRequest();

        borrowRequest.setBook(bookMapper.mapToRequest(bookResponse));
        borrowRequest.setUserBorrowed(userMapper.mapToRequest(userResponse));
        borrowRequest.setReturnDate(DateUtils.addDays(new Date(), 5));
        borrowRequest.setReturned(false);

        borrowRestController.createBorrow(borrowRequest);
    }

    private void createExpiredBorrowItem(BookResponse bookResponse, UserResponse userResponse) {
        BorrowDAO borrow = new BorrowDAO();

        borrow.setBook(bookMapper.mapToDao(bookResponse));
        borrow.setUserBorrowed(userMapper.mapToDao(userResponse));
        borrow.setReturnDate(DateUtils.addDays(new Date(), -5));
        borrow.setReturned(false);

        borrowRepository.save(borrow);
    }

}
