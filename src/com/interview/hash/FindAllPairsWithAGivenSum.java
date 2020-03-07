package com.interview.hash;

import java.util.*;
import java.io.*;

//Reference: https://practice.geeksforgeeks.org/problems/find-all-pairs-whose-sum-is-x/0
//Must Do

public class FindAllPairsWithAGivenSum {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for (int i = 0; i < T; i++) {
			String input = bf.readLine();
			String[] inputArrayString = input.split(" ");

			int firstArrayLength = Integer.parseInt(inputArrayString[0]);
			int secondArrayLength = Integer.parseInt(inputArrayString[1]);
			int sum = Integer.parseInt(inputArrayString[2]);
			Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
			Map<Integer, Integer> map2 = new TreeMap<Integer, Integer>();
			String inputString1 = bf.readLine();
			String[] inputArrayString1 = inputString1.split(" ");
			String inputString2 = bf.readLine();
			String[] inputArrayString2 = inputString2.split(" ");
			for (int j = 0; j < firstArrayLength; j++) {
				int value = Integer.parseInt(inputArrayString1[j]);
				map1.put(value, value);
			}
			for (int j = 0; j < secondArrayLength; j++) {
				int value = Integer.parseInt(inputArrayString2[j]);
				map2.put(value, value);
			}
			boolean firstFound = true;
			boolean foundSum = false;
			Set<Integer> keys = map1.keySet();
			Iterator<Integer> itr = keys.iterator();
			while (itr.hasNext()) {
				int value1 = map1.get(itr.next());
				if (map2.containsKey(sum - value1)) {
					int value2 = map2.get(sum - value1);
					if (sum == value1 + map2.get(value2)) {
						if (!firstFound) {
							System.out.print(", " + value1 + " " + value2);
							foundSum = true;
						} else {
							System.out.print(value1 + " " + value2);
							firstFound = false;
							foundSum = true;
						}

					}

				}
			}
			if (!foundSum) {
				System.out.print("-1");
			}
			System.out.println();
		}
	}
}
