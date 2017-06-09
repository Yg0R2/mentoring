package com.epam.training.observer.model;

import com.epam.training.subject.Subject;

public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    public abstract void notifyMe();

}
