package com.interview.multithreaded;

/*
 * Reference: https://www.geeksforgeeks.org/print-even-and-odd-numbers-in-increasing-order-using-two-threads-in-java/
    Explanation:
    Shared Counter (counter): Tracks the current number to be printed.
    Synchronization (synchronized Methods): Ensures only one thread accesses the shared counter at a time.
    Wait-Notify Mechanism:
    The thread prints a number if it matches its responsibility (odd/even).
    It then notifies the other thread to proceed and calls wait() to pause execution.
    *  Example Output:
     * For N = 10, expected output:
     *
     * Odd: 1
     * Even: 2
     * Odd: 3
     * Even: 4
     * Odd: 5
     * Even: 6
     * Odd: 7
     * Even: 8
     * Odd: 9
     * Even: 10

 */
class PartA_A_PrintEvenOdd {
    private static class PrintEvenOddUsingIndividualMethod {
        private int counter = 1;
        private final int n;

        public PrintEvenOddUsingIndividualMethod(int n) {
            this.n = n;
        }

        public synchronized void printOdd() {
            try {
                while (counter <= n) {
                    while (counter % 2 == 0) { // Wait if it's even
                        wait();
                    }
                    System.out.println("Odd: " + counter);
                    counter++;
                    notifyAll(); // Notify the even thread
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public synchronized void printEven() {
            try {
                while (counter <= n) {
                    while (counter % 2 != 0) { // Wait if it's odd
                        wait();
                    }
                    System.out.println("Even: " + counter);
                    counter++;
                    notifyAll(); // Notify the odd thread
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public static void main(String[] args) {
            int n = 10;
            PrintEvenOddUsingIndividualMethod peo = new PrintEvenOddUsingIndividualMethod(n);

            Thread t1 = new Thread(peo::printOdd);  // Thread 1 prints odd numbers
            Thread t2 = new Thread(peo::printEven); // Thread 2 prints even numbers

            t1.start();
            t2.start();
        }
    }

    private static class PrintEvenOddUsingSingleMethod {
        private final int n;
        private int counter = 1;

        public PrintEvenOddUsingSingleMethod(int n) {
            this.n = n;
        }

        public synchronized void print(int threadId) {
            while (counter <= n) {
                try {
                    while ((counter % 2 == 0 && threadId == 1) || (counter % 2 != 0 && threadId == 2)) {
                        wait(); // Wait if it's not this thread's turn
                    }

                    if (counter <= n) {
                        System.out.println((counter % 2 == 0 ? "Even: " : "Odd: ") + counter);
                        counter++;
                        notifyAll(); // Notify the other thread
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public static void main(String[] args) {
            int n = 10;
            PrintEvenOddUsingSingleMethod peo = new PrintEvenOddUsingSingleMethod(n);

            Thread t1 = new Thread(() -> peo.print(1)); // Thread 1 prints odd numbers
            Thread t2 = new Thread(() -> peo.print(2)); // Thread 2 prints even numbers

            t1.start();
            t2.start();
        }
    }

}

