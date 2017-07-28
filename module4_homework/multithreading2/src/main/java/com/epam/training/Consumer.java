package com.epam.training;

public class Consumer extends Customer {

    public Consumer(SharedResource sharedResource) {
        super(sharedResource, "consumer");
    }

    @Override
    protected boolean evaluateCondition(final SharedResource sharedResource) {
        return sharedResource.get() <= 5;
    }

    @Override
    protected int executeOperation(final SharedResource sharedResource) {
        return sharedResource.decrement();
    }

}
