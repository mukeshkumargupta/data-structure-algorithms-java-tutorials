package com.interview.dynamic.PartDTwoDDPArrayProblems;

import java.util.Arrays;

public class D_MinCostPath {
    /*
     * https://leetcode.com/problems/minimum-path-sum/
     * https://www.youtube.com/watch?v=_rgTlyky1uQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=11
     * Category: Medium
     * Status: Tricky
     *
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
     * Output: 7
     * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
     * Example 2:
     *
     * Input: grid = [[1,2,3],[4,5,6]]
     * Output: 12
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 200
     */
    public class MinCostPath {

        /*
        Time Complexity: O(N*M)

    Reason: At max, there will be N*M calls of recursion.

    Space Complexity: O((M-1)+(N-1)) + O(N*M)

    Reason: We are using a recursion stack space: O((M-1)+(N-1)), here (M-1)+(N-1) is the path length and an external DP Array of size ‘N*M’.
         */
        // Helper function to calculate the minimum sum path in the matrix
        //Derive question find max path sum , two changes could be not run but it sud be dp[i][j] = grid[i][j] + Math.max(up, left); and base case if (i < 0 || j < 0)
        //                return Integer.MIN_VALUE;
        static int minPathSumUtil(int i, int j, int[][] grid, int[][] dp) {
            // Base cases
            if (i == 0 && j == 0)
                return grid[0][0]; // If we're at the top-left cell, return its value
            if (i < 0 || j < 0)
                return Integer.MAX_VALUE; // If we're out of bounds, return a large value
            if (dp[i][j] != -1)
                return dp[i][j]; // If we've already calculated this cell, return the stored result

            // Calculate the sum of the current cell plus the minimum sum path from above and from the left
            int up = minPathSumUtil(i - 1, j, grid, dp);
            int left = minPathSumUtil(i, j - 1, grid, dp);

            // Store the minimum of the two possible paths
            return dp[i][j] = grid[i][j] + Math.min(up, left);
        }

        // Main function to calculate the minimum sum path in the matrix
        static int minPathSum(int[][] grid) {
            int R = grid.length;
            int C = grid[0].length;
            int dp[][] = new int[R][C];

            // Initialize the DP matrix with -1
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the helper function starting from the bottom-right cell
            return minPathSumUtil(R - 1, C - 1, grid, dp);
        }

        public int minPathSumTabulation(int[][] grid) {
            int R = grid.length; //Row
            int C = grid[0].length; //Column
            int i, j;
            int dp[][] = new int[R][C];
            dp[0][0] = grid[0][0];

            /* Initialize first column of total cost(dp) array */
            for (i = 1; i < R; i++)
                dp[i][0] = dp[i-1][0] + grid[i][0];

            /* Initialize first row of dp array */
            for (j = 1; j < C; j++)
                dp[0][j] = dp[0][j-1] + grid[0][j];

            /* Construct rest of the dp array */
            for (i = 1; i < R; i++)
                for (j = 1; j < C; j++)
                    dp[i][j] = Math.min(
                            dp[i-1][j],
                            dp[i][j-1]) + grid[i][j];

            return dp[R-1][C-1];
        }

        public static void main(String args[]) {
            // Define the matrix
            int matrix[][] = {
                    {5, 9, 6},
                    {11, 5, 2}
            };

            // Calculate and print the minimum sum path in the matrix
            System.out.println(minPathSum(matrix));
        }

    }
}
