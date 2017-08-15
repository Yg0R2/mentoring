package com.epam.mentoring.lessfour;

import java.util.List;

public class Opposition {

    private List<Thread> wrestlers;

    public Opposition(List<Thread> wrestlers) {
        this.wrestlers = wrestlers;
    }

    public void start() {
        startThreads();

        while (isAllThreadsAlive()) {
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                break;
            }
        }

        stopThreads();

        System.out.println("Finished");
    }

    private boolean isAllThreadsAlive() {
        return !(wrestlers.stream().anyMatch(thread -> !thread.isAlive()));
    }

    private void startThreads() {
        wrestlers.stream().forEach(Thread::start);
    }

    private void stopThreads() {
        wrestlers.stream().forEach(Thread::interrupt);
    }

}
