package com.interview.multithreaded;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * https://www.youtube.com/watch?v=ZDf4EnTR9RE&list=PLTZbNwgO5ebr1O4i3CI9XHLJmiKBRRZsL&index=12
 */
public class ThreadPoolImplementationExample {
    class Task implements Runnable {
        String name;
        Task(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            try {
                System.out.println("Task name started running: " + name);
                Thread.sleep(1000);
                System.out.println("Task name finished: " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
    class ThreadPool {
        BlockingQueue<Runnable> taskQueue;
        List<PoolThread> threads;
        private int threadPoolSize;
        private int taskSize;
        boolean isStopped = false;
        
        ThreadPool(int thread, int taskSize) {
            this.threadPoolSize = thread;
            this.taskSize = taskSize;
            threads = new ArrayList<>();
            taskQueue = new ArrayBlockingQueue(taskSize);
            for (int i =0; i < thread; i++) {
                threads.add(new PoolThread(taskQueue));
            }
            
            for (int i =0; i < thread; i++) {
                threads.get(i).start();
            }
        }
        
        ThreadPool(int thread) {
            this.threadPoolSize = thread;
            this.taskSize = 25;//Default
            
            taskQueue = new ArrayBlockingQueue(taskSize);
            for (int i =0; i < thread; i++) {
                threads.add(new PoolThread(taskQueue));
            }
            
            for (int i =0; i < thread; i++) {
                threads.get(i).start();
            }
        }
        
        public void execute(Runnable task )  {
            if (isStopped) {
                throw new IllegalStateException();
            }
            try {
                taskQueue.put(task);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        public void stop() {
            isStopped = true;
            for (PoolThread poolThread : threads) {
                poolThread.stopThread();
            }
            
        }
        
        
    }
    
    class PoolThread extends Thread {
        boolean isStopped = false;
        BlockingQueue<Runnable> taskQueue;
        PoolThread(BlockingQueue<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
        }
        @Override
        public void run() {
            while(!isStopped) {
                Thread task;
                try {
                    task = (Thread)taskQueue.take();
                    task.start();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
        }
        

        @SuppressWarnings("deprecation")
        public void stopThread() {
            isStopped = true;
            this.interrupt();
        }
        
    }
    
    void run() {
        ThreadPool tp = new ThreadPool(2, 20);
        int totalTask = 100;
        for (int i = 0; i < totalTask; i++) {
            Random r = new Random();
            int randomNumber = r.nextInt(totalTask);
            String taskName = "" + randomNumber;
            System.out.println(taskName);
            if (randomNumber == 0) {
                System.out.println("Since task 0 is assumed to shutdown threadpool so shutting down therad pool");
                tp.stop();
            } else {
                tp.execute(new Thread(new Task(taskName)));
            }
            
        }
    }
    


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ThreadPoolImplementationExample tpie = new ThreadPoolImplementationExample();
        tpie.run();
        
    }
    
}
