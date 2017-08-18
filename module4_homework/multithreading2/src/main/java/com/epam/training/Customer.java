package com.epam.training;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public abstract class Customer extends Thread {

    private final BlockingQueue<Object> blockingQueue;

    private static Random RND = new Random();

    public Customer(BlockingQueue<Object> blockingQueue, String threadName) {
        super(threadName);

        this.blockingQueue = blockingQueue;
    }

    @Override
    public final void run() {
        while (true) {
            try {
                Thread.sleep(RND.nextInt(100));

                if (evaluateCondition(blockingQueue)) {
                    executeOperation(blockingQueue);

                    System.out.println(getName() + " - Current size: " + blockingQueue.size());
                }
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }

    protected abstract boolean evaluateCondition(final BlockingQueue<Object> blockingQueue);

    protected abstract void executeOperation(final BlockingQueue<Object> blockingQueue) throws InterruptedException;

}
