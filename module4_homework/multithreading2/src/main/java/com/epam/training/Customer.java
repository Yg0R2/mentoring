package com.epam.training;

import java.util.Random;

public abstract class Customer extends Thread {

    private final SharedResource sharedResource;

    private static Random RND = new Random();

    public Customer(SharedResource sharedResource, String threadName) {
        super(threadName);

        this.sharedResource = sharedResource;
    }

    @Override
    public final void run() {
        while (true) {
            try {
                Thread.sleep(RND.nextInt(100));

                doRun();
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }

    protected abstract boolean evaluateCondition(final SharedResource sharedResource);

    protected abstract int executeOperation(final SharedResource sharedResource);

    private void doRun() throws InterruptedException {
        synchronized (sharedResource) {
            if (evaluateCondition(sharedResource)) {
                sharedResource.wait();
            }

            int value = executeOperation(sharedResource);

            System.out.println(getName() + " - New value: " + value);

            if (value < 0) {
                throw new IllegalStateException("We have below zero!");
            }

            sharedResource.notifyAll();
        }
    }

}
