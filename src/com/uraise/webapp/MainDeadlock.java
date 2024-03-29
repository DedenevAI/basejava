package com.uraise.webapp;

public class MainDeadlock implements Runnable {
    private static class Resource {
    }

    private final Resource resource1 = new Resource();
    private final Resource resource2 = new Resource();

    public void stepOne() {
        synRes(resource1, resource2);
    }

    public void stepTwo() {
        synRes(resource2, resource1);
    }

    public void synRes(Resource firstResource, Resource secondResource) {
        synchronized (firstResource) {
            System.out.println(Thread.currentThread().getName() + "resource1 is using now...");
            synchronized (secondResource) {
                System.out.println(Thread.currentThread().getName() + "resource2 is using now...");
            }
        }
    }

    @Override
    public void run() {
        stepOne();
        stepTwo();
    }

    public static void main(String[] args) {
        MainDeadlock something = new MainDeadlock();
        Thread thread1 = new Thread(something, "Th1 ");
        Thread thread2 = new Thread(something, "Th2 ");
        thread1.start();
        thread2.start();
    }
}
