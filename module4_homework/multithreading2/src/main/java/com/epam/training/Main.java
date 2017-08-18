package com.epam.training;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        BlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>();

        Thread consumer = new Thread(new Consumer(blockingQueue));
        Thread producer = new Thread(new Producer(blockingQueue));

        Main instance = new Main();

        instance.execute(Arrays.asList(producer, consumer));
    }

    private void execute(List<Thread> threads) {
        threads.stream().forEach(Thread::start);

        while (true) {
            if (threads.stream().anyMatch(thread -> !thread.isAlive())) {
                break;
            }
        }

        System.out.println("Finished.");

        threads.stream().forEach(Thread::interrupt);
    }

}
