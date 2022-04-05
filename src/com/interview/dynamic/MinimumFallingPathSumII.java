package com.interview.dynamic;

/*
 * https://leetcode.com/problems/minimum-falling-path-sum-ii/
 * https://www.youtube.com/watch?v=N913Vsbfiws
 * Category: Medium, Must Do, Extension of minimum-falling-path-sum
 * Related: https://leetcode.com/problems/ones-and-zeroes/ Medium
 * https://leetcode.com/problems/squirrel-simulation/ Medium Locked
 * https://leetcode.com/problems/largest-plus-sign/ Medium
 * 
 * Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.

A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.

 

Example 1:


Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13
Explanation: 
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.
Example 2:

Input: grid = [[7]]
Output: 7
 

Constraints:

n == grid.length == grid[i].length
1 <= n <= 200
-99 <= grid[i][j] <= 99
 * 
 */
public class MinimumFallingPathSumII {
    public static boolean isValid(int row, int col, int R, int C) {
        return (row >= 0 && col >= 0 && row < R && col < C);
    }

    public static int getMinPathSumHelper(int row, int col, int[][] matrix, int[][] dp, int R, int C) {

        // Base case: we are at last row
        if (row == R - 1) {
            return matrix[row][col];
      
        }

        // Check whether we have already a solution or not?
        if (dp[row][col] != Integer.MAX_VALUE) {
            return dp[row][col];
        }

        int minPathSum, currPathSum = Integer.MAX_VALUE;

        // Get the minimum path sum from (row,col) to the any cell of last row,
        // excluding (row,col) cell
        for (int i = 0; i < C; i++) {
            if (i == col) {
                continue;
            }
            // Get new cell
            int nextRow = row + 1;
            int nextCol = i;

            // Check whether new cell is a valid cell or not?
            if (isValid(nextRow, nextCol, R, C)) {
                currPathSum = Math.min(currPathSum, getMinPathSumHelper(nextRow, nextCol, matrix, dp, R, C));
            }
        }

        // Include (row, col) cell into minimum path sum
        minPathSum = matrix[row][col] + currPathSum;

        // Store in dp to avoid redundant calls
        dp[row][col] = minPathSum;

        return minPathSum;
    }


    public int minFallingPathSum(int[][] matrix) {
        /*
         * Runtime: 502 ms, faster than 5.26% of Java online submissions for Minimum Falling Path Sum II.
Memory Usage: 55.9 MB, less than 5.26% of Java online submissions for Minimum Falling Path Sum II.
         */

        int minPathSum = Integer.MAX_VALUE, currPathSum;

        int R = matrix.length;
        int C = matrix[0].length;

        // For storing the output of each cell so that we can avoid redundant calls
        int dp[][] = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < C; i++) {
            currPathSum = getMinPathSumHelper(0, i, matrix, dp, R, C);
            if (currPathSum < minPathSum) {
                minPathSum = currPathSum;
            }
        }

        return minPathSum;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
