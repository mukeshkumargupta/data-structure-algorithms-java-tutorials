package com.interview.recursionBacktracking;

import java.util.*;
/*
 * https://leetcode.com/problems/palindrome-partitioning/
 * Category: Medium, Must Do, Top150, VVImp
 * https://www.youtube.com/watch?v=WBgsABoClE0
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

A palindrome string is a string that reads the same backward as forward.
Related:https://leetcode.com/problems/palindrome-partitioning-ii/ Hard VVImp
https://leetcode.com/problems/palindrome-partitioning-iv/ Hard VVImp
  

 

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
        /*
         * Runtime: 9 ms, faster than 88.54% of Java online submissions for Palindrome Partitioning.
Memory Usage: 54.9 MB, less than 82.66% of Java online submissions for Palindrome Partitioning.
         */
        List<List<String>> result = new ArrayList<>(); 
        partitionUtil(0, s.length()-1, s, new ArrayList<>(), result);
        return result;
    }
    
    void partitionUtil(int start, int end, String s, List<String> ds, List<List<String>> result) {//Runtime: 8 ms, faster than 77.54% of Java online submissions for Palindrome Partitioning.
        if(start == end+1) {
            result.add(new ArrayList<>(ds));
            return;
        }
        for(int i = start; i <= end; ++i) {
            if(isPalindrome(s, start, i)) {
                ds.add(s.substring(start, i+1));
                partitionUtil(i+1, end, s, ds, result);
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
