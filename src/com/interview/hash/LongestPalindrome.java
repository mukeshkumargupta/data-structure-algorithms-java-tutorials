package com.interview.hash;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/longest-palindrome/
 * Category: Easy, Tricky, Google
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome here.

 

Example 1:

Input: s = "abccccdd"
Output: 7
Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
Example 2:

Input: s = "a"
Output: 1
Example 3:

Input: s = "bb"
Output: 2
 */
public class LongestPalindrome {
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
