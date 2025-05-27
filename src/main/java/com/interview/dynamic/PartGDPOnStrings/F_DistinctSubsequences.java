package com.interview.dynamic.PartGDPOnStrings;

import java.util.Arrays;

public class F_DistinctSubsequences {
    /*
    https://www.youtube.com/watch?v=nVG7eTiD2bY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=34
    Category: Hard, VVImp, Tricky

    LeetCode Problem: https://leetcode.com/problems/distinct-subsequences/

    Related Problems:
    - https://leetcode.com/problems/number-of-unique-good-subsequences/ (Hard)

    📄 Problem:
    Given two strings s and t, return the number of distinct subsequences of s which equals t.

    The test cases are generated so that the answer fits on a 32-bit signed integer.

    📚 Example 1:
    Input: s = "rabbbit", t = "rabbit"
    Output: 3
    Explanation:
    There are 3 ways to generate "rabbit" from "rabbbit":
    1. rab_bbit
    2. rabb_bit
    3. rabb_bi_t

    📚 Example 2:
    Input: s = "babgbag", t = "bag"
    Output: 5
    Explanation:
    There are 5 ways to generate "bag" from "babgbag".

    🔥 Constraints:
    - 1 <= s.length, t.length <= 1000
    - s and t consist of English letters only.
*/
    public static class DistinctSubsequences {
        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

            Space Complexity: O(N*M) + O(N+M)

            Reason: We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
         */
        static int prime = (int) (Math.pow(10, 9) + 7);

        // Function to count the number of distinct subsequences of s1 that are equal to s2
        static int countUtil(String s1, String s2, int ind1, int ind2, int[][] dp) {
            // If we have exhausted s2, there's one valid subsequence (empty string) in s1.
            if (ind2 < 0)
                return 1;
            // If we have exhausted s1 but not s2, there are no valid subsequences.
            if (ind1 < 0)
                return 0;

            // If the result is already computed, return it.
            if (dp[ind1][ind2] != -1)
                return dp[ind1][ind2];

            // If the characters at the current positions match, we can either leave one character from s1
            // or continue to the next character in s1 while staying at the same character in s2.
            if (s1.charAt(ind1) == s2.charAt(ind2)) {
                int leaveOne = countUtil(s1, s2, ind1 - 1, ind2 - 1, dp);
                int stay = countUtil(s1, s2, ind1 - 1, ind2, dp);

                // Add the two possibilities and take modulo prime to avoid integer overflow.
                return dp[ind1][ind2] = (leaveOne + stay) % prime;
            } else {
                // If the characters don't match, we can only continue to the next character in s1.
                return dp[ind1][ind2] = countUtil(s1, s2, ind1 - 1, ind2, dp);
            }
        }

        // Function to calculate the count of distinct subsequences of s1 equal to s2
        static int subsequenceCounting(String s1, String s2, int lt, int ls) {
            // Initialize a DP array to store intermediate results
            int dp[][] = new int[lt][ls];
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Call the recursive helper function to compute the count
            return countUtil(s1, s2, lt - 1, ls - 1, dp);
        }


        static int countUtilMoveIndexByOne(String s1, String s2, int ind1, int ind2, int[][] dp) {
            // If we have exhausted s2, there's one valid subsequence (empty string) in s1.
            if (ind2 == 0)
                return 1;
            // If we have exhausted s1 but not s2, there are no valid subsequences.
            if (ind1 == 0)
                return 0;

            // If the result is already computed, return it.
            if (dp[ind1][ind2] != -1)
                return dp[ind1][ind2];

            // If the characters at the current positions match, we can either leave one character from s1
            // or continue to the next character in s1 while staying at the same character in s2.
            if (s1.charAt(ind1-1) == s2.charAt(ind2-1)) {
                int leaveOne = countUtilMoveIndexByOne(s1, s2, ind1 - 1, ind2 - 1, dp);
                int stay = countUtilMoveIndexByOne(s1, s2, ind1 - 1, ind2, dp);

                // Add the two possibilities and take modulo prime to avoid integer overflow.
                return dp[ind1][ind2] = (leaveOne + stay) % prime;
            } else {
                // If the characters don't match, we can only continue to the next character in s1.
                return dp[ind1][ind2] = countUtil(s1, s2, ind1 - 1, ind2, dp);
            }
        }
        public static int distinctSubsequencesMoveIndexByOne(String str, String sub) {
            int lt = str.length();
            int ls = sub.length();
            // Initialize a DP array to store intermediate results
            int dp[][] = new int[lt+1][ls+1];
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Call the recursive helper function to compute the count
            return countUtil(str, sub, lt, ls, dp);
        }
        /*
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M’. Stack Space is eliminated.
         */

        // Function to calculate the count of distinct subsequences of s1 equal to s2
        static int subsequenceCountingTabulation(String s1, String s2, int n, int m) {
            // Create a 2D array to store the counts of subsequences
            int dp[][] = new int[n + 1][m + 1];

            // Initialize the first column with 1 because there's one empty subsequence in any string.
            for (int i = 0; i < n + 1; i++) {
                dp[i][0] = 1;
            }

            // Initialize the first row (except dp[0][0]) with 0 because there's no way to form s2 from an empty string.
            for (int i = 1; i < m + 1; i++) {
                dp[0][i] = 0;
            }

            // Fill the dp array using a bottom-up approach
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        // If the characters match, we can either include this character in the subsequence
                        // or exclude it. So, we add the counts from both possibilities.
                        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % prime;
                    } else {
                        // If the characters don't match, we can only exclude this character.
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[n][m];
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops.

            Space Complexity: O(M)

            Reason: We are using an external array of size ‘M+1’ to store only one row.
         */
        // Function to calculate the count of distinct subsequences of s1 equal to s2
        static int subsequenceCountingSpaceOptimized(String s1, String s2, int n, int m) {
            // Create an array to store the counts of subsequences
            int[] prev = new int[m + 1];

            // Initialize the first element to 1 because there's one empty subsequence in any string.
            prev[0] = 1;

            // Fill the prev array using a bottom-up approach
            for (int i = 1; i < n + 1; i++) {
                for (int j = m; j >= 1; j--) { // Reverse direction for updating

                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        // If the characters match, we can either include this character in the subsequence
                        // or exclude it. So, we add the counts from both possibilities.
                        prev[j] = (prev[j - 1] + prev[j]) % prime;
                    } else {
                        // If the characters don't match, we can only exclude this character.
                        prev[j] = prev[j]; // This statement is not necessary, as it doesn't change the value.
                    }
                }
            }

            return prev[m];
        }


        public static void main(String args[]) {
            String s1 = "babgbag";
            String s2 = "bag";

            System.out.println("The Count of Distinct Subsequences is " +
                    subsequenceCounting(s1, s2, s1.length(), s2.length()));
        }
    }
}
