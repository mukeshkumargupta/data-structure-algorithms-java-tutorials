package com.interview.recursionBacktracking;

import java.util.*;
/*
 * https://leetcode.com/problems/palindrome-partitioning/
 * Category: Medium, Must Do
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.
Related:https://leetcode.com/problems/palindrome-partitioning-ii/ Hard
https://leetcode.com/problems/palindrome-partitioning-iv/ Hard
  

 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
 

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>(); 
        func(0, s.length(), s, new ArrayList<>(), result);
        return result;
    }
    
    void func(int index, int N, String s, List<String> ds, List<List<String>> result) {//Runtime: 8 ms, faster than 77.54% of Java online submissions for Palindrome Partitioning.
        if(index == s.length()) {
            result.add(new ArrayList<>(ds));
            return;
        }
        for(int i = index; i < N; ++i) {
            if(isPalindrome(s, index, i)) {
                ds.add(s.substring(index, i+1));
                func(i+1, N, s, ds, result);
                ds.remove(ds.size()-1);
            }
        }
    }
    
    boolean isPalindrome(String s, int start, int end) {
        while(start <= end) {
            if(s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }
}
