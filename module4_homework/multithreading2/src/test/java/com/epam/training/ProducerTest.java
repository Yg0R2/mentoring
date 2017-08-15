package com.epam.training;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.testng.Assert.assertEquals;

public class ProducerTest {

    Producer producer;
    private BlockingQueue blockingQueue;

    @BeforeMethod
    public void beforeMethod() {
        blockingQueue = new LinkedBlockingQueue(Arrays.asList(1, 2, 3, 4, 5));
    }

    @Test
    public void testOnlyOneProducer() {
        // GIVEN
        producer = new Producer(blockingQueue);

        // WHEN
        producer.start();

        TestUtils.waitAndInterruptThreads(2000, producer);

        // THEN
        assertEquals(blockingQueue.size(), 10);
    }

}
