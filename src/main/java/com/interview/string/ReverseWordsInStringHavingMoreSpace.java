package com.interview.string;


public class ReverseWordsInStringHavingMoreSpace {

	// Reference: https://leetcode.com/problems/reverse-words-in-a-string/
	// Category: Must Do
	/*
	Explanation of Improvements
Removed Redundant Check for Empty Word: The check for an empty first element is unnecessary after using trim() and replacing spaces.
Simplified Result Append: Using a single append operation avoids conditionally handling the last word.
Used StringBuilder: StringBuilder is more efficient than StringBuffer for single-threaded operations.
Concise Swapping and Reversing: Combined increment and decrement in the loop condition to streamline reverseString.
	 */


	// Reverses a portion of the character array from start to end
	public String reverseWords(String s) {
		// Step 1: Trim extra spaces and split the string into words
		String trimmedInput = s.trim().replaceAll("\\s+", " ");
		String[] words = trimmedInput.split(" ");

		// Step 2: Use a StringBuilder to append words in reverse order
		StringBuilder result = new StringBuilder();
		for (int i = words.length - 1; i >= 0; i--) {
			result.append(words[i]);
			if (i > 0) result.append(" ");
		}

		// Return the final reversed words as a string
		return result.toString();
	}

	public static void main(String[] args) {
		ReverseWordsInString reverser = new ReverseWordsInString();
		System.out.println(reverser.reverseWords("  hello world!  "));
	}

}
