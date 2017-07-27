package com.epam.mentoring.lessfour;

public class Counter {

    private volatile int count = 10;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int get() {
        return count;
    }

}
