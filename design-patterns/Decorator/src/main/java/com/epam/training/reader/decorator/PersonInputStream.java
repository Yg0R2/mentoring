package com.epam.training.reader.decorator;

import com.epam.training.person.Person;
import com.epam.training.reader.model.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonInputStream extends ReaderDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonInputStream.class);

    public PersonInputStream(Reader readerToBeDecorated) {
        super(readerToBeDecorated);
    }

    @Override
    public Person readPerson(String name) throws Exception {
        return super.readPerson(decoratePerson(name));
    }

    private String decoratePerson(String name) {
        LOGGER.info("Decorating person in PersonInputStream.");

        if (Character.isUpperCase(name.charAt(0))) {
            return name;
        }

        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

}
