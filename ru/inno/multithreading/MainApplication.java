package ru.inno.multithreading;

import java.util.UUID;

public class MainApplication {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPoolExecutor threadPoolExecutor = new CustomThreadPoolExecutor(3);

            threadPoolExecutor.execute(() -> System.out.println(UUID.randomUUID() + "print task " + Thread.currentThread().getName()));
            threadPoolExecutor.execute(() -> System.out.println(UUID.randomUUID() + "print task " + Thread.currentThread().getName()));
            threadPoolExecutor.execute(() -> System.out.println(UUID.randomUUID() + "print task " + Thread.currentThread().getName()));
            threadPoolExecutor.execute(() -> System.out.println(UUID.randomUUID() + "print task " + Thread.currentThread().getName()));
            threadPoolExecutor.execute(() -> System.out.println(UUID.randomUUID() + "print task " + Thread.currentThread().getName()));
            threadPoolExecutor.execute(() -> System.out.println(UUID.randomUUID() + "print task " + Thread.currentThread().getName()));

        threadPoolExecutor.shutdown();
    }

    }

