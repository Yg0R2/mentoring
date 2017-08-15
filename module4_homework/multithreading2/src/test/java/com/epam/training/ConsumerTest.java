package com.epam.training;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.testng.Assert.assertEquals;

public class ConsumerTest {

    private Consumer consumer;
    private BlockingQueue blockingQueue;

    @BeforeMethod
    public void beforeMethod() {
        blockingQueue = new LinkedBlockingQueue(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

    @Test
    public void testOnlyOneConsumer() {
        // GIVEN
        consumer = new Consumer(blockingQueue);

        // WHEN
        consumer.start();

        TestUtils.waitAndInterruptThreads(2000, consumer);

        // THEN
        assertEquals(blockingQueue.size(), 5);
    }

}
