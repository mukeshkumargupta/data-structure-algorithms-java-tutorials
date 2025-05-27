package com.interview.linklist.PartABasicLinkedListOperations.F_PalindromePatterns;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/longest-palindrome/
 * Category: Easy, Tricky, Google, Palindrome
 * Related:
 * https://leetcode.com/problems/palindrome-permutation/ Easy
 * https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/ Medium
 * https://leetcode.com/problems/largest-palindromic-number/ Medium
 *
 * 🔹 Problem Statement:
 * Given a string `s` consisting of lowercase or uppercase letters, return the length
 * of the longest palindrome that can be built using those letters.
 *
 * Letters are case-sensitive, meaning "Aa" is NOT considered a palindrome.
 *
 * 🔹 Examples:
 * Example 1:
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome is "dccaccd", whose length is 7.
 *
 * Example 2:
 * Input: s = "a"
 * Output: 1
 *
 * Example 3:
 * Input: s = "bb"
 * Output: 2
 *
 * 🔹 Complexity Analysis:
 * - Time Complexity: O(N) where N is the length of `s`
 * - Space Complexity: O(1) since we store at most 52 characters in a fixed-sized map
 */

public class H_A_LongestPalindrome {
    public int longestPalindrome(String s) {
        int length = s.length();
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < length; i++) {
            m.put(s.charAt(i), m.getOrDefault(s.charAt(i), 0) +1);
        }
        int result = 0;
        boolean anyOddFound = false;
        for (Character c : m.keySet()) {
            //if even
            if (m.get(c) % 2 == 0) {
                result += m.get(c);
            }
            if (m.get(c) % 2 != 0) {
               result += m.get(c) -1; 
                anyOddFound = true;
            }  
        }
        if (anyOddFound) {
            result += 1;
        }
        return result;
        
    }
}
