package com.epam.mentoring.repository;

import com.epam.mentoring.domain.BorrowDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BorrowRepository extends CrudRepository<BorrowDAO, Long> {

    @Override
    List<BorrowDAO> findAll();

    BorrowDAO findById(long id);

    //@Query("SELECT r FROM BorrowDAO r WHERE r.returnDate < CURRENT_DATE AND r.ongoing = true")
    List<BorrowDAO> findByOngoingIsTrueAndReturnDateIsBefore(Date beforeDate);

}
