package com.interview.multithreaded;

/*
 * Status: Done
 * I was trying here to start and call run by thread instance using runnable approach
 */

public class ThreadConcepts {
	public class AdderThread extends Thread {
		public AdderThread() {
			
		}
		public void run() {
			System.out.println("I am in adder thread " + "my Thrad info: " + Thread.currentThread().getName());		
		}
	}

	public class AdderRunnablThread implements Runnable{
		public AdderRunnablThread() {
			
		}
		public void run() {
			System.out.println("I am in adder runnabl thread " + "my Thrad info: " + Thread.currentThread().getName());		
		}
	}
	public void runExtendedThread() {
	    Thread t1 = new AdderThread();
	    t1.start();
	}
	
	public void runImplementedThread() {
	    Thread t1 = new Thread(new AdderRunnablThread());
        t1.start();
    }

	public static void main(String[] runExtendedThread) {
	    ThreadConcepts tc = new ThreadConcepts();
	    tc.runExtendedThread();
	    tc.runImplementedThread();

	}

}
