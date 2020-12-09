package com.interview.multithreaded;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/*
 * Reference: https://www.baeldung.com/java-cyclicbarrier-countdownlatch
 */


public class CyclicBarrierVsCountDownLatchExample {
    public void runCountDownLatch()  {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t = new Thread(() -> {
            countDownLatch.countDown();
            countDownLatch.countDown();
        });
        t.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(0, countDownLatch.getCount());
        
    }

    public void runCyclicBarrier() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread t = new Thread(() -> {
            try {
                cyclicBarrier.await();
                cyclicBarrier.await();    
            } catch (InterruptedException | BrokenBarrierException e) {
                // error handling
            }
        });
        t.start();

        assertEquals(0, cyclicBarrier.getNumberWaiting());
        assertFalse(cyclicBarrier.isBroken());//Will not break
        Thread t2 = new Thread(() -> {
            try {
                cyclicBarrier.await();
                cyclicBarrier.await();    
            } catch (InterruptedException | BrokenBarrierException e) {
                // error handling
            }
        });
        t2.start();
        //assertEquals(0, cyclicBarrier.getNumberWaiting());
        assertTrue(cyclicBarrier.isBroken());//Will not break
        
    }
    
    public static void main(String[] args) {
        CyclicBarrierVsCountDownLatchExample example = new CyclicBarrierVsCountDownLatchExample();
        example.runCountDownLatch();
        example.runCyclicBarrier();
        
    }
    
}
