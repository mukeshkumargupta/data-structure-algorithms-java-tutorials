package com.interview.multithreaded;

public class PartA_B_PrintSequenceOneTwoThree {

    /*
    How It Works?
    Shared Counter (step): Ensures execution order:
    step = 1 → "One"
    step = 2 → "Two"
    step = 3 → "Three"
    wait() & notifyAll():
    A thread waits if it's not its turn.
    After printing, it increments step and notifies other threads.
    Ensuring Proper Sequence:
    printOne() waits for step = 1, then prints and sets step = 2.
    printTwo() waits for step = 2, then prints and sets step = 3.
    printThree() waits for step = 3, then prints and resets step = 1 (for cyclic execution).
     */
    private static class PrintSequenceUsingIndividualMethod {
        private int step = 1;
        private final int MAX = 3;

        public synchronized void printOne() {
            try {
                while (step != 1) {
                    wait();
                }
                System.out.println("One");
                step = 2;
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public synchronized void printTwo() {
            try {
                while (step != 2) {
                    wait();
                }
                System.out.println("Two");
                step = 3;
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public synchronized void printThree() {
            try {
                while (step != 3) {
                    wait();
                }
                System.out.println("Three");
                step = 1; // Reset for next cycle (if needed)
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public static void main(String[] args) {
            PrintSequenceUsingIndividualMethod ps = new PrintSequenceUsingIndividualMethod();

            Thread t1 = new Thread(ps::printOne);
            Thread t2 = new Thread(ps::printTwo);
            Thread t3 = new Thread(ps::printThree);

            t1.start();
            t2.start();
            t3.start();
        }
    }

    /*
    How This Works
    Single Method print(int threadId):
    Each thread calls the same method with a different threadId (1, 2, or 3).
    Uses a shared step variable to ensure ordered execution.
    Synchronization:
    Threads wait if step doesn’t match their threadId.
    Once a thread prints, it updates step and notifies all threads.
    Looping Mechanism:
    Keeps running in a loop (modify as needed for finite execution).
    Output:
        One
        Two
        Three
        One
        Two
        Three
...
     */
    private static class PrintSequenceUsingSingleMethod {
        private int step = 1; // Shared counter for controlling order

        public synchronized void print(int threadId) {
            try {
                while (true) {
                    while (step != threadId) {
                        wait(); // Wait until it's this thread's turn
                    }

                    // Print based on thread ID
                    if (threadId == 1) {
                        System.out.println("One");
                        step = 2;
                    } else if (threadId == 2) {
                        System.out.println("Two");
                        step = 3;
                    } else if (threadId == 3) {
                        System.out.println("Three");
                        step = 1; // Reset for cyclic execution
                    }

                    notifyAll(); // Notify other waiting threads
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public static void main(String[] args) {
            PrintSequenceUsingSingleMethod ps = new PrintSequenceUsingSingleMethod();

            Thread t1 = new Thread(() -> ps.print(1));
            Thread t2 = new Thread(() -> ps.print(2));
            Thread t3 = new Thread(() -> ps.print(3));

            t1.start();
            t2.start();
            t3.start();
        }
    }


}
