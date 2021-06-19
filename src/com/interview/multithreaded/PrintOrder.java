package com.interview.multithreaded;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Reference: https://www.youtube.com/watch?v=ltbY2brj6UQ
 * https://leetcode.com/problems/print-in-order/
 * Category: Easy
 */

public class PrintOrder {
    private AtomicInteger first;
    private AtomicInteger second;
    public PrintOrder() {
        first = new AtomicInteger(0);
        second = new AtomicInteger(0);
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        first.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (first.get() != 1) {
            
        }
        
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        second.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (second.get() != 1) {
            
        }
        
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
    public static void main(String[] args) {
       
        
    }
    
}
