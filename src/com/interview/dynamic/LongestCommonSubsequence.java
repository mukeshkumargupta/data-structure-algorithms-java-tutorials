package com.interview.dynamic;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * Category: Medium, Must Do
 * Reference: Takeyouforward
 * Related: https://leetcode.com/problems/delete-operation-for-two-strings/
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        /*
         * Runtime: 17 ms, faster than 67.70% of Java online submissions for Longest Common Subsequence.
Memory Usage: 46.5 MB, less than 36.07% of Java online submissions for Longest Common Subsequence.
         */
        int R = text1.length()+1;
        int C = text2.length()+1;
        int dp[][] = new int[R][C];
        //Fill first row with all zero
        for (int c1 = 0; c1 < C; c1++ ) {
            dp[0][c1] = 0;
        }
        //Fill first column with all zero
        for (int r1 = 0; r1 < R; r1++ ) {
            dp[r1][0] = 0;
        }
        
        for (int i = 1; i < R; i++) {
            for (int j =1 ; j < C; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {// If both are same
                    dp[i][j] = dp[i-1][j-1] +1; 

                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);  
                }       
            }
        }
        return dp[R-1][C-1];
        
    }
}
