package com.interview.multithreaded;

/*
 * Reference: https://www.geeksforgeeks.org/print-even-and-odd-numbers-in-increasing-order-using-two-threads-in-java/
 * Status; Done
 */

public class PrintIncreasingDecresing {
    // Starting counter 
    int counter = 0; 
  
    static int N; 
    boolean isIncreasing = true;
    int startRange = 1;
    int endRange = 5;
    
  
    // Function to print odd numbers 
    public void printIncreasing() 
    { 
        synchronized (this) 
        { 
            // Print number till the N 
            while (counter < N) { 
                while (counter >= endRange) { 
                    // Exception handle 
                    try { 
                        wait(); 
                    } 
                    catch ( InterruptedException e) { 
                        e.printStackTrace(); 
                    } 
                } 
  
                // Increment counter 
                counter++;
                // Print the number 
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(counter + " "); 
  
                // Notify to second thread 
                notify(); 
            } 
        } 
    } 
  
    // Function to print even numbers 
    public void printDecresing() 
    { 
        synchronized (this) 
        { 
            // Print number till the N 
            while (counter < N) {  
                while (counter <= startRange) { 
  
                    // Exception handle 
                    try { 
                        wait(); 
                    } 
                    catch ( InterruptedException e) 
                    { 
                        e.printStackTrace(); 
                    } 
                } 
                // Increment counter 
                counter--; 
  
                // Print the number 
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print( 
                    counter + " ");
  
  
                // Notify to 2nd thread 
                notify(); 
            } 
        } 
    } 
    
    public static void main(String[] args) {
        // Given Number N 
        N = 10; 
  
        // Create an object of class 
        PrintIncreasingDecresing EvenOdd = new PrintIncreasingDecresing(); 
  
        // Create thread t1 
        Thread t1 = new Thread(new Runnable() { 
            public void run() 
            { 
                EvenOdd.printDecresing(); 
            } 
        }); 
  
        // Create thread t2 
        Thread t2 = new Thread(new Runnable() { 
            public void run() 
            { 
                EvenOdd.printIncreasing(); 
            } 
        }); 
  
        // Start both threads 
        t1.start(); 
        t2.start(); 
    } 
}
