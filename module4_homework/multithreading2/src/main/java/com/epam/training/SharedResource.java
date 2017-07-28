package com.epam.training;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedResource {

    private volatile AtomicInteger counter;

    public SharedResource(int initAmount) {
        counter = new AtomicInteger(initAmount);
    }

    public int decrement() {
        return counter.decrementAndGet();
    }

    public int get() {
        return counter.get();
    }

    public int increment() {
        return counter.incrementAndGet();
    }

}
