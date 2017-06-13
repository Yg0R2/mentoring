package com.epam.training.reader.model;

import com.epam.training.person.Person;

import java.util.List;

public interface Reader {

    Person readPerson(String name) throws Exception;

    List<Person> readPersons() throws Exception;

}
