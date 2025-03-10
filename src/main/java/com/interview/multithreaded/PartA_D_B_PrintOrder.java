package com.interview.multithreaded;

/*
 * Reference: https://www.youtube.com/watch?v=ltbY2brj6UQ
 * https://leetcode.com/problems/print-in-order/
 * Category: Easy
 *
 * 1114. Print in Order
 *
 * Suppose we have a class:
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 *
 * The same instance of Foo will be passed to three different threads.
 * Thread A will call first(), thread B will call second(), and thread C will call third().
 * Design a mechanism and modify the program to ensure that second() is executed after first(),
 * and third() is executed after second().
 *
 * Note:
 * We do not know how the threads will be scheduled in the operating system,
 * even though the numbers in the input seem to imply the ordering.
 * The input format you see is mainly to ensure our tests' comprehensiveness.
 *
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: "firstsecondthird"
 *
 * Example 2:
 * Input: nums = [1,3,2]
 * Output: "firstsecondthird"
 *
 * Approach:
 * - We need to ensure order between first(), second(), and third().
 * - We use two boolean flags (firstDone and secondDone) to track completion.
 * - Synchronization using wait() and notifyAll() ensures correct execution sequence.
 */

public class PartA_D_B_PrintOrder {
    private boolean firstDone = false;
    private boolean secondDone = false;

    public synchronized void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();  // Print "first"
        firstDone = true;
        notifyAll(); // Notify waiting threads
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
        while (!firstDone) {
            wait();  // Wait until first() is executed
        }
        printSecond.run();  // Print "second"
        secondDone = true;
        notifyAll(); // Notify third thread
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
        while (!secondDone) {
            wait();  // Wait until second() is executed
        }
        printThird.run();  // Print "third"
    }
    
}
