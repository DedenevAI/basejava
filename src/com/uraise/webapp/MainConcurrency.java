package com.uraise.webapp;

import java.util.concurrent.*;

public class MainConcurrency {
    private static int counter;
    private static final int THREADS_NUMBER = 10000;
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        CompletionService completionService = new ExecutorCompletionService(executorService);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    inc();
                }
                latch.countDown();
                return counter;
            });
            completionService.poll();
            System.out.println(future.isDone());
            System.out.println(future.get());
        }


//        {
//            new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    inc();
//                }
//            }).start();
//        }
        latch.await();
        executorService.shutdown();
        System.out.println(counter);
    }

    private static void inc() {
        synchronized (LOCK) {
            counter++;
        }
    }
}
