package com.epam.mentoring.repository;

import com.epam.mentoring.domain.AuthorDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorDAO, Long> {

    @Override
    List<AuthorDAO> findAll();

    AuthorDAO findById(long id);

}
