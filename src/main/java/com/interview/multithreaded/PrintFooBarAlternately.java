package com.interview.multithreaded;

import java.util.concurrent.CountDownLatch;

/*
 * Reference: https://www.youtube.com/watch?v=qQjSPkC6_gU
 */
public class PrintFooBarAlternately {
    private int n;
    private CountDownLatch[] countdownLatchFooAfter;
    private CountDownLatch[] countdownLatchBarAfter;

    public PrintFooBarAlternately(int n) {
        this.n = n;
        countdownLatchFooAfter = new CountDownLatch[n];
        countdownLatchBarAfter = new CountDownLatch[n];
        for (int i = 0; i < n; i++) {
            countdownLatchFooAfter[i] = new CountDownLatch(1);
            countdownLatchBarAfter[i] = new CountDownLatch(1);
        }
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            if (i > 0) {
               countdownLatchBarAfter[i-1].await(); 
            }

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            countdownLatchFooAfter[i].countDown();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        synchronized(this) {
            for (int i = 0; i < n; i++) {
                countdownLatchFooAfter[i].await();

                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                countdownLatchBarAfter[i].countDown();
            }
        }
    }
    
}
