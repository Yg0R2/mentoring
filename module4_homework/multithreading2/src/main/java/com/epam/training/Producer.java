package com.epam.training;

import java.util.concurrent.BlockingQueue;

public class Producer extends Customer {

    public Producer(BlockingQueue blockingQueue) {
        super(blockingQueue, "producer");
    }

    @Override
    protected boolean evaluateCondition(final BlockingQueue blockingQueue) {
        return blockingQueue.size() < 10;
    }

    @Override
    protected void executeOperation(final BlockingQueue blockingQueue) throws InterruptedException {
        blockingQueue.put(1);
    }

}
