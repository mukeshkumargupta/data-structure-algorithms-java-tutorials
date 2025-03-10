package com.interview.multithreaded;


/*
 * https://www.youtube.com/watch?v=3y4zGH2eokA&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=11
 */
class ReentrantLockManager {
    private Thread currentThread = null;
    private int lockCount = 0;

    public synchronized void lock() {
        while (currentThread != Thread.currentThread() && lockCount > 0) {
            try {
                wait();  // Wait until the lock is available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restore interrupt status
                System.out.println("Thread interrupted while waiting for lock.");
            }
        }
        currentThread = Thread.currentThread();
        lockCount++;
        System.out.println(Thread.currentThread().getName() + " acquired lock. Count: " + lockCount);
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            lockCount--;
            System.out.println(Thread.currentThread().getName() + " released lock. Count: " + lockCount);

            if (lockCount == 0) {
                currentThread = null;
                notifyAll();  // Notify other waiting threads
            }
        }
    }
}

class ReentrantExample extends Thread {
    private final ReentrantLockManager lockManager;

    public ReentrantExample(ReentrantLockManager lockManager) {
        this.lockManager = lockManager;
    }

    private void enter() {
        lockManager.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " entered critical section.");
            Thread.sleep(1000);
            reenter();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        } finally {
            System.out.println(Thread.currentThread().getName() + " exiting critical section.");
            lockManager.unlock();
        }
    }

    private void reenter() {
        lockManager.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " re-entered lock.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        } finally {
            System.out.println(Thread.currentThread().getName() + " exiting re-entered lock.");
            lockManager.unlock();
        }
    }

    @Override
    public void run() {
        enter();
    }

    public static void main(String[] args) {
        ReentrantLockManager lockManager = new ReentrantLockManager();

        Thread t1 = new ReentrantExample(lockManager);
        Thread t2 = new ReentrantExample(lockManager);
        Thread t3 = new ReentrantExample(lockManager);

        t1.start();
        t2.start();
        t3.start();
    }
}
    

