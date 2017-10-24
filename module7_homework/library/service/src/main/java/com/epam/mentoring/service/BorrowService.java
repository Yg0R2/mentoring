package com.epam.mentoring.service;

import com.epam.mentoring.domain.BorrowDAO;
import com.epam.mentoring.domain.InventoryDAO;
import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.repository.BorrowRepository;
import com.epam.mentoring.service.exception.InvalidParameterException;
import com.epam.mentoring.service.exception.NoSuchEntryException;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private InventoryService inventoryService;

    @Transactional
    public BorrowDAO createBorrow(BorrowDAO borrow) {
        /*InventoryDAO inventory = inventoryService.getInventoryByBookId(borrow.getBook().getId());

        if (inventory.getAvailableCopiesNumber() < 1) {
            UserDAO userWantToBorrow = borrow.getUserBooked();

            borrow.setUserBooked(userWantToBorrow);

            borrow.setUserBorrowed(null);
        }
        else {
            int currentAmount = inventory.getAvailableCopiesNumber();

            inventory.setAvailableCopiesNumber(currentAmount - 1);
        }*/

        Date nextMonth = DateUtils.addMonths(new Date(), 1);

        Date returnDate = borrow.getReturnDate();
        if (returnDate.before(new Date())) {
            throw new InvalidParameterException("Return date should be in the future.");
        }
        else if (returnDate.after(nextMonth)) {
            throw new InvalidParameterException("Return date should maximum 1 month.");
        }

        return borrowRepository.save(borrow);
    }

    @Transactional
    public void deleteBorrow(long id) {
        try {
            borrowRepository.delete(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoSuchEntryException("Borrow doesn't exist with the requested id=" + id);
        }
    }

    public BorrowDAO getBorrowById(long id) {
        BorrowDAO request = borrowRepository.findById(id);

        if (request == null) {
            throw new NoSuchElementException("Borrow doesn't exist with the requested id=" + id);
        }

        return request;
    }

    public List<BorrowDAO> getBorrows() {
        return borrowRepository.findAll();
    }

    public List<BorrowDAO> getNotReturnedAndExpiredBorrows() {
        return borrowRepository.findByReturnedIsFalseAndReturnDateIsBefore(new Date());
    }

    @Transactional
    public BorrowDAO updateBorrow(BorrowDAO borrow) {
        BorrowDAO storedBorrow = getBorrowById(borrow.getId());

        if (borrow.isReturned()) {
            storedBorrow.setReturned(borrow.isReturned());
        }

        if (Objects.nonNull(borrow.getReturnDate())) {
            storedBorrow.setReturnDate(borrow.getReturnDate());
        }

        return storedBorrow;
    }

}
