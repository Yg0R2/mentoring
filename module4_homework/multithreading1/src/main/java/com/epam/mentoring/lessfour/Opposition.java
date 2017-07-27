package com.epam.mentoring.lessfour;

public class Opposition {

    private Thread wrestler1Thread;
    private Thread wrestler2Thread;

    public Opposition(Thread thread1, Thread thread2) {
        this.wrestler1Thread =  thread1;
        this.wrestler2Thread =  thread2;
    }

    public void start() {
        startThreads();

        while (true) {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                break;
            }

            if (!isThreadsAllive()) {
                stopThreads();

                break;
            }
        }

        System.out.println("Finished");
    }

    private boolean isThreadsAllive() {
        return !(!wrestler1Thread.isAlive() || !wrestler2Thread.isAlive());
    }

    private void startThreads() {
        wrestler1Thread.start();
        wrestler2Thread.start();
    }

    private void stopThreads() {
        wrestler1Thread.interrupt();
        wrestler2Thread.interrupt();
    }

}
