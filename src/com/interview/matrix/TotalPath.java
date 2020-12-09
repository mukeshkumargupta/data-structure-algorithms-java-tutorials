package com.interview.matrix;

import java.util.*;

//Reference: https://practice.geeksforgeeks.org/problems/number-of-paths/0 It is not passing here becaus eof some environment issue
//Reference: https://leetcode.com/problems/unique-paths/submissions/ paased here
//Category Must Do
//Status: Done
public class TotalPath {
	public static void main(String[] args) {
		// code
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			int M = sc.nextInt();
			int N = sc.nextInt();
			if (M == 1 || N == 1) {
				System.out.println(1);
				return;
			}
			int output[][] = new int[M][N];
			// fill first row
			for (int c1 = 0; c1 < N; c1++) {
				output[0][c1] = 1;
			}
			// fill first column
			for (int r1 = 0; r1 < M; r1++) {
				output[r1][0] = 1;
			}
			// Fill matrix
			// Calculate
			for (int r1 = 1; r1 < M; r1++) {
				for (int c1 = 1; c1 < N; c1++) {
					output[r1][c1] = output[r1 - 1][c1] + output[r1][c1 - 1];
				}
			}
			System.out.println(output[M - 1][N - 1]);

		}
	}

}
