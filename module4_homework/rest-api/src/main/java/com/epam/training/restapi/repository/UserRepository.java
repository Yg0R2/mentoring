package com.epam.training.restapi.repository;

import com.epam.training.restapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByFirstName(String firstName);

    User findById(long id);

    List<User> findByLastName(String lastName);

    User findByFirstNameAndLastName(String firstName, String lastName);

}
