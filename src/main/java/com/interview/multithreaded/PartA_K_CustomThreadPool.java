package com.interview.multithreaded;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.*;

/*
 * https://www.youtube.com/watch?v=ZDf4EnTR9RE&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=12
 *
 * 📌 Explanation of the Code
 *
 * 🔹 1. Task Class (Runnable)
 *    - Represents a unit of work that needs to be executed by the thread pool.
 *    - It prints messages when a task starts and completes.
 *    - Simulates a delay (Thread.sleep(1000)) to mimic task execution.
 *
 * 🔹 2. ThreadPool Class
 *    - Maintains a fixed number of worker threads (WorkerThread) that continuously take tasks from the queue.
 *    - Uses a blocking queue (ArrayBlockingQueue) to hold tasks.
 *    - Implements execute(Runnable task) to submit new tasks.
 *    - Implements shutdown() to stop the thread pool gracefully.
 *
 * 🔹 3. WorkerThread Class
 *    - Worker threads continuously fetch and execute tasks from the queue.
 *    - Implements a graceful shutdown mechanism instead of Thread.stop().
 *    - Uses taskQueue.poll(500, TimeUnit.MILLISECONDS) to periodically check for tasks, ensuring a proper exit when stopping.
 *
 * 🔹 4. Main Method (Testing the ThreadPool)
 *    - Creates a thread pool with 3 worker threads and a queue size of 10.
 *    - Submits 20 tasks (Task-1 to Task-20).
 *    - Waits for 5 seconds before shutting down the pool, allowing tasks to complete.
 */



public class PartA_K_CustomThreadPool {

    // Task to be executed by the thread pool
    static class Task implements Runnable {
        private final String name;

        Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " started task: " + name);
                Thread.sleep(1000);  // Simulate task execution time
                System.out.println(Thread.currentThread().getName() + " completed task: " + name);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
                System.err.println(Thread.currentThread().getName() + " was interrupted.");
            }
        }
    }

    // Custom Thread Pool
    static class ThreadPool {
        private final BlockingQueue<Runnable> taskQueue;
        private final List<WorkerThread> workers;
        private volatile boolean isStopped = false;

        public ThreadPool(int threadCount, int queueSize) {
            taskQueue = new ArrayBlockingQueue<>(queueSize);
            workers = new ArrayList<>();

            for (int i = 0; i < threadCount; i++) {
                WorkerThread worker = new WorkerThread(taskQueue);
                workers.add(worker);
                worker.start();
            }
        }

        public void execute(Runnable task) {
            if (isStopped) {
                throw new IllegalStateException("ThreadPool is stopped. Cannot accept new tasks.");
            }
            try {
                taskQueue.put(task); // Blocks if the queue is full
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Task submission interrupted.");
            }
        }

        public void shutdown() {
            isStopped = true;
            for (WorkerThread worker : workers) {
                worker.stopWorker();
            }
        }
    }

    // Worker Thread - Continuously takes tasks from the queue
    static class WorkerThread extends Thread {
        private final BlockingQueue<Runnable> taskQueue;
        private volatile boolean isStopped = false;

        WorkerThread(BlockingQueue<Runnable> queue) {
            this.taskQueue = queue;
        }

        @Override
        public void run() {
            while (!isStopped || !taskQueue.isEmpty()) {
                try {
                    Runnable task = taskQueue.poll(500, TimeUnit.MILLISECONDS);
                    if (task != null) {
                        task.run();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void stopWorker() {
            isStopped = true;
            this.interrupt(); // Interrupt if waiting on task
        }
    }

    // Running the ThreadPool
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(3, 10);
        int totalTasks = 20;

        for (int i = 1; i <= totalTasks; i++) {
            String taskName = "Task-" + i;
            threadPool.execute(new Task(taskName));
        }

        // Shutdown the thread pool after tasks are added
        try {
            Thread.sleep(5000); // Allow tasks to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Shutting down thread pool.");
        threadPool.shutdown();
    }
}

