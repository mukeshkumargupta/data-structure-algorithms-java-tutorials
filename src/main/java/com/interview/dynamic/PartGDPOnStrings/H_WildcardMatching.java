package com.interview.dynamic.PartGDPOnStrings;

import java.util.Arrays;

public class H_WildcardMatching {
    /*
https://www.youtube.com/watch?v=ZmlQ3vgAOMo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=35
 */
    /*
     * Date 02/11/2017
     * @author Mukesh Kumar Gupta
     *
     * Wild car matching with ? and *
     *
     * Reference
     * https://leetcode.com/problems/wildcard-matching/
     * https://www.youtube.com/watch?v=3ZDZ-N0EPV0
     * Category: Hard, Must Do, Top150, Facebook, FAANG
     * https://leetcode.com/problems/basic-calculator/ Hard, Basic calculate has three version, try all, VVImp similar to https://leetcode.com/problems/evaluate-reverse-polish-notation/
     * https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/ Medium, Locked, It looks similar to sliding window, just explore in future
     * https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/ Medium, VVImp
     * Related: https://leetcode.com/problems/lemonade-change/ Easy
     * https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/ Medium
     * https://leetcode.com/problems/latest-time-by-replacing-hidden-digits/ Easy
     *
     * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
    The matching should cover the entire input string (not partial).



    Example 1:

    Input: s = "aa", p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    Example 2:

    Input: s = "aa", p = "*"
    Output: true
    Explanation: '*' matches any sequence.
    Example 3:

    Input: s = "cb", p = "?a"
    Output: false
    Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
    Example 4:

    Input: s = "adceb", p = "*a*b"
    Output: true
    Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
    Example 5:

    Input: s = "acdcb", p = "a*c?b"
    Output: false


    Constraints:

    0 <= s.length, p.length <= 2000
    s contains only lowercase English letters.
    p contains only lowercase English letters, '?' or '*'.
     *
     */
    public static class WildcardMatching {
        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

            Space Complexity: O(N*M) + O(N+M)

            Reason: We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
         */
        // Helper function to check if all characters from index 0 to i in S1 are '*'
        static boolean isAllStars(String S1, int i) {
            for (int j = 0; j <= i; j++) {
                if (S1.charAt(j) != '*')
                    return false;
            }
            return true;
        }

        // Recursive function to perform wildcard pattern matching
        static int wildcardMatchingUtil(String S1, String S2, int i, int j, int[][] dp) {
            // Base Cases
            if (i < 0 && j < 0)
                return 1; // Both strings are empty, and the pattern matches.
            if (i < 0 && j >= 0)
                return 0; // S1 is empty, but there are characters left in S2.
            if (j < 0 && i >= 0)
                return isAllStars(S1, i) ? 1 : 0; // S2 is empty, check if remaining characters in S1 are all '*'.

            // If the result is already computed, return it.
            if (dp[i][j] != -1) return dp[i][j];

            // If the characters match or S1 has a '?', continue matching the rest of the strings.
            if (S1.charAt(i) == S2.charAt(j) || S1.charAt(i) == '?')
                return dp[i][j] = wildcardMatchingUtil(S1, S2, i - 1, j - 1, dp);

            else {
                if (S1.charAt(i) == '*') {
                    // Two possibilities when encountering '*':
                    // 1. '*' matches one or more characters in S2.
                    // 2. '*' matches zero characters in S2.
                    return dp[i][j] = (wildcardMatchingUtil(S1, S2, i - 1, j, dp) == 1 || wildcardMatchingUtil(S1, S2, i, j - 1, dp) == 1) ? 1 : 0;
                } else {
                    // Characters don't match, and S1[i] is not '*'.
                    return 0;
                }
            }
        }

        // Main function to check if S1 matches the wildcard pattern S2
        static int wildcardMatching(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            int dp[][] = new int[n][m];
            for (int row[]: dp)
                Arrays.fill(row, -1);

            // Call the recursive helper function
            return wildcardMatchingUtil(S1, S2, n - 1, m - 1, dp);
        }

        static boolean isAllStarsMoveOneIndexRight(String S1, int i) {
            for (int j = 1; j <= i; j++) {
                if (S1.charAt(j-1) != '*')
                    return false;
            }
            return true;
        }


