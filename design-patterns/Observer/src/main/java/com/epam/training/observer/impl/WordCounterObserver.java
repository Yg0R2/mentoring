package com.epam.training.observer.impl;

import com.epam.training.observer.model.Observer;
import com.epam.training.subject.Subject;

public class WordCounterObserver extends Observer {

    private int wordCount;

    public WordCounterObserver(Subject subject) {
        super(subject);
    }

    public int getWordCount() {
        return wordCount;
    }

    @Override
    public void notifyMe() {
        wordCount++;
    }

}
