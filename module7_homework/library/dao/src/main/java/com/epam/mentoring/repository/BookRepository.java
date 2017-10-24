package com.epam.mentoring.repository;

import com.epam.mentoring.domain.BookDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookDAO, Long> {

    @Override
    List<BookDAO> findAll();

    BookDAO findById(long id);

    BookDAO findByTitle(String title);

}
