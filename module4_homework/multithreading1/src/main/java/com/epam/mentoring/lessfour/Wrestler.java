package com.epam.mentoring.lessfour;

import java.util.Random;

public class Wrestler implements Runnable {
    private Counter counter;
    private boolean increment;
    private Random rand;

    public Wrestler(Counter counter, boolean increment) {
        this.counter = counter;
        this.increment = increment;
        rand = new Random();
    }

    @Override
    public void run() {
        while (true) {
            doRun();

            try {
                Thread.sleep(rand.nextInt(100));
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }

    private void doRun() {
        if (increment) {
            counter.increment();
        }
        else {
            counter.decrement();
        }

        int counterCurrentValue = counter.get();
        if (counterCurrentValue < 0) {
            throw new IllegalStateException("We have below zero!");
        }

        System.out.println("Wrestler" + Thread.currentThread().getName() + " " + counterCurrentValue);
    }

}
