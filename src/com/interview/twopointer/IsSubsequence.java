package com.interview.twopointer;

/*
 * https://leetcode.com/problems/is-subsequence/
 * https://www.youtube.com/watch?v=PwQnydsKk_I&list=PLIA-9QRQ0RqFaV3j0I4FMiPQQkG-jIouX&index=10
 * Category: Easy, Must Do, Google
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
Related: https://leetcode.com/problems/number-of-matching-subsequences/ Medium
https://leetcode.com/problems/shortest-way-to-form-string/ Medium
 

Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false
 

Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.
 

Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int l1 = s.length();
        int l2 = t.length();
        int p1 = 0; 
        int p2 = 0;
        while (p1 < l1 && p2 < l2) {
            if (s.charAt(p1) == t.charAt(p2)) {
                p1++;
                p2++;
            } else {
                p2++;
            }
        }
        if (p1 == l1) {
            return true;
        } else {
            return false;
        }
        

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
