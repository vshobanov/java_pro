package ru.inno.multithreading;


public class MainApplication {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPoolExecutor threadPoolExecutor = new CustomThreadPoolExecutor(3);
        Runnable task1 = new Task();
        threadPoolExecutor.execute(task1);
        threadPoolExecutor.execute(task1);
        threadPoolExecutor.execute(task1);
        threadPoolExecutor.execute(task1);
        threadPoolExecutor.execute(task1);
        threadPoolExecutor.execute(task1);
        threadPoolExecutor.execute(task1);
        threadPoolExecutor.execute(task1);

        threadPoolExecutor.shutdown();
        threadPoolExecutor.execute(task1);
        threadPoolExecutor.execute(task1);

    }

}

