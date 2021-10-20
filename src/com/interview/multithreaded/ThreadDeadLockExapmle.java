package com.interview.multithreaded;

/*
 * Reference: https://www.youtube.com/watch?v=cNCFltoh1fE&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=8
 */
public class ThreadDeadLockExapmle {
    class Thread1 extends Thread {
        Object r1;
        Object r2;
        Thread1(Object r1, Object r2) {
            this.r1= r1;
            this.r2 = r2;
        }
        
        
        @Override
        public void run() {
            synchronized(r1) {
                try {
                    System.out.println("Thread 1: Accuired and lock resource 1");
                    Thread.sleep(100);
                    System.out.println("Thread 1: Trying to accuire lock on R2");
                    synchronized(r2) {
                        try {
                            System.out.println("Thread 1: Accuired and lock resource 2");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
        }
    }
    
    class Thread2 extends Thread {
        Object r1;
        Object r2;
        Thread2(Object r1, Object r2) {
            this.r1= r1;
            this.r2 = r2;
        }
        
        
        @Override
        public void run() {
            synchronized(r2) {
                try {
                    System.out.println("Thread 2: Accuired and lock resource 2");
                    Thread.sleep(2000);
                    
                    System.out.println("Thread 2: Trying to accuire lock on R1");
                    synchronized(r1) {
                        try {
                            System.out.println("Thread 2: Accuired and lock resource 1");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    void runExample1() {
        Object r1 = "Resouce 1";
        Object r2 = "Resouce 2";
        Thread t1 = new Thread(new Runnable () {

            @Override
            public void run() {
                synchronized(r1) {
                    try {
                        System.out.println("Thread 1: Accuired and lock resource 1");
                        Thread.sleep(100);
                        System.out.println("Thread 1: Trying to accuire lock on R2");
                        synchronized(r2) {
                            try {
                                System.out.println("Thread 1: Accuired and lock resource 2");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(r2) {
                    try {
                        System.out.println("Thread 2: Accuired and lock resource 2");
                        Thread.sleep(100);
                        
                        System.out.println("Thread 2: Trying to accuire lock on R1");
                        synchronized(r1) {
                            try {
                                System.out.println("Thread 2: Accuired and lock resource 1");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
    
    void runExample2() {
        Object r1 = "Resouce 1";
        Object r2 = "Resouce 2";
        Thread t1 = new Thread(new Thread1(r1, r2));
        Thread t2 = new Thread(new Thread2(r1, r2));
        t1.start();
        t2.start();
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ThreadDeadLockExapmle tde = new ThreadDeadLockExapmle();
        //tde.runExample1();
        tde.runExample2(); //Note both way it is running
    }
    
}
