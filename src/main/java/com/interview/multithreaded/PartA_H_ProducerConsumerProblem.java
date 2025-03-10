package com.interview.multithreaded;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Reference: https://www.youtube.com/watch?v=u3MUdI1B-X8&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=6
 *
 * 🔹 Explanation
 *
 * SharedQueue (Buffer)
 * - Stores items in a queue with a fixed capacity.
 * - Uses synchronized for thread safety.
 * - Uses wait() when full (producer) or empty (consumer).
 * - Uses notifyAll() to wake up waiting threads.
 *
 * Producer Thread
 * - Adds items to the queue.
 * - Sleeps to simulate work.
 *
 * Consumer Thread
 * - Removes items from the queue.
 * - Sleeps to simulate work.
 *
 * ✅ Why is this Clean?
 * ✔ Uses wait() & notifyAll() for proper synchronization.
 * ✔ Avoids busy-waiting or unnecessary complexity.
 * ✔ Easily scalable for multiple producers & consumers.
 */

public class PartA_H_ProducerConsumerProblem {

    /*
     * Reference: Producer-Consumer Problem in Java
     * Explanation:
     * - Producer adds items to a shared queue.
     * - Consumer removes items from the queue.
     * - Uses synchronized methods with wait() & notifyAll() to handle concurrency.
     */

    private static class SharedQueue {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity;

        SharedQueue(int capacity) {
            this.capacity = capacity;
        }

        // Producer method to add an item
        public synchronized void produce(int value) throws InterruptedException {
            while (queue.size() == capacity) {
                wait(); // Wait if the queue is full
            }
            queue.add(value);
            System.out.println("Produced: " + value);
            notifyAll(); // Notify consumers that an item is available
        }

        // Consumer method to remove an item
        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait(); // Wait if the queue is empty
            }
            int value = queue.poll();
            System.out.println("Consumed: " + value);
            notifyAll(); // Notify producers that space is available
            return value;
        }
    }

    public class ProducerConsumer {
        public static void main(String[] args) {
            SharedQueue sharedQueue = new SharedQueue(5);

            // Producer thread
            Thread producer = new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    try {
                        sharedQueue.produce(i);
                        Thread.sleep(100); // Simulate work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            // Consumer thread
            Thread consumer = new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    try {
                        sharedQueue.consume();
                        Thread.sleep(200); // Simulate work
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            producer.start();
            consumer.start();
        }
    }
    
}
