package com.interview.multithreaded;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A class that has 5 threads - two to increment the myVar variable, two to decrement the myVar variable and one to print the value of myVar.
 * Implement increment(), decrement() and printVar() methods such that the following series is printed:
 * 0 1 2 3 4 5 4 3 2 1 0 1 2 3 4 5 4 3 2 1 ... (repeating)
 *Derived question like odd first up to five interval 1 3 5 7 9 then 0 2 4 6 8 then 11 13 and so on
 * Status: Done
 */
public class PrintInSequence {

    private volatile int val = 0;
    private volatile boolean shouldPrint = true;
    private volatile boolean isIncreasing = true;
    private ReentrantReadWriteLock.WriteLock lock = new ReentrantReadWriteLock().writeLock();
    public void increment() {
        lock.lock();
        if (!shouldPrint && isIncreasing) {
            val = val + 1;
            if (val == 5) {
                isIncreasing = false;
            }
            shouldPrint = true;
        }
        lock.unlock();
    }

    public void decrement() {
        lock.lock();
        if (!shouldPrint && !isIncreasing) {
            val = val - 1;
            if (val == 0) {
                isIncreasing = true;
            }
            shouldPrint = true;
        }
        lock.unlock();
    }

    //only one thread is calling print. So no contention in updating shouldPrint flag.
    public void printVar() {
        if (shouldPrint) {
            System.out.println(val);
            shouldPrint = false;
        }
    }

    private void runIncrement() {
        while(true) {
            this.increment();
        }
    }

    private void runPrint() {
        while (true) {
            this.printVar();
        }
    }

    private void runDecrement() {
        while (true) {
            this.decrement();
        }
    }
    

    public static void main(String args[]) {
        PrintInSequence printInSequence = new PrintInSequence();
        Thread t1 = new Thread(printInSequence::runIncrement);
        t1.start();
        Thread t2 = new Thread(printInSequence::runIncrement);
        t2.start();
        Thread t3 = new Thread(printInSequence::runPrint);
        t3.start();
        Thread t4 = new Thread(printInSequence::runDecrement);
        t4.start();
        Thread t5 = new Thread(printInSequence::runDecrement);
        t5.start();
    }
}
