package com.epam.training.observer.impl;

import com.epam.training.observer.model.Observer;
import com.epam.training.subject.Subject;

public class NumberCounterObserver extends Observer {

    private int numberCount;

    public NumberCounterObserver(Subject subject) {
        super(subject);
    }

    public int getNumberCount() {
        return numberCount;
    }

    @Override
    public void notifyMe() {
        if (subject.getText().matches("^\\d+(\\.\\d+)?$")) {
            numberCount++;
        }
    }

}
