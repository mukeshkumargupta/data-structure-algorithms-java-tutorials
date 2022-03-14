package com.interview.multithreaded;

/*
 * Reference: https://www.geeksforgeeks.org/print-even-and-odd-numbers-in-increasing-order-using-two-threads-in-java/
 */

public class PrintEvenOdd {
    // Starting counter 
    int counter = 1; 
  
    static int N; 
  
    // Function to print odd numbers 
    public void printOneNumber() 
    { 
        synchronized (this) 
        { 
            // Print number till the N 
            while (counter < N) { 
  
                // If count is even then print 
                while (counter % 3 != 0 && counter % 2 != 0) { 
  
                    // Exception handle 
                    try { 
                        wait(); 
                    } 
                    catch ( 
                        InterruptedException e) { 
                        e.printStackTrace(); 
                    } 
                } 
  
                // Print the number 
                System.out.print(1 + " "); 
  
                // Increment counter 
                counter++; 
  
                // Notify to other thread 
                notifyAll(); 
            } 
        } 
    } 
  
    // Function to print even numbers 
    public void printTwoNumber() 
    { 
        synchronized (this) 
        { 
            // Print number till the N 
            while (counter < N) { 
  
                // If count is odd then print 
                while (counter % 2 != 0) { 
  
                    // Exception handle 
                    try { 
                        wait(); 
                    } 
                    catch ( 
                        InterruptedException e) { 
                        e.printStackTrace(); 
                    } 
                } 
  
                // Print the number 
                System.out.print( 
                    2 + " "); 
  
                // Increment counter 
                counter++; 
  
                // Notify to other thread 
                notifyAll(); 
            } 
        } 
    } 
    
    // Function to print odd numbers 
    public void printThreeNumber() 
    { 
        synchronized (this) 
        { 
            // Print number till the N 
            while (counter < N) { 
  
                // If count is even then print 
                while (counter % 3 != 0) { 
  
                    // Exception handle 
                    try { 
                        wait(); 
                    } 
                    catch ( 
                        InterruptedException e) { 
                        e.printStackTrace(); 
                    } 
                } 
  
                // Print the number 
                System.out.print(3 + " "); 
  
                // Increment counter 
                counter++; 
  
                // Notify to second thread 
                notifyAll(); 
            } 
        } 
    } 
    
    public static void main(String[] args) {
        // Given Number N 
        N = 10; 
  
        // Create an object of class 
        PrintEvenOdd EvenOdd = new PrintEvenOdd(); 
  
        
        // Create thread t1 
        Thread t1 = new Thread(EvenOdd::printOneNumber);
        Thread t2 = new Thread(EvenOdd::printTwoNumber);
        Thread t3 = new Thread(EvenOdd::printThreeNumber);
  

  
        // Start both threads 
        t1.start(); 
        t2.start(); 
        t3.start(); 
    } 
}
