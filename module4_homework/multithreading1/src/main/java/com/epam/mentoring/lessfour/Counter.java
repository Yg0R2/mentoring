package com.epam.mentoring.lessfour;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private AtomicInteger count = new AtomicInteger(10);

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public int increment() {
        int currentValue;

        lock.lock();

        try {
            currentValue = count.incrementAndGet();

            condition.signalAll();
        }
        finally {
            lock.unlock();
        }

        return currentValue;
    }

    public int decrement() throws InterruptedException {
        int currentValue;

        lock.lock();

        while (count.get() == 0) {
            condition.await();
        }

        try {
            currentValue = count.decrementAndGet();
        }
        finally {
            lock.unlock();
        }

        return currentValue;
    }

    public int get() {
        return count.get();
    }

}
