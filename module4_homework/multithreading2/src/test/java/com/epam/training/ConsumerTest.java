package com.epam.training;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ConsumerTest {

    private Consumer consumer;
    private SharedResource sharedResource;

    @BeforeMethod
    public void beforeMethod() {
        sharedResource = new SharedResource(10);
    }

    @Test
    public void testOnlyOneConsumer() {
        // GIVEN
        consumer = new Consumer(sharedResource);

        // WHEN
        consumer.start();

        TestUtils.waitAndInterruptThreads(2000, consumer);

        // THEN
        assertEquals(sharedResource.get(), 5);
    }

}
