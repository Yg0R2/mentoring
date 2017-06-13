package com.epam.training.reader.decorator;

import com.epam.training.person.Person;
import com.epam.training.reader.model.Reader;

import java.util.List;

public abstract class ReaderDecorator implements Reader {

    private Reader readerToBeDecorated;

    public ReaderDecorator(Reader readerToBeDecorated) {
        this.readerToBeDecorated = readerToBeDecorated;
    }

    @Override
    public Person readPerson(String name) throws Exception {
        return readerToBeDecorated.readPerson(name);
    }

    @Override
    public List<Person> readPersons() throws Exception {
        return readerToBeDecorated.readPersons();
    }

}
