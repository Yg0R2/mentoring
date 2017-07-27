package com.epam.mentoring.lessfour;

public class Opposition {

    private Thread thread1;
    private Thread thread2;

    public Opposition(Runnable runnable1, Runnable runnable2) {
        thread1 =  new Thread(runnable1);
        thread2 =  new Thread(runnable2);
    }

    public void start() {
        startThreads();

        while (true) {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
            }

            if (!thread1.isAlive() || !thread2.isAlive()) {
                stopThreads();

                break;
            }
        }

        System.out.println("Finished");
    }

    private void startThreads() {
        thread1.start();
        thread2.start();
    }

    private void stopThreads() {
        thread1.interrupt();
        thread2.interrupt();
    }

}
