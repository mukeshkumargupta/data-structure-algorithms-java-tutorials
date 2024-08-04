package com.interview.dynamic;

/*
 * https://leetcode.com/problems/minimum-falling-path-sum/
 * https://www.youtube.com/watch?v=T0h30zOtCmM
 * Category: Medium, Must Do, Similar to MaximumPathSuminthematrix
 * Related: https://leetcode.com/problems/minimum-falling-path-sum-ii/ Hard
 * 
 */
public class MinimumFallingPathSum {
    //this is recursion statin g from 0, 0
//x-directions and y-directions
static int dR[] = { 1, 1, 1 };
static int dC[] = { 0, -1, 1 };


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
    for (int i = 0; i < 3; i++) {
        // Get new cell
        int nextRow = row + dR[i];
        int nextCol = col + dC[i];

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
     * Time Complexity
O(N*M), Where ‘N’ is the number of rows and ‘M’ is the number of columns in the given matrix.


There will be only N*M distinct recursion calls in recursion because we are saving the result so there will be no duplicate calls. So overall time complexity will be O(N*M).

Space Complexity
O(N*M), Where ‘N’ is the number of rows and ‘M’ is the number of columns in the given matrix.


We will be memorizing the result for N*M cells using O(N*M) space.
Runtime: 4 ms, faster than 69.67% of Java online submissions for Minimum Falling Path Sum.
Memory Usage: 48 MB, less than 5.03% of Java online submissions for Minimum Falling Path Sum.
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
