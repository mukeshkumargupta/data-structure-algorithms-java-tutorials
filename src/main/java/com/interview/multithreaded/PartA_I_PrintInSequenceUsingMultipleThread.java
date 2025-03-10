package com.interview.multithreaded;

/*
 * A class that has 5 threads:
 * - Two threads increment the `myVar` variable.
 * - Two threads decrement the `myVar` variable.
 * - One thread prints the value of `myVar`.
 *
 * The sequence must be:
 * 0 1 2 3 4 5 4 3 2 1 0 1 2 3 4 5 4 3 2 1 ... (repeating).
 *
 * Derived Question:
 * - Print odd numbers first up to a five-number interval: (1 3 5 7 9)
 * - Then print even numbers in the same interval: (0 2 4 6 8)
 * - Continue alternating with the next odd and even sequences.
 *
 * ✅ Thread Safety:
 * - Uses `synchronized` for correct order execution.
 * - Uses `wait()` and `notifyAll()` for proper thread communication.
 *
 * 🔹 Explanation:
 *
 * 🔹 SharedVariable Class:
 * - `myVar`: Maintains the current value.
 * - `increasing`: A flag that determines whether to increment or decrement.
 *
 * 🔹 Synchronized Methods:
 * - `increment()`: Increments `myVar` up to 5, waits if max is reached.
 * - `decrement()`: Decrements `myVar` down to 0, waits if min is reached.
 * - `printVar()`: Prints the value, determines if we need to switch between incrementing and decrementing.
 *
 * 🔹 Thread Execution:
 * - Two incrementer threads increase the value.
 * - Two decrementer threads decrease the value.
 * - One printer thread prints the value and helps in switching direction.
 *
 * 🔹 Synchronization Strategy:
 * - Uses `wait()` to pause threads when necessary.
 * - Uses `notifyAll()` to wake up all waiting threads.
 *
 * ✅ Why is this Clean?
 * ✔ Minimal and clear synchronization.
 * ✔ Efficient wait-notify mechanism to avoid unnecessary CPU usage.
 * ✔ Scalable for additional variations like odd-even sequencing.
 */



public class PartA_I_PrintInSequenceUsingMultipleThread {

    private static class SharedVariable {
        private int myVar = 0;
        private boolean increasing = true; // Track the direction (increasing or decreasing)

        // Increments myVar up to 5
        public synchronized void increment() throws InterruptedException {
            while (!increasing || myVar >= 5) {
                wait(); // Wait if decrement is required or max value reached
            }
            myVar++;
            notifyAll(); // Notify waiting threads
        }

        // Decrements myVar down to 0
        public synchronized void decrement() throws InterruptedException {
            while (increasing || myVar <= 0) {
                wait(); // Wait if increment is required or min value reached
            }
            myVar--;
            notifyAll(); // Notify waiting threads
        }

        // Prints myVar value and manages increasing/decreasing direction
        public synchronized void printVar() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " -> " + myVar);

            // When max is reached, switch to decreasing; when min is reached, switch to increasing
            if (myVar == 5) increasing = false;
            if (myVar == 0) increasing = true;

            notifyAll(); // Notify other threads
        }
    }

    public static void main(String[] args) {
        SharedVariable sharedVar = new SharedVariable();

        Runnable incrementTask = () -> {
            while (true) {
                try {
                    sharedVar.increment();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable decrementTask = () -> {
            while (true) {
                try {
                    sharedVar.decrement();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable printTask = () -> {
            while (true) {
                try {
                    sharedVar.printVar();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Creating threads
        new Thread(incrementTask, "Incrementer-1").start();
        new Thread(incrementTask, "Incrementer-2").start();
        new Thread(decrementTask, "Decrementer-1").start();
        new Thread(decrementTask, "Decrementer-2").start();
        new Thread(printTask, "Printer").start();
    }
}
