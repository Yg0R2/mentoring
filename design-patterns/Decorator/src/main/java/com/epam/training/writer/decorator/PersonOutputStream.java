package com.epam.training.writer.decorator;

import com.epam.training.person.Person;
import com.epam.training.writer.model.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonOutputStream extends WriterDecorator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonOutputStream.class);

    public PersonOutputStream(Writer writerToBeDecorated) {
        super(writerToBeDecorated);
    }

    @Override
    public void writePerson(Person person) throws Exception {
        super.writePerson(decoratePerson(person));
    }

    private Person decoratePerson(Person person) {
        LOGGER.info("Decorating person in PersonOutputStream.");

        String personName = person.getName();

        if (Character.isUpperCase(personName.charAt(0))) {
            return person;
        }

        personName = personName.substring(0, 1).toUpperCase() + personName.substring(1);

        return new Person(personName, person.getAge());
    }

}
