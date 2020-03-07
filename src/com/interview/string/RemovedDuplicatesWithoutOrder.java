package com.interview.string;
import java.io.*;
import java.util.*;

//Reference: 
//Category: Must Do
//Geeksforggeeks

public class RemovedDuplicatesWithoutOrder {
	public static void main(String[] args)  throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			String S = br.readLine();
			char[] input = S.toCharArray();
			int startIndex = 0;
			Map<Character, Boolean> hm = new HashMap<Character, Boolean>();
			int nStringLength = S.length();
			for (int j = 0; j < nStringLength; j++) {

				if (hm.containsKey(input[j])) {
					continue;
				} else {
					hm.put(input[j], true);
					// new then write on nstart index then increment
					input[startIndex] = input[j];
					startIndex++;
				}

			}
			for (int k = 0; k < startIndex; k++) {
				System.out.print(input[k]);
			}

		}
	}

}
