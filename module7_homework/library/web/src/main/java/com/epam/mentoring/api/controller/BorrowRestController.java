package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.api.request.BorrowRequest;
import com.epam.mentoring.api.response.BorrowResponse;
import com.epam.mentoring.domain.BorrowDAO;
import com.epam.mentoring.mapper.BorrowMapper;
import com.epam.mentoring.service.BorrowService;
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
public class BorrowRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowRestController.class);

    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BorrowService borrowService;

    @PostMapping(path = "/borrow", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BorrowResponse createBorrow(@RequestBody @Valid BorrowRequest borrowRequest) {
        BorrowDAO borrow = borrowMapper.mapToDao(borrowRequest);

        BorrowDAO storedBorrow = borrowService.createBorrow(borrow);

        return borrowMapper.mapToResponse(storedBorrow);
    }

    @DeleteMapping(path = "/borrow")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteBorrow(@RequestParam long id) {
        borrowService.deleteBorrow(id);
    }

    @GetMapping(path = "/borrow")
    public BorrowResponse getBorrow() {
        throw new MissingRequestParameterException("Missing request parameter.");
    }

    @GetMapping(path = "/borrow", params = {"id"})
    @ResponseStatus(value = HttpStatus.OK)
    public BorrowResponse getBorrow(@RequestParam long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get borrow by Id: {}", id);
        }

        BorrowDAO storedBorrow = borrowService.getBorrowById(id);

        return borrowMapper.mapToResponse(storedBorrow);
    }

    @GetMapping(path = "/borrows")
    @ResponseStatus(value = HttpStatus.OK)
    public List<BorrowResponse> getBorrows() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all borrows.");
        }

        List<BorrowDAO> storedBorrows = borrowService.getBorrows();

        return borrowMapper.mapToResponse(storedBorrows);
    }

    @GetMapping(path = "/expired-borrows")
    public List<BorrowResponse> getExpiredBorrows() {
        List<BorrowDAO> storedBorrows = borrowService.getOngoingAndExpiredBorrows();

        return borrowMapper.mapToResponse(storedBorrows);
    }


    @PutMapping(path = "/borrow", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BorrowResponse updateBorrow(@RequestBody @Valid BorrowRequest borrowRequest) {
        BorrowDAO borrow = borrowMapper.mapToDao(borrowRequest);

        BorrowDAO storedBorrow = borrowService.updateBorrow(borrow);

        return borrowMapper.mapToResponse(storedBorrow);
    }

}
