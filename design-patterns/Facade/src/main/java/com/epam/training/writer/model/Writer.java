package com.epam.training.writer.model;

import com.epam.training.person.Person;

public interface Writer {

    void updatePerson(Person person) throws Exception;

    void writePerson(Person person) throws Exception;

}
