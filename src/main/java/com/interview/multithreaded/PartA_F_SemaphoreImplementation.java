package com.interview.multithreaded;

/*
 * Reference: https://www.youtube.com/watch?v=cIkzOOcdh2Y&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=4
 *
 * Explanation of the Semaphore Implementation in Java:
 * This Java program implements a simple semaphore mechanism using the wait() and notifyAll() methods
 * to control thread access to a shared resource.
 *
 * 🔹 What is a Semaphore?
 * A semaphore is a synchronization mechanism that controls how many threads can access a resource at the same time.
 * It maintains a permit count, and:
 * - A thread acquires a permit before accessing the resource.
 * - A thread releases a permit after finishing its work.
 * - If no permits are available, the thread waits until a permit is released.
 */


public class PartA_F_SemaphoreImplementation {
    private static class Semaphore {
        private int permits;

        public Semaphore(int permits) {
            this.permits = Math.max(permits, 0); // Ensuring non-negative permits
        }

        public synchronized void acquire() {
            while (permits == 0) {
                try {
                    wait(); // Wait until a permit is available
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            permits--;
            System.out.println(Thread.currentThread().getName() + " acquired semaphore. Remaining: " + permits);
            notifyAll(); // Notify waiting threads
        }

        public synchronized void release() {
            permits++;
            System.out.println(Thread.currentThread().getName() + " released semaphore. Available: " + permits);
            notifyAll(); // Notify waiting threads
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(4);

        for (int i = 0; i < 10; i++) { // Reduced to 10 threads for readability
            new Thread(() -> {
                semaphore.acquire();
                try {
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                semaphore.release();
            }).start();
        }
    }
}
