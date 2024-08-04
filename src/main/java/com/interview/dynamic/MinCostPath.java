package com.interview.dynamic;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * Category: Medium 
 * Status: Tricky
 */
public class MinCostPath {

    /* A utility function that returns minimum of 3 integers */
    private static int min(int x, int y) 
    { 
        if (x <y ) {
            return x;
        } else {
            return y;
        }
    } 
  
    
    public int minPathSum(int[][] grid) {
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
                dp[i][j] = min(
                               dp[i-1][j], 
                               dp[i][j-1]) + grid[i][j]; 
  
        return dp[R-1][C-1]; 
    }

}
