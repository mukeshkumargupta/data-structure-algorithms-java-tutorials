package com.interview.multithreaded;

/*
 * Video: https://www.youtube.com/watch?v=7VqWkc9o7RM&list=PLhfHPmPYPPRk6yMrcbfafFGSbE2EPK_A6&index=14
 * Reference: https://www.codejava.net/java-core/concurrency/java-readwritelock-and-reentrantreadwritelock-example
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ReentrantReadWriteLockExample {
    /**
     * ReadWriteList.java
     * This class demonstrates how to use ReadWriteLock to add concurrency
     * features to a non-threadsafe collection
     * @author www.codejava.net
     */
    class ReadWriteList<E> {
        private List<E> list = new ArrayList<>();
        private ReadWriteLock rwLock = new ReentrantReadWriteLock();
     
        public ReadWriteList(E... initialElements) {
            list.addAll(Arrays.asList(initialElements));
        }
     
        public void add(E element) {
            Lock writeLock = rwLock.writeLock();
            writeLock.lock();
     
            try {
                list.add(element);
            } finally {
                writeLock.unlock();
            }
        }
     
        public E get(int index) {
            Lock readLock = rwLock.readLock();
            readLock.lock();
     
            try {
                return list.get(index);
            } finally {
                readLock.unlock();
            }
        }
     
        public int size() {
            Lock readLock = rwLock.readLock();
            readLock.lock();
     
            try {
                return list.size();
            } finally {
                readLock.unlock();
            }
        }
     
    }
    
    /**
     * Writer.java
     * This thread randomly read an element from a shared val structure
     * @author www.codejava.net
     */
    public class Writer extends Thread {
        private ReadWriteList<Integer> sharedList;
     
        public Writer(ReadWriteList<Integer> sharedList) {
            this.sharedList = sharedList;
        }
     
        public void run() {
            Random random = new Random();
            int number = random.nextInt(100);
            sharedList.add(number);
     
            try {
                Thread.sleep(100);
                System.out.println(getName() + " -> put: " + number);
            } catch (InterruptedException ie ) { ie.printStackTrace(); }
        }
    }
    
    /**
     * Reader.java
     * This thread randomly adds an element to a shared val structure
     * @author www.codejava.net
     */
    class Reader extends Thread {
        private ReadWriteList<Integer> sharedList;
     
        public Reader(ReadWriteList<Integer> sharedList) {
            this.sharedList = sharedList;
        }
     
        public void run() {
            Random random = new Random();
            int index = random.nextInt(sharedList.size());
            Integer number = sharedList.get(index);
     
            System.out.println(getName() + " -> get: " + number);
     
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie ) { ie.printStackTrace(); }
     
        }
    }

    
    public void runExample() {
        final int READER_SIZE = 10;
        final int WRITER_SIZE = 2;
        Integer[] initialElements = {33, 28, 86, 99};
        
        ReadWriteList<Integer> sharedList = new ReadWriteList<>(initialElements);
 
        /*for (int i = 0; i < WRITER_SIZE; i++) {
            new Writer(sharedList).start();
        }
 
        for (int i = 0; i < READER_SIZE; i++) {
            new Reader(sharedList).start();
        }*/
        
        //U can use executor service as well
        ExecutorService write = Executors.newFixedThreadPool(2);
        ExecutorService read = Executors.newFixedThreadPool(10);
        for (int i = 0; i < WRITER_SIZE; i++) {
            write.execute(new Writer(sharedList));
        }
 
        for (int i = 0; i < READER_SIZE; i++) {
            read.execute(new Reader(sharedList));
        }
        
        
        write.shutdown();
        read.shutdown();
    }
 
    public static void main(String[] args) {
        ReentrantReadWriteLockExample runExample = new ReentrantReadWriteLockExample();
        runExample.runExample();

    }
    
}
