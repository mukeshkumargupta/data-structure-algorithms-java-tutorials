package com.interview.multithreaded;

import java.util.function.IntConsumer;

/*
 * Reference: https://www.youtube.com/watch?v=XP75KkwHiNw
 *            https://leetcode.com/problems/print-zero-even-odd/
 * Category: Medium, Tricky
 *
 * Explanation:
 * Three synchronized methods:
 *  - zero(): Prints 0 before each number.
 *  - even(): Prints even numbers.
 *  - odd(): Prints odd numbers.
 *
 * Synchronization using wait() and notifyAll():
 *  - zeroTurn ensures 0 prints first.
 *  - counter tracks the current number.
 *  - Threads wait until their turn and then notifyAll().
 *
 * Example Output:
 * For n = 5, the output will be:
 *
 *  0102030405
 *
 * Where:
 *  - 0 is always printed before each number.
 *  - Odd and even numbers alternate.
 *
 * Why is this Clean?
 * ✅ Simple and readable
 * ✅ Uses wait() and notifyAll() for synchronization
 * ✅ Avoids busy-waiting or unnecessary complexity
 * ✅ Easily scalable for any n
 */
public class PartA_C_PrintZeroEvenOdd {
    private static class ZeroEvenOdd {
        private int n;
        private int counter = 1;
        private boolean zeroTurn = true;

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                while (!zeroTurn) {
                    wait();
                }
                printNumber.accept(0);
                zeroTurn = false;
                notifyAll();
            }
        }

        public synchronized void even(IntConsumer printNumber) throws InterruptedException {
            for (int i = 2; i <= n; i += 2) {
                while (zeroTurn || counter % 2 != 0) {
                    wait();
                }
                printNumber.accept(counter);
                counter++;
                zeroTurn = true;
                notifyAll();
            }
        }

        public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i += 2) {
                while (zeroTurn || counter % 2 == 0) {
                    wait();
                }
                printNumber.accept(counter);
                counter++;
                zeroTurn = true;
                notifyAll();
            }
        }


        public static void main(String[] args) {
            ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);

            IntConsumer printNumber = System.out::print;

            Thread t1 = new Thread(() -> {
                try {
                    zeroEvenOdd.zero(printNumber);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            Thread t2 = new Thread(() -> {
                try {
                    zeroEvenOdd.even(printNumber);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            Thread t3 = new Thread(() -> {
                try {
                    zeroEvenOdd.odd(printNumber);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            t1.start();
            t2.start();
            t3.start();
        }
    }
}
