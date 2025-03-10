package com.interview.multithreaded;

/*
 * Reference: https://www.youtube.com/watch?v=cNCFltoh1fE&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=8
 */
public class PartA_G_ThreadDeadLockExapmle {
    private static final Object resource1 = new Object(); // First shared resource
    private static final Object resource2 = new Object(); // Second shared resource

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " locked resource1");

                try {
                    Thread.sleep(100); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                synchronized (resource2) { // Waiting for resource2 (held by Thread 2)
                    System.out.println(Thread.currentThread().getName() + " locked resource2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + " locked resource2");

                try {
                    Thread.sleep(100); // Simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                synchronized (resource1) { // Waiting for resource1 (held by Thread 1)
                    System.out.println(Thread.currentThread().getName() + " locked resource1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
    
}
