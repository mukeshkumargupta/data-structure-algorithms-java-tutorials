package com.interview.dynamic.PartGDPOnStrings;

import java.util.Arrays;

public class G_EditDistance {
        /*
        https://www.youtube.com/watch?v=fJaKO8FbDdo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=34
     */

    /*
     * Date 07/07/2017
     * @author Mukesh Kumar Gupta
     * Reference: https://leetcode.com/problems/edit-distance/submissions/
     * Category: Hard, Must Do, Google, Facebook
     * Related:
     * https://leetcode.com/problems/one-edit-distance/ Medium
     * https://leetcode.com/problems/delete-operation-for-two-strings/ Medium
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/ Medium
     * https://leetcode.com/problems/uncrossed-lines/ Medium
     *
     * Given two strings how many minimum edits(update, delete or add) is needed to convert one string to another
     *
     * Time complexity is O(m*n)
     * Space complexity is O(m*n)
     *
     * References:
     * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
     * https://en.wikipedia.org/wiki/Edit_distance
     */

    public static class EditDistance {
        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

            Space Complexity: O(N*M) + O(N+M)

            Reason: We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
         */
        // Function to calculate the minimum edit distance between two strings
        static int editDistanceUtil(String S1, String S2, int i, int j, int[][] dp) {
            // Base cases
            if (i < 0)
                return j + 1;
            if (j < 0)
                return i + 1;

            // If the result is already computed, return it
            if (dp[i][j] != -1)
                return dp[i][j];

            // If the characters at the current positions match, no edit is needed
            if (S1.charAt(i) == S2.charAt(j))
                return dp[i][j] = 0 + editDistanceUtil(S1, S2, i - 1, j - 1, dp);

            else
                return dp[i][j] = 1 + Math.min(editDistanceUtil(S1, S2, i - 1, j - 1, dp), //1. Replace the character in S1 with the character in S2.
                        Math.min(editDistanceUtil(S1, S2, i - 1, j, dp), //2. Delete the character in S1.
                                editDistanceUtil(S1, S2, i, j - 1, dp))); //3. Insert the character from S2 into S1.
        }

        static int editDistance(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            int[][] dp = new int[n][m];
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the recursive helper function
            return editDistanceUtil(S1, S2, n - 1, m - 1, dp);
        }


        static int editDistanceUtilMovedIndexByOne(String S1, String S2, int i, int j, int[][] dp) {
            if (i == 0)
                return j;
            if (j == 0)
                return i;

            // If the result is already computed, return it
            if (dp[i][j] != -1)
                return dp[i][j];

            // If the characters at the current positions match, no edit is needed
            if (S1.charAt(i-1) == S2.charAt(j-1))
                return dp[i][j] = 0 + editDistanceUtilMovedIndexByOne(S1, S2, i - 1, j - 1, dp);

            else
                return dp[i][j] = 1 + Math.min(editDistanceUtilMovedIndexByOne(S1, S2, i - 1, j - 1, dp), //1. Replace the character in S1 with the character in S2.
                        Math.min(editDistanceUtilMovedIndexByOne(S1, S2, i - 1, j, dp), //2. Delete the character in S1.
                                editDistanceUtilMovedIndexByOne(S1, S2, i, j - 1, dp))); //3. Insert the character from S2 into S1.into S1.
        }
        public int minDistanceMovedIndexByOne(String word1, String word2) {
            String S1 = word1;
            String S2 = word2;
            int n = S1.length();
            int m = S2.length();

            int[][] dp = new int[n+1][m+1];
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the recursive helper function
            return editDistanceUtilMovedIndexByOne(S1, S2, n , m, dp);
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M’. Stack Space is eliminated.
         */

        // Function to calculate the minimum edit distance between two strings
        static int editDistanceTabulation(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            // Create a 2D array to store the minimum edit distances
            int[][] dp = new int[n + 1][m + 1];

            // Initialize the first row and column with their respective indices
            for (int i = 0; i <= n; i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j <= m; j++) {
                dp[0][j] = j;
            }

            // Fill the dp array using a bottom-up approach
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                        // If the characters match, no edit is needed, so take the value from the diagonal.
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // If the characters don't match, take the minimum of three possibilities:
                        // 1. Replace the character in S1 with the character in S2 (diagonal).
                        // 2. Delete the character in S1 (left).
                        // 3. Insert the character from S2 into S1 (up).
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
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

            Reason: We are using an external array of size ‘M+1’ to store two rows.
         */

        // Function to calculate the minimum edit distance between two strings
        static int editDistanceSpaceOptimized(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            // Create two arrays to store the previous and current rows of minimum edit distances
            int[] prev = new int[m + 1];
            int[] cur = new int[m + 1];

            // Initialize the first row with their respective indices
            for (int j = 0; j <= m; j++) {
                prev[j] = j;
            }

            // Fill the cur array using a bottom-up approach
            for (int i = 1; i <= n; i++) {
                cur[0] = i;
                for (int j = 1; j <= m; j++) {
                    if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                        // If the characters match, no edit is needed, so take the value from the diagonal.
                        cur[j] = prev[j - 1];
                    } else {
                        // If the characters don't match, take the minimum of three possibilities:
                        // 1. Replace the character in S1 with the character in S2 (diagonal).
                        // 2. Delete the character in S1 (left).
                        // 3. Insert the character from S2 into S1 (up).
                        cur[j] = 1 + Math.min(prev[j - 1], Math.min(prev[j], cur[j - 1]));
                    }
                }
                // Update prev array to store the current values
                prev = cur.clone();
            }

            return cur[m];
        }

        public static void main(String args[]) {
            String s1 = "horse";
            String s2 = "ros";

            System.out.println("The minimum number of operations required is: " +
                    editDistance(s1, s2));
        }
    }
}
