package com.interview.array;

import java.util.*;

//Reference: https://practice.geeksforgeeks.org/problems/count-the-triplets/0
//Category: Must Do
public class CountTheTriplets {
	public static void main(String[] args) {
		// code
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		class Point {
			public Point(int x, int y) {
				this.x = x;
				this.y = y;
			}
			public int x;
			public int y;
		}

		for (int i = 1; i <= T; i++) {
			ArrayList<Point> outputList = new ArrayList<Point>();
			int output = 0;
			int N = sc.nextInt();
			int[] input = new int[N];
			ArrayList<Integer> list = new ArrayList<Integer>();
			// scan input
			for (int j = 0; j < N; j++) {
				input[j] = sc.nextInt();
				list.add(input[j]);
			}

			Collections.sort(list);
			for (int k = N - 1; k > 1; k--) {
				int l = 0; 
				int r = k-1;
				for ( ; l < r; ) {
					if (list.get(l) + list.get(r) > list.get(k)) {
						r = r - 1;
					} else if (list.get(l) + list.get(r) < list.get(k)) {
						l = l + 1;
					} else {
						outputList.add(new Point(list.get(l), list.get(r)));
						output = output + 1;
						l = l + 1;
						r = r - 1;
					}

				}
			}
			if (output == 0) {
				System.out.println(-1);
			} else {
				System.out.println(output);
				/*for (int m = 0; m < outputList.size(); m++ ) {
					System.out.println(outputList.get(m).x + ", " + outputList.get(m).y);	
				}*/

			}
		}
	}
}