        // Recursive function to perform wildcard pattern matching
        static int wildcardMatchingUtilMoveOneIndexRight(String S1, String S2, int i, int j, int[][] dp) {
            // Base Cases
            if (i == 0 && j == 0)
                return 1; // Both strings are empty, and the pattern matches.
            if (i == 0 && j > 0)
                return 0; // S1 is empty, but there are characters left in S2.
            if (j == 0 && i > 0)
                return isAllStarsMoveOneIndexRight(S1, i) ? 1 : 0; // S2 is empty, check if remaining characters in S1 are all '*'.

            // If the result is already computed, return it.
            if (dp[i][j] != -1) return dp[i][j];

            // If the characters match or S1 has a '?', continue matching the rest of the strings.
            if (S1.charAt(i-1) == S2.charAt(j-1) || S1.charAt(i-1) == '?')
                return dp[i][j] = wildcardMatchingUtilMoveOneIndexRight(S1, S2, i - 1, j - 1, dp);

            else {
                if (S1.charAt(i-1) == '*') {
                    // Two possibilities when encountering '*':
                    // 1. '*' matches one or more characters in S2.
                    // 2. '*' matches zero characters in S2.
                    return dp[i][j] = (wildcardMatchingUtilMoveOneIndexRight(S1, S2, i - 1, j, dp) == 1 || wildcardMatchingUtilMoveOneIndexRight(S1, S2, i, j - 1, dp) == 1) ? 1 : 0;
                } else {
                    // Characters don't match, and S1[i] is not '*'.
                    return 0;
                }
            }
        }

        // Main function to check if S1 matches the wildcard pattern S2
        static int wildcardMatchingMoveOneIndexRight(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            int dp[][] = new int[n+1][m+1];
            for (int row[]: dp)
                Arrays.fill(row, -1);

            // Call the recursive helper function
            return wildcardMatchingUtilMoveOneIndexRight(S1, S2, n, m, dp);
        }

        public boolean isMatch(String s, String p) {
            return wildcardMatchingMoveOneIndexRight(p, s) == 1? true: false;


        }


        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M’. Stack Space is eliminated.
         */
        // Function to perform wildcard pattern matching
        static boolean wildcardMatchingTabulation(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            // Create a 2D array to store the matching results
            boolean dp[][] = new boolean[n + 1][m + 1];
            dp[0][0] = true;

            // Initialize the first row and column based on wildcard '*' in S1
            for (int j = 1; j <= m; j++) {
                dp[0][j] = false;
            }
            for (int i = 1; i <= n; i++) {
                dp[i][0] = isAllStars(S1, i);
            }

            // Fill the dp array using a bottom-up approach
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (S1.charAt(i - 1) == S2.charAt(j - 1) || S1.charAt(i - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1]; // Characters match or '?' is encountered.
                    } else {
                        if (S1.charAt(i - 1) == '*') {
                            dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // '*' matches one or more characters.
                        } else {
                            dp[i][j] = false; // Characters don't match, and S1[i-1] is not '*'.
                        }
                    }
                }
            }

            return dp[n][m]; // The final result indicates whether S1 matches S2.
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops.

            Space Complexity: O(M)

            Reason: We are using an external array of size ‘M+1’ to store two rows.
         */
        // Function to perform wildcard pattern matching
        static boolean wildcardMatchingSpcaeOptimized(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            // Create two boolean arrays to store the matching results for the current and previous rows
            boolean[] prev = new boolean[m + 1];
            boolean[] cur = new boolean[m + 1];

            // Initialize the first element of prev as true
            prev[0] = true;

            // Iterate through S1 and S2 to fill the cur array
            for (int i = 1; i <= n; i++) {
                // Initialize the first element of cur based on whether S1 contains '*'
                cur[0] = isAllStars(S1, i);
                for (int j = 1; j <= m; j++) {
                    if (S1.charAt(i - 1) == S2.charAt(j - 1) || S1.charAt(i - 1) == '?') {
                        cur[j] = prev[j - 1]; // Characters match or '?' is encountered.
                    } else {
                        if (S1.charAt(i - 1) == '*') {
                            cur[j] = prev[j] || cur[j - 1]; // '*' matches one or more characters.
                        } else {
                            cur[j] = false; // Characters don't match, and S1[i-1] is not '*'.
                        }
                    }
                }
                // Update prev array to store the current values
                prev = cur.clone();
            }

            return prev[m]; // The final result indicates whether S1 matches S2.
        }


        public static void main(String args[]) {
            String S1 = "ab*cd";
            String S2 = "abdefcd";

            if (wildcardMatching(S1, S2) == 1)
                System.out.println("String S1 and S2 do match");
            else
                System.out.println("String S1 and S2 do not match");
        }
    }
}
