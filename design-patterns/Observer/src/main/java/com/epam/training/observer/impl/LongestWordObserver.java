package com.epam.training.observer.impl;

import com.epam.training.observer.model.Observer;
import com.epam.training.subject.Subject;

public class LongestWordObserver extends Observer {

    private String longestWord = "";

    public LongestWordObserver(Subject subject) {
        super(subject);
    }

    public String getLongestWord() {
        return longestWord;
    }

    @Override
    public void notifyMe() {
        String subjectText = subject.getText();

        if (longestWord.length() < subjectText.length()) {
            longestWord = subjectText;
        }
    }

}
