package ru.inno.multithreading;

import java.util.LinkedList;

public class CustomThreadPoolExecutor {
    private final LinkedList<Runnable> workerQueue;
    private final Thread[] workerThreads;
    private final Runnable POISON_PILL = () -> {
    };
    private boolean isShutdown;
    public CustomThreadPoolExecutor(int numThreads) {
        workerQueue = new LinkedList<>();
        workerThreads = new Thread[numThreads];
        int i = 0;
        for (Thread t : workerThreads) {
            t = new Worker("Thread " + i++);
            System.out.println("Thread-" + i + " created in ThreadPool.");
            t.start();
        }
    }
    public void execute(Runnable r) {
        if (isShutdown) {
            throw new IllegalStateException(String.format(
                    "Cannot accept %s tasks. The pool has been shutdown.", r.toString()));
        }
        synchronized (workerQueue) {
            System.out.println("Task added.");
            workerQueue.addFirst(r);
            workerQueue.notify();
        }
    }
    public void shutdown() {
        isShutdown = true;
        System.out.println("Shutdown Process initiated.");
        synchronized (workerQueue) {
            workerQueue.add(POISON_PILL);
            workerQueue.notify();
        }
    }
    class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName()
                        + " is READY to execute task.");
                Runnable task;
                synchronized (workerQueue) {
                    while (workerQueue.isEmpty() && !isShutdown) {
                        try {
                            workerQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                task = workerQueue.poll();
                System.out.println(Thread.currentThread().getName()
                        + " has taken task.");
                if (task == null) {
                    return;
                }
                if (task != null) {
                    try {
                        task.run();
                        System.out.println(Thread.currentThread().getName()
                                + " has EXECUTED task.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        }
    }
}
