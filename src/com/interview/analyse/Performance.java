package com.interview.analyse;
/**
 * Date 05/21/2019
 * @author Mukesh Kumar Gupta
 *
 *
 * Reference
 * https://www.vogella.com/tutorials/JavaPerformance/article.html
 * Category: Must Do
 */
public class Performance {
	long startTime;

	public void startTime() {
		startTime = System.currentTimeMillis();
	}
	public long  totalTimeInMillis() {
		return System.currentTimeMillis() - startTime;
	}
	public static void main(String [] args) {
		Performance pf = new Performance();
		pf.startTime();
		long total = 0;
        for (int i = 0; i < 10000000; i++) {
            total += i;
        }
        System.out.println("Total run time:" +  pf.totalTimeInMillis() + "ms"+ " with calculated value:" + total);
	}
}
