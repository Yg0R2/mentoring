package com.epam.training;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Customer {

    public Consumer(BlockingQueue blockingQueue) {
        super(blockingQueue, "consumer");
    }

    @Override
    protected boolean evaluateCondition(final BlockingQueue blockingQueue) {
        return blockingQueue.size() > 5;
    }

    @Override
    protected void executeOperation(final BlockingQueue blockingQueue) throws InterruptedException {
        blockingQueue.take();
    }

}
