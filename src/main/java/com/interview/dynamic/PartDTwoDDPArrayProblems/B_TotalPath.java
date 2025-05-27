package com.interview.dynamic.PartDTwoDDPArrayProblems;

import java.util.Arrays;

public class B_TotalPath {
    /*
     * Reference: https://leetcode.com/problems/unique-paths/
     * https://www.youtube.com/watch?v=sdE0A2Oxofw
     * https://www.youtube.com/watch?v=rBAxUTqvlQA&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=8
     * Category: Medium, Must Do, Top150
     * Related:https://leetcode.com/problems/unique-paths-ii/ Medium
     * https://leetcode.com/problems/dungeon-game/ Hard
     * https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/ Medium
     */

    public static class TotalPath {
        /* Without memoization TC:O( 2 power  (M*N))
        after memoization
        Time Complexity: O(M*N)

Reason: At max, there will be M*N calls of recursion.

Space Complexity: O((N-1)+(M-1)) + O(M*N)

Reason: We are using a recursion stack space: O((N-1)+(M-1)), here (N-1)+(M-1) is the path length and an external DP Array of size ‘M*N’.

         */
        public int solveUtilRecursive(int i, int j, int[][] dp) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
Memory Usage: 40.9 MB, less than 5.23% of Java online submissions for Unique Paths.
         */
            if (i == 0 && j == 0) {
                return 1;
            }

            if (i < 0 || j < 0) {
                return 0;
            }

            if (dp[i][j] != -1) {
                return dp[i][j];
            }


            int up = 0;
            int left = 0;
            if (i > 0) {
                up = solveUtilRecursive(i - 1, j, dp);
            }
            if (j > 0) {
                left = solveUtilRecursive(i, j - 1, dp);
            }
            return dp[i][j] = up + left;
        }

        public int solveRecursive(int m, int n) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
Memory Usage: 40.9 MB, less than 5.23% of Java online submissions for Unique Paths.
         */
            // Create a 2D DP array to store the results
            int[][] dp = new int[m][n];

            // Initialize the DP array with -1 to indicate uncomputed values
            for (int[] row : dp)
                Arrays.fill(row, -1);

            // Start the recursive calculation from the bottom-right cell (m-1, n-1)
            return solveUtilRecursive(m - 1, n - 1, dp);
        }

        /*
        Time Complexity: O(M*N)

        Reason: There are two nested loops

        Space Complexity: O(M*N)

        Reason: We are using an external array of size ‘M*N’’.
         */
        public int solveBottomUp(int m, int n) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
            // Create a 2D DP array to store the results
            int dp[][] = new int[m][n];

            // Initialize the DP array with -1 to indicate uncomputed values
            for (int[] row : dp)
                Arrays.fill(row, -1);

            // Loop through each cell in the grid
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // Base condition: If we are at the top-left cell (0, 0), there's one way to reach it.
                    if (i == 0 && j == 0) {
                        dp[i][j] = 1;
                    } else {
                        int up = 0;
                        int left = 0;

                        // Calculate the number of ways by moving up (if possible) and left (if possible)
                        if (i > 0)
                            up = dp[i - 1][j];
                        if (j > 0)
                            left = dp[i][j - 1];

                        // Store the total number of ways to reach the current cell in the DP array
                        dp[i][j] = up + left;
                    }
                }
            }

            // Return the number of ways to reach the bottom-right cell (m-1, n-1)
            return dp[m - 1][n - 1];

        }

        /*
        Time Complexity: O(M*N)

Reason: There are two nested loops

Space Complexity: O(N)

Reason: We are using an external array of size ‘N’ to store only one row.
Write your own version of memoization
         */
        public int solveBottomUpSpaceOptimized(int m, int n) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
            // Create an array to store the results for the previous row
            int prev[] = new int[n];

            for (int i = 0; i < m; i++) {
                // Create a temporary array to store the results for the current row
                int temp[] = new int[n];

                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0) {
                        // Base condition: There's one way to reach the top-left cell (0, 0)
                        temp[j] = 1;
                        continue;
                    }

                    int up = 0;
                    int left = 0;

                    // Calculate the number of ways by moving up (if possible) and left (if possible)
                    if (i > 0)
                        up = prev[j];
                    if (j > 0)
                        left = temp[j - 1];

                    // Store the total number of ways to reach the current cell in the temporary array
                    temp[j] = up + left;
                }

                // Set the temporary array as the previous array for the next row
                prev = temp;
            }
            // Return the number of ways to reach the bottom-right cell (m-1, n-1)
            return prev[n - 1];
        }
    }
}
