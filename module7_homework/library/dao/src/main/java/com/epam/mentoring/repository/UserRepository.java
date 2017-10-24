package com.epam.mentoring.repository;

import com.epam.mentoring.domain.UserDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserDAO, Long> {

    @Override
    List<UserDAO> findAll();

    UserDAO findByEmailAddress(String emailAddress);

    UserDAO findById(long id);

}
