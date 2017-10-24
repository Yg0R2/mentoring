package com.epam.mentoring.service;

import com.epam.mentoring.domain.BorrowDAO;
import com.epam.mentoring.repository.BorrowRepository;
import com.epam.mentoring.service.exception.NoSuchEntryException;
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

    @Transactional
    public BorrowDAO createBorrow(BorrowDAO request) {
        return borrowRepository.save(request);
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

    public List<BorrowDAO> getOngoingAndExpiredBorrows() {
        return borrowRepository.findByOngoingIsTrueAndReturnDateIsBefore(new Date());
    }

    @Transactional
    public BorrowDAO updateBorrow(BorrowDAO borrow) {
        BorrowDAO storedBorrow = getBorrowById(borrow.getId());

        if (borrow.isOngoing()) {
            storedBorrow.setOngoing(borrow.isOngoing());
        }

        if (Objects.nonNull(borrow.getReturnDate())) {
            storedBorrow.setReturnDate(borrow.getReturnDate());
        }

        return storedBorrow;
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

}