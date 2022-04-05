package com.interview.dynamic;

/*
 * https://leetcode.com/problems/unique-paths-ii/
 * https://www.youtube.com/watch?v=TmhpgXScLyY&t=196s
 * Category: Medium, next version of unique path https://leetcode.com/problems/unique-paths/
 * Related: https://leetcode.com/problems/unique-paths-iii/ Hard
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and space is marked as 1 and 0 respectively in the grid.

 

Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
 

Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
 */
public class UniquePathsII {
    private int uniquePathsWithObstaclesUtil(int i, int j, int[][] obstacleGrid, int[][] dp) {
        /*Runtime: 7 ms, faster than 6.83% of Java online submissions for Unique Paths II.
        Memory Usage: 40 MB, less than 6.73% of Java online submissions for Unique Paths II.
        */
        if (obstacleGrid[i][j] ==1) {
            return 0;
        } else if (i == 0 && j == 0) {
            return 1;
        }
        
        if (i < 0 || j < 0) {
            return 0;
        }
                 
         if (dp[i][j] != 0) {
            return dp[i][j];
         }
                 
                 
        int up = 0;
        int left = 0;
        if (i > 0) {
           up =  uniquePathsWithObstaclesUtil(i-1, j, obstacleGrid, dp);
        }
        if (j > 0) {
           left =  uniquePathsWithObstaclesUtil(i, j-1, obstacleGrid, dp);
        }
        dp[i][j] = up + left;

        return dp[i][j];
        
    }
    public int uniquePathsWithObstaclesRecursionPlusMemoization(int[][] obstacleGrid) {
        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;
        int[][] dp = new int[R][C];
        return uniquePathsWithObstaclesUtil(R-1, C-1, obstacleGrid, dp);
        
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths II.
Memory Usage: 40.2 MB, less than 6.73% of Java online submissions for Unique Paths II.
         */
        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;
        int[][] dp = new int[R][C];

        for(int i=0; i < R; i++){
            for(int j=0; j < C; j++){
                if (obstacleGrid[i][j] ==1) {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int up = 0;
                    int left = 0;
                    if (i > 0) {
                       up =  dp[i-1][j];
                    }
                    if (j > 0) {
                       left =  dp[i][j-1];
                    }
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[R-1][C-1];
        
    }
    
    public int uniquePathsWithObstaclesSpaceOptimization(int[][] obstacleGrid) {
        /*
         * Runtime: 1 ms, faster than 48.03% of Java online submissions for Unique Paths II.
Memory Usage: 42.6 MB, less than 6.73% of Java online submissions for Unique Paths II.
         */
        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;
        
        int[] prev = null;
        for(int i=0; i < R; i++){
            int[] current = new int[C];
            for(int j=0; j < C; j++){
                if (obstacleGrid[i][j] ==1) {
                    current[j] = 0;
                } else if (i == 0 && j == 0) {
                    current[j] = 1;
                } else {
                    int up = 0;
                    int left = 0;
                    if (i > 0) {
                       up =  prev[j];
                    }
                    if (j > 0) {
                       left = current[j-1];
                    }
                    current[j] = up + left;
                }
            }
            prev = current;
        }
        return prev[C-1];
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
