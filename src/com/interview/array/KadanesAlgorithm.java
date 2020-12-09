package com.interview.array;

import java.util.*;
/*
 *  Reference: https://www.youtube.com/watch?v=kekmCQXYwQ0&list=PLIA-9QRQ0RqGP4zrm09iyiVSXU-3e6CfP&index=25&t=853s
 *  Derived question must found the starting and ending index
 * //Reference: https://practice.geeksforgeeks.org/problems/kadanes-algorithm/0
 * //Category: Must Do
 * //Note code is not passing here but on leet code it is passed
 * //Company: Groupon
 */
public class KadanesAlgorithm {
	public static void main(String[] args) {
		// code
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 1; i <= T; i++) {
			int N = sc.nextInt();
			int[] input = new int[N];
			// scan input
			for (int j = 0; j < N; j++) {
				input[j] = sc.nextInt();
			}
			int maxSoFarSum = input[0];
			int maxTillSum = 0;
			for (int k = 0; k < N; k++) {
				maxTillSum += input[k];
				if (maxTillSum > maxSoFarSum) {
					maxSoFarSum = maxTillSum;
				}
				if (maxTillSum < 0) {
					maxTillSum = 0;
				}
			}
			System.out.println(maxSoFarSum);
		}
	}
}
