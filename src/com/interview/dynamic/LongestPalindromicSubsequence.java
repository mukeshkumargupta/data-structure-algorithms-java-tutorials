package com.interview.dynamic;

/**
 * Date 08/01/2017
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * Category: Medium, Must Do
 *
 * Given a string find longest palindromic subsequence in this string.
 * Related: https://leetcode.com/problems/palindromic-substrings/ Medium
 * https://leetcode.com/problems/count-different-palindromic-subsequences/ Hard
 * https://leetcode.com/problems/longest-palindromic-subsequence-ii/ Medium
 * https://leetcode.com/problems/maximize-palindrome-length-from-subsequences/ Hard
 * https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/ Medium
 *
 * Time complexity - O(n2)
 * Space complexity - O(n2
 *
 * Youtube link - https://youtu.be/_nCsPn7_OgI
 *
 * References
 * http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
 * Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {//Runtime: 132 ms, faster than 8.24% of Java online submissions for Longest Palindromic Subsequence.
        int length = s.length();
        int[][] dp = new int [length][length];
        
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        
        for (int i = 0; i < length -1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
               dp[i][i+1] = 2; 
            } else {
                dp[i][i+1] = 1; 
            }
        }
        
        // Check for lengths greater than 2.
        // k is length of substring
        for (int k = 3; k <= length; ++k) {
 
            // Fix the starting index
            for (int i = 0; i < length - k + 1; ++i) {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;
 
                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] =  dp[i+1][j-1] +2;
                } else {
                   dp[i][j] =  Math.max(dp[i][j-1], dp[i+1][j]); 
                }
            }
        }
        return dp[0][length -1];
    }
    
    public static void main(String args[]){
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String str = "agbdba";
        int r1 = lps.longestPalindromeSubseq(str);
        System.out.print(r1 + " " + r1);
    }
    
}
