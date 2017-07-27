package com.epam.mentoring.lessfour;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OppositionTest {

    private Counter counter;
    private Thread thread1;
    private Thread thread2;

    @BeforeMethod
    public void before(Object[] incrementThreads) {
        counter = new Counter();
    }

    @Test
    public void bothWrestlersDecrement() {
        // GIVEN
        thread1 = new Thread(new Wrestler(counter, false));
        thread2 = new Thread(new Wrestler(counter, false));

        // WHEN
        interruptThreads(2000);

        Opposition opposition = new Opposition(thread1, thread2);
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

            thread1.interrupt();
            thread2.interrupt();
        };

        Thread waitingThread = new Thread(runnable);
        waitingThread.start();
    }

}
