package com.interview.multithreaded;


/*
 * https://www.youtube.com/watch?v=3y4zGH2eokA&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=11
 */
public class ReentrantExample extends Thread {
    LockManager lm;
    ReentrantExample() {
        this.lm = new LockManager();
    }
    void enter() {
        lm.lock();
        try {
            System.out.println("Enter: Got lock");
            Thread.sleep(1000);
            reenter();
        } catch(InterruptedException e ) {
            e.printStackTrace();
        }
        System.out.println("Enter: Released lock");
        lm.unlock();
        
    }
    
    void reenter() {
        lm.lock();
        try {
            System.out.println("Reeneter: Got lock");
            Thread.sleep(1000);;
        } catch(InterruptedException e ) {
            e.printStackTrace();
        }
        System.out.println("Reeneter: Released lock");
        lm.unlock();
    }
    @Override
    public void run() {
        try {
            enter();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
class LockManager {
    Thread currentThread = null;
    int count = 0;
    synchronized void  lock() {
        while (currentThread != Thread.currentThread() && count > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        currentThread = Thread.currentThread();
        count++;
        System.out.println("Increased reference count by thread: " + Thread.currentThread().getName() + " Reference count: " + count);

    }
    
    synchronized void unlock() {
        if (currentThread == Thread.currentThread() && count > 0) {
            count--;
            System.out.println("Decreased reference count by thread: " + Thread.currentThread().getName() + " Reference count: " + count);
        }
        
        if (count == 0) {
            System.out.println("Released lock by thead name: " + Thread.currentThread().getName());
            notifyAll();
        }
    }
}

    
    public static void main(String[] args) {
        ReentrantExample Re = new ReentrantExample();
        Thread t1 = new Thread(Re);
        Thread t2 = new Thread(Re);
        Thread t3 = new Thread(Re);
        t2.start();
        t1.start();
        t3.start();
    }
}
    

