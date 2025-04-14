package com.interview.string;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/reverse-words-in-a-string/
 * Category: Medium
 * Tricky
 */



public class ReverseWordsInString {
    /*
    Explanation:
    Trim removes extra spaces at the start and end.

    Split breaks the string into words using \\s+, ensuring multiple spaces are handled correctly.

    Reverse iteration appends words to StringBuilder, maintaining correct order.

    Trim the final result to remove any trailing spaces.
     */

    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+"); // Split by one or more spaces
        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]).append(" ");
        }

        return result.toString().trim(); // Remove trailing space
    }
    public static void main(String[] args) {
        ReverseWordsInString solution = new ReverseWordsInString();
        System.out.println(solution.reverseWords("  the sky is blue  ")); // "blue is sky the"
        System.out.println(solution.reverseWords(" hello world  "));      // "world hello"
        System.out.println(solution.reverseWords("a good   example"));    // "example good a"
    }
    
}
