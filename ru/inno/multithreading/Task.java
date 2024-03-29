package ru.inno.multithreading;


import java.util.UUID;

public class Task implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(UUID.randomUUID() + "print task. " + Thread.currentThread().getName()
                    + " is executing task.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
};

