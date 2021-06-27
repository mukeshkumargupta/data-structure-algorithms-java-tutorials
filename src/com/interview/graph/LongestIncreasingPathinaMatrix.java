package com.interview.graph;

/*
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * Reference: https://www.youtube.com/watch?v=cZlHLMxIOMI&t=665s
 * Category: Hard, Tricky
 * Related dfs and dp
 * Video: https://www.youtube.com/watch?v=cZlHLMxIOMI&t=665s
 */
public class LongestIncreasingPathinaMatrix {
    private boolean isSafe(int i , int j, int R, int C) {
        if ( i >= 0 && i < R && j >=0 && j < C) {
            return true;
        }
        return false;
        
        
    }
    
    private int dfs(int[][] matrix, int i , int j, int[][] dp, int R, int C) {
        if (dp[i][j] != 0) {
           return dp[i][j];
        }
        
        int ans = 1;
        int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        for (int dirI = 0; dirI < 4; dirI++) {
            for (int dirJ = 0; dirJ< 2; dirJ++) {
                int currentI = i + dir[dirI][0];
                int currentJ = j + dir[dirI][1];
                if (isSafe(currentI, currentJ, R, C)) {
                    if (matrix[currentI][currentJ] > matrix[i][j]) {
                        int result = 1 + dfs(matrix, currentI, currentJ, dp, R, C);
                        if (result > ans) {
                            ans = result;
                        }
                    }
                    
                }
                
            }
            
        }
        dp[i][j] = ans;
        return dp[i][j];
        
    }
    public int longestIncreasingPath(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        int[][] dp = new int[R][C];
        
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j =0; j < C; j++) {
                int result = dfs(matrix, i, j, dp, R, C);
                if (result > ans) {
                    ans = result; 
                }
                
            }
        }
        return ans;
        
    }
}
