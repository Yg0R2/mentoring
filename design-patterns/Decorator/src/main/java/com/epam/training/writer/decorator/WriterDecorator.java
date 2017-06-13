package com.epam.training.writer.decorator;

import com.epam.training.person.Person;
import com.epam.training.writer.model.Writer;

public abstract class WriterDecorator implements Writer {

    private Writer writerToBeDecorated;

    public WriterDecorator(Writer writerToBeDecorated) {
        this.writerToBeDecorated = writerToBeDecorated;
    }

    @Override
    public void writePerson(Person person) throws Exception {
        writerToBeDecorated.writePerson(person);
    }

}
