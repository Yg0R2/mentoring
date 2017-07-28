package com.epam.training;

public class Main {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(10);

        Thread consumer = new Thread(new Consumer(sharedResource));
        Thread producer = new Thread(new Producer(sharedResource));

        consumer.start();
        producer.start();

        while (consumer.isAlive() && producer.isAlive()) {
        }

        System.out.println("Finished.");

        consumer.interrupt();
        producer.interrupt();
    }

}
