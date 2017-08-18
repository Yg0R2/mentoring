package com.epam.training;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Customer {

    public Consumer(BlockingQueue<Object> blockingQueue) {
        super(blockingQueue, "consumer");
    }

    @Override
    protected boolean evaluateCondition(final BlockingQueue<Object> blockingQueue) {
        return blockingQueue.size() > 5;
    }

    @Override
    protected void executeOperation(final BlockingQueue<Object> blockingQueue) throws InterruptedException {
        blockingQueue.take();
    }

}
