package com.epam.training;

public class TestUtils {

    public static void waitAndInterruptThreads(final int waitTimeBeforeInterrupt, final Thread... threads) {
        try {
            Thread.sleep(waitTimeBeforeInterrupt);
        }
        catch (InterruptedException e) {
        }

        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

}
