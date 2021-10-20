package com.interview.multithreaded;

/*
 * Reference: https://www.youtube.com/watch?v=cIkzOOcdh2Y&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=4
 */
public class SemaphoreImplementation {
    class Semaphore  {
        int semaphore;
        Semaphore(int semaphore) {
            if (semaphore < 0) {
                semaphore = 0;
            }
            this.semaphore = semaphore;
        }
        
        synchronized void accuire() {
            while (semaphore == 0) {
                try {
                    wait();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            System.out.println("Got semaphore: " + semaphore);
            semaphore--;
            notifyAll();
        }
        
        synchronized void release() {
            semaphore++;
            System.out.println("Released semaphore: " + semaphore);
            notifyAll();
        }
        
    }
    
    void runSemaphoreImplementation() {
        Semaphore sm = new Semaphore(4);
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    sm.accuire();
                    System.out.println("Current Thread name: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    sm.release();
                }
            });
            t.start();
        }

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SemaphoreImplementation is = new SemaphoreImplementation();
        is.runSemaphoreImplementation();
        
        
    }
    
}
