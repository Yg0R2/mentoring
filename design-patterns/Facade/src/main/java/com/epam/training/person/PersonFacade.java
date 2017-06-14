package com.epam.training.person;

import com.epam.training.writer.model.Writer;

public class PersonFacade {

    public static final int AMOUNT_OF_INCREASE_IQ = 50;
    public static final int AMOUNT_OF_MOVE_IQ = 10;

    private Writer writer;

    public PersonFacade(Writer writer) {
        this.writer = writer;
    }

    public void moveIQ(Person person1, Person person2) throws Exception {
        int person1Iq = person1.getIq();
        int person2Iq = person2.getIq();

        person1.setIq(person1Iq - AMOUNT_OF_MOVE_IQ);
        person2.setIq(person2Iq + AMOUNT_OF_MOVE_IQ);

        writer.updatePerson(person1);
        writer.updatePerson(person2);
    }

    public Person smarter(Person person1, Person person2) {
        if (person1.getIq() > person2.getIq()) {
            return person1;
        }

        return person2;
    }

    public void updatePersonIq(Person person) throws Exception {
        int personIq = person.getIq();

        person.setIq(personIq + AMOUNT_OF_INCREASE_IQ);

        writer.updatePerson(person);
    }

}
