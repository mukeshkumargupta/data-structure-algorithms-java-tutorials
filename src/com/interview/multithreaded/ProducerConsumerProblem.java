package com.interview.multithreaded;

import java.util.*;
/*
 * Reference: https://www.youtube.com/watch?v=u3MUdI1B-X8&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=6
 *Try to implement if multiple thread producing and multiple thread consuming then notifyall requirwed
 */

public class ProducerConsumerProblem {
    
    class ProducerThread extends Thread {
        private Queue<Integer> queue;
        private int size;
        ProducerThread(Queue<Integer> q, int size) {
            this.queue = q;
            this.size = size;
        }
        void produce(int i) {
            while (queue.size() == size) {//Queue is full
                synchronized(queue) {
                    System.out.println("Since queue is full so waiting for queue to empty");
                    try {
                        queue.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } 
            
            //Then modifying queue so lock it
            synchronized(queue) {
                System.out.println("Producing element " + i);
                queue.offer(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //queue.notifyAll();
                queue.notify();
            }
                

            
        }
        public void run() {
            for (int i = 0 ; i < 15; i++) {
                produce(i); 
            }
            
        }
    }
    
    class ConsumerThread extends Thread {
        private Queue<Integer> queue;
        ConsumerThread(Queue<Integer> q) {
            this.queue = q;
        }
        void consume() {
            while (queue.isEmpty()) {
                synchronized(queue) {
                    System.out.println("Since queue is empty so waiting for emement to be added in queue");
                    try {
                        queue.wait();//Note wait and notify should be on same object otherwise IllegalMonitorStateException
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } 
            //Since modifying queue so get lock
            synchronized(queue) {
                int c = queue.poll();
                System.out.println("Consuming element " + c);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                queue.notify();//Note wait and notify should be on same object otherwise IllegalMonitorStateException
                //queue.notifyAll(); //Here notifyAll is not required because only two thread
            }
        }

        
        @Override
        public void run() {
            for (int i = 0 ; i < 15; i++) {
                consume();
            }
            
        }
    }
    
    class RunnableProducerThread implements Runnable {
        private Queue<Integer> queue;
        private int size;
        RunnableProducerThread(Queue<Integer> q, int size) {
            this.queue = q;
            this.size = size;
        }
        void produce(int i) throws InterruptedException {
            while (queue.size() == size) {//Queue is full
                synchronized(queue) {
                    System.out.println("Since queue is full so waiting for queue to empty");
                    try {
                        queue.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } 
            
            //Then modifying queue so lock it
            synchronized(queue) {
                System.out.println("Producing element " + i);
                queue.offer(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                queue.notifyAll();
            }
                

            
        }
        public void run() {
            for (int i = 0 ; i < 15; i++) {
                try {
                    produce(i); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            
        }
    }
    
    class RunnableConsumerThread implements Runnable {
        private Queue<Integer> queue;
        RunnableConsumerThread(Queue<Integer> q) {
            this.queue = q;
        }
        void consume() throws InterruptedException {
            while (queue.isEmpty()) {
                synchronized(queue) {
                    System.out.println("Since queue is empty so waiting for emement to be added in queue");
                    try {
                        queue.wait(); //Imp Point Note wait and notify should be on same object otherwise IllegalMonitorStateException
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } 
            //Since modifying queue so get lock
            synchronized(queue) {
                int c = queue.poll();
                System.out.println("Consuming element " + c);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                queue.notifyAll();
            }
        }

        
        @Override
        public void run() {
            for (int i = 0 ; i < 15; i++) {
                try {
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
            
        }
    }
    
    void run1() {
        Queue<Integer> q = new LinkedList<>();
        Thread p = new ProducerThread(q, 5);
        Thread c = new ConsumerThread(q);
        p.start();
        c.start();
    }
    
    void run2() {
        Queue<Integer> q = new LinkedList<>();
        Thread p = new Thread(new RunnableProducerThread(q, 5));
        Thread c = new Thread(new RunnableConsumerThread(q));
        p.start();
        c.start();
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ProducerConsumerProblem pcp = new ProducerConsumerProblem();
        pcp.run1();  //Both method working thread and runnable
        //pcp.run2();
        
    }
    
}
