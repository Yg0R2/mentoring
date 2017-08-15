package com.epam.mentoring.lessfour;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread incrementThread = new Thread(new Wrestler(counter, true));
        Thread decrementThread = new Thread(new Wrestler(counter, false));

        Opposition opposition = new Opposition(Arrays.asList(incrementThread, decrementThread));
        opposition.start();
    }

}
