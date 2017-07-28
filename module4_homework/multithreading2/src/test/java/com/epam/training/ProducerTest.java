package com.epam.training;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProducerTest {

    Producer producer;
    private SharedResource sharedResource;

    @BeforeMethod
    public void beforeMethod() {
        sharedResource = new SharedResource(5);
    }

    @Test
    public void testOnlyOneProducer() {
        // GIVEN
        producer = new Producer(sharedResource);

        // WHEN
        producer.start();

        TestUtils.waitAndInterruptThreads(2000, producer);

        // THEN
        assertEquals(sharedResource.get(), 10);
    }

}
