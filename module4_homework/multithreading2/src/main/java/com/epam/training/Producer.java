package com.epam.training;

import java.util.concurrent.BlockingQueue;

public class Producer extends Customer {

    public Producer(BlockingQueue<Object> blockingQueue) {
        super(blockingQueue, "producer");
    }

    @Override
    protected boolean evaluateCondition(final BlockingQueue<Object> blockingQueue) {
        return blockingQueue.size() < 10;
    }

    @Override
    protected void executeOperation(final BlockingQueue<Object> blockingQueue) throws InterruptedException {
        blockingQueue.put(1);
    }

}
