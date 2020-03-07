package com.interview.multithreaded;

/*
 * Not complete
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

	public static void main(String[] args) {
		//Thread t1 = new AdderThread();
		//Thread t2 = new Thread(new ThreadConcepts.AdderRunnablThread());
	}

}
