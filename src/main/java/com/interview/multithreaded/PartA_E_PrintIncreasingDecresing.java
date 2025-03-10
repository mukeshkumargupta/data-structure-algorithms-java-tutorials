package com.interview.multithreaded;

/*
 * Status; Done
 *  Example Execution
For n = 5
Copy
Edit
1 2 3 4 5 4 3 2 1
🔹 How It Works?
Thread t1 calls printIncreasing() and prints numbers from 1 to n (5 in this case).
It sets increasingDone = true and notifies t2 (waiting thread).
Thread t2 then prints numbers from n-1 to 1 (4 to 1).
The program ensures correct sequence using wait() and notifyAll().
 */

public class PartA_E_PrintIncreasingDecresing {
    private final int n;
    private boolean increasingDone = false;

    public PartA_E_PrintIncreasingDecresing(int n) {
        this.n = n;
    }

    public synchronized void printIncreasing() {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }
        increasingDone = true;
        notifyAll(); // Notify decreasing thread
    }

    public synchronized void printDecreasing() {
        try {
            while (!increasingDone) {
                wait(); // Wait for increasing to complete
            }
            for (int i = n - 1; i >= 1; i--) {
                System.out.print(i + " ");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        int n = 5;
        PartA_E_PrintIncreasingDecresing pid = new PartA_E_PrintIncreasingDecresing(n);

        Thread t1 = new Thread(pid::printIncreasing);
        Thread t2 = new Thread(pid::printDecreasing);

        t1.start();
        t2.start();
    }
}
