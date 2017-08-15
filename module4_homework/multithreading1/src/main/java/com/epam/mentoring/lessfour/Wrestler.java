package com.epam.mentoring.lessfour;

import java.util.Random;

public class Wrestler implements Runnable {

    private final Counter counter;
    private boolean increment;

    private static final Random RND = new Random();

    public Wrestler(Counter counter, boolean increment) {
        this.counter = counter;
        this.increment = increment;
    }

    @Override
    public void run() {
        while (true) {
            try {
                doRun();

                Thread.sleep(RND.nextInt(100));
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }

    private void doRun() throws InterruptedException {
        int counterCurrentValue;

        if (increment) {
            counterCurrentValue = counter.increment();
        }
        else {
            counterCurrentValue = counter.decrement();
        }

        System.out.println("Wrestler" + Thread.currentThread().getName() + " " + counterCurrentValue);

        if (counterCurrentValue < 0) {
            throw new IllegalStateException("We have below zero!");
        }
    }

}
