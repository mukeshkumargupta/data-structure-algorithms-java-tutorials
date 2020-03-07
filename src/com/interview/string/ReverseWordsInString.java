package com.interview.string;

import java.util.*;

//Reference: https://practice.geeksforgeeks.org/problems/reverse-words-in-a-given-string/0
//Category: Must Do

public class ReverseWordsInString {
	public void swap(char[] input, int i, int j) {
		char temp = input[j];
		input[j] = input[i];
		input[i] = temp;
	}

	public void reverseString(char[] input, int start, int end) {
		while (start < end) {
			swap(input, start, end);
			start++;
			end--;
		}

	}

	public static void main(String[] a) {
		ReverseWordsInString rws = new ReverseWordsInString();
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			String S = sc.next();
			// Reference:
			// https://javarevisited.blogspot.com/2016/02/2-ways-to-split-string-with-dot-in-java-using-regular-expression.html
			String[] word = S.split("\\.");
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < word.length; j++) {
				char[] charArray = word[j].toCharArray();
				rws.reverseString(charArray, 0, word[j].length() - 1);
				if (j < word.length - 1) {
					sb.append(charArray);
					sb.append(".");
				}
				if (j == word.length - 1) {
					// Dont put string otherwise it will append address
					sb.append(charArray);
				}

			}
			char[] ouput = sb.toString().toCharArray();
			rws.reverseString(ouput, 0, sb.length() - 1);
			// Dont print toString otherwise it will print reference so pass array
			System.out.println(ouput);
		}
	}

}
