package com.epam.mentoring.lessfour;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class OppositionTest {

    private Counter counter;
    private Thread wrestlerThread1;
    private Thread wrestlerThread2;

    @BeforeMethod
    public void before(Object[] incrementThreads) {
        counter = new Counter();
    }

    @Test
    public void bothWrestlersDecrement() {
        // GIVEN
        wrestlerThread1 = new Thread(new Wrestler(counter, false));
        wrestlerThread2 = new Thread(new Wrestler(counter, false));

        // WHEN
        interruptThreads(2000);

        Opposition opposition = new Opposition(Arrays.asList(wrestlerThread1, wrestlerThread2));
        opposition.start();

        // THEN
        assertEquals(counter.get(), 0);
    }

    private void interruptThreads(final int waitTimeBeforeInterrupt) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(waitTimeBeforeInterrupt);
            }
            catch (InterruptedException e) {
            }

            wrestlerThread1.interrupt();
            wrestlerThread2.interrupt();
        };

        Thread waitingThread = new Thread(runnable);
        waitingThread.start();
    }

}
