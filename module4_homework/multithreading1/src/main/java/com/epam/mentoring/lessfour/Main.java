package com.epam.mentoring.lessfour;

public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread thread1 = new Thread(new Wrestler(counter, true));
        Thread thread2 = new Thread(new Wrestler(counter, false));

        Opposition opposition = new Opposition(thread1, thread2);
        opposition.start();
    }

}
