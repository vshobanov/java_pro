package ru.inno.multithreading;

import java.util.LinkedList;

public class CustomThreadPoolExecutor {
    private final LinkedList<Runnable> workerQueue;
    private final Thread[] workerThreads;
    private final Runnable POISON_PILL = () -> {};
    private boolean isShutdown;

    public CustomThreadPoolExecutor(int numThreads) {
        workerQueue = new LinkedList<>();
        workerThreads = new Thread[numThreads];
        int i = 0;
        for (Thread t : workerThreads) {
            t = new Worker("Custom Thread " + ++i);
            t.start();
            System.out.println(t.getName());
        }
    }
    public void execute(Runnable r) {
        synchronized (workerQueue) {
            workerQueue.addFirst(r);
            workerQueue.notify();
        }
    }
    public void shutdown() {
        isShutdown = true;
        synchronized (workerQueue) {
            for (Thread t : workerThreads) {
                workerQueue.add(POISON_PILL);
                workerQueue.notify();
            }
        }
    }

    class Worker extends Thread {
        public Worker(String name) {
            super(name);
        }

        public void run() {
            while (true) {
                Runnable task;
                synchronized (workerQueue) {
                    while (workerQueue.isEmpty() && !isShutdown) {
                        try {
                            workerQueue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = workerQueue.poll();
                    if (isShutdown && task != null) {
                        throw new IllegalStateException(String.format(
                                "Cannot accept %s tasks. The pool has been shutdown.", task.toString()));
                    }
                    if (workerQueue.contains(POISON_PILL)) {
                        workerQueue.add(POISON_PILL);
                        return;
                    }
                    if (!isShutdown && task != null) {
                        try {
                            Thread.sleep(500);
                            task.run();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            }
        }
    }
}
