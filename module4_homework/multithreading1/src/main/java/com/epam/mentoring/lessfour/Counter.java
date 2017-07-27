package com.epam.mentoring.lessfour;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private volatile AtomicInteger count = new AtomicInteger(10);

    public void increment() {
        count.incrementAndGet();
    }

    public void decrement() {
        count.decrementAndGet();
    }

    public int get() {
        return count.get();
    }

}
