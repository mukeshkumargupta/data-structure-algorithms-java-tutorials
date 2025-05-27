package com.interview.dynamic.PartGDPOnStrings;

import java.util.Arrays;

public class B_LongestPalindromicSubsequence {
    /*
     * Date 08/01/2017
     * @author Mukesh Kumar Gupta
     * https://www.youtube.com/watch?v=6i_T5kkfv4A&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=29
     * https://leetcode.com/problems/longest-palindromic-subsequence/
     * Category: Medium, Must Do
     * Derived Question: DP 29. Minimum Insertions to Make String Palindrome
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

    /*


        Complexity Analysis
        Time Complexity: O(N*N)

        Reason: There are two nested loops

        Space Complexity: O(N*N)

        Reason: We are using an external array of size ‘(N*N)’. Stack Space is eliminated.
     */
    public static class LongestPalindromicSubsequence {
        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcs(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store the LCS lengths
            int dp[][] = new int[n + 1][m + 1];

            // Initialize the dp array with -1
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Initialize the first row and first column with 0
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= m; i++) {
                dp[0][i] = 0;
            }

            // Fill the dp array using a bottom-up approach
            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    else
                        dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

            return dp[n][m];
        }

        // Function to find the length of the Longest Palindromic Subsequence
        static int longestPalindromeSubsequence(String s) {
            // Create a reversed version of the input string
            String reversed = new StringBuilder(s).reverse().toString();

            // Calculate the LCS of the original string and its reverse
            return lcs(s, reversed);
        }

        public static void main(String args[]) {
            String s = "bbabcbcab";

            System.out.print("The Length of Longest Palindromic Subsequence is ");
            System.out.println(longestPalindromeSubsequence(s));
        }
    }
}
