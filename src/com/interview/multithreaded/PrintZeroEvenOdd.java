package com.interview.multithreaded;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/*
 * Reference: https://www.youtube.com/watch?v=XP75KkwHiNw
 * https://leetcode.com/problems/print-zero-even-odd/
 * Category: Medium, Tricky
 */
public class PrintZeroEvenOdd {
    private int n;
    private Semaphore printZeroSemaphore;
    private Semaphore printOddSemaphore;
    private Semaphore printEvenSemaphore;
    private int lastPrinted;
    
    public void ZeroEvenOdd(int n) {
        this.n = n;
        printZeroSemaphore = new Semaphore(1);
        printOddSemaphore = new Semaphore(1);
        printEvenSemaphore = new Semaphore(1);
        lastPrinted= 0;
        try {
            printOddSemaphore.acquire();
            printEvenSemaphore.acquire();
            
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }  
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        
        int zeroNum = n;
        for (int i = 0 ; i < zeroNum ; i++) {
            printZeroSemaphore.acquire();
            printNumber.accept(0);
            if (lastPrinted % 2 == 0) {
               printOddSemaphore.release(); 
            } else {
                printEvenSemaphore.release(); 
            } 
        }

        
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        int evenNum = n/2;
        for (int i = 0 ; i < evenNum ; i++)  {
            printEvenSemaphore.acquire();
            printNumber.accept(++lastPrinted);
            printZeroSemaphore.release();
        }

        
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int oddNum = n- n/2;
        for (int i = 0 ; i < oddNum ; i++) {
            printOddSemaphore.acquire();
            printNumber.accept(++lastPrinted);
            printZeroSemaphore.release();
        }
        
    }
    // 0 1 0 2 0 3 0 4 0 5  odd be 3 count while even is 2 count and 0 be five count
    public static void main(String[] args) {
        PrintZeroEvenOdd instance = new PrintZeroEvenOdd();
        instance.ZeroEvenOdd(10);
        
    }
}
