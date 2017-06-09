package com.epam.training.observer.impl;

import com.epam.training.observer.model.Observer;
import com.epam.training.subject.Subject;

public class ReverseWordObserver extends Observer {

    private StringBuilder sb = new StringBuilder();

    public ReverseWordObserver(Subject subject) {
        super(subject);
    }

    public String getReverseText() {
        return sb.toString();
    }

    @Override
    public void notifyMe() {
        StringBuilder reverse = new StringBuilder(subject.getText());

        sb.append(reverse.reverse());
        sb.append(" ");
    }

}
