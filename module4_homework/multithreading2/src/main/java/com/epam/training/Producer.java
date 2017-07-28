package com.epam.training;

public class Producer extends Customer {

    public Producer(SharedResource sharedResource) {
        super(sharedResource, "producer");
    }

    @Override
    protected boolean evaluateCondition(final SharedResource sharedResource) {
        return sharedResource.get() >= 10;
    }

    @Override
    protected int executeOperation(final SharedResource sharedResource) {
        return sharedResource.increment();
    }

}
