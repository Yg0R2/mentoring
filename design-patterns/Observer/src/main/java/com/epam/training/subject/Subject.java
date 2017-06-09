package com.epam.training.subject;

import com.epam.training.observer.model.Observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<>();
    private String text;

    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    public String getText() {
        return text;
    }

    public void notifyAllObservers() {
        observers.forEach(o -> o.notifyMe());
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void setText(String text) {
        this.text = text;
        notifyAllObservers();
    }

}
