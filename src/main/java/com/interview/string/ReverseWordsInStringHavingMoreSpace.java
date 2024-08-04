package com.interview.string;


public class ReverseWordsInStringHavingMoreSpace {

	// Reference: https://leetcode.com/problems/reverse-words-in-a-string/
	// Category: Must Do

	public static void swap(char[] input, int i, int j) {
		char temp = input[j];
		input[j] = input[i];
		input[i] = temp;
	}

	public static void reverseString(char[] input, int start, int end) {
		while (start < end) {
			swap(input, start, end);
			start++;
			end--;
		}

	}

	public String reverseWords(String s) {
		// Reference:
		// https://howtodoinjava.com/java/string/remove-extra-whitespaces-between-words/
		String removedExtraSpace = s.replaceAll("\\s+", " ");
		String[] word = removedExtraSpace.split(" ");
		int j =0;
		if (word.length > 0 && word[0].isEmpty()) {
			//ignore first array
			j =1;
		}
		StringBuffer sb = new StringBuffer();
		for (; j < word.length; j++) {
			char[] charArray = word[j].toCharArray();
			reverseString(charArray, 0, word[j].length() - 1);
			if (j < word.length - 1) {
				sb.append(charArray);
				sb.append(" ");
			}
			if (j == word.length - 1) {
				// Dont put string otherwise it will append address
				sb.append(charArray);
			}

		}
		char[] output = sb.toString().toCharArray();
		reverseString(output, 0, sb.length() - 1);
		// Dont print toString otherwise it will print reference so pass array
		StringBuffer returnOutput = new StringBuffer();
		returnOutput.append(output);
		
		return returnOutput.toString();
	}
	
	public static void main(String[] args) {
		ReverseWordsInStringHavingMoreSpace rsw = new ReverseWordsInStringHavingMoreSpace();
		System.out.println(rsw.reverseWords("  hello world!  ").toCharArray());
		
	}

}
