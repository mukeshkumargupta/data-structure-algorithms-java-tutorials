package com.interview.multithreaded;

public class PrintSequenceOneTwoThree {
    static Object object = new Object();
    
    static boolean one = true;
    static boolean two = false;
    static boolean three = false;
    
    static class PrintSequenceTask implements Runnable {
        
        int threadName;
        
        PrintSequenceTask(int threadName) {
            this.threadName = threadName;
        }
        
        public void run() {
            try {
                while (true) {
                    Thread.sleep(500);
                    synchronized (object) {
                        if (1 == threadName) {
                            if (!one) {
                                object.wait();
                            } else {
                                System.out.print(threadName + " ");
                                one = false;
                                two = true;
                                three = false;
                                object.notifyAll();
                            }
                        }
                        if (2 == threadName) {
                            if (!two) {
                                object.wait();
                            } else {
                                System.out.print(threadName + " ");
                                one = false;
                                two = false;
                                three = true;
                                object.notifyAll();
                            }
                        }
                        if (3 == threadName) {
                            if (!three) {
                                object.wait();
                            } else {
                                System.out.print(threadName + " ");
                                one = true;
                                two = false;
                                three = false;
                                object.notifyAll();
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    public static void main(String[] args) {
        
        Thread t1 = new Thread(new PrintSequenceTask(1));
        Thread t2 = new Thread(new PrintSequenceTask(2));
        Thread t3 = new Thread(new PrintSequenceTask(3));
        t1.start();
        t2.start();
        t3.start();
        
    }
    
}
