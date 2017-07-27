package com.epam.mentoring.lessfour;

public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Runnable runnable1 = new Wrestler(counter, true);
        Runnable runnable2 = new Wrestler(counter, false);

        Opposition instance = new Opposition(runnable1, runnable2);

        instance.start();
    }

}
