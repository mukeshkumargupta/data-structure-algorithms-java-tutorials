package com.interview.dynamic;

/*
 * https://leetcode.com/problems/maximal-square/
 * Category: Medium, Must Do, Facebook, FAANG
 * Related:
 * https://leetcode.com/problems/maximal-rectangle/ Hard
 * https://leetcode.com/problems/largest-plus-sign/ Medium
 *
 * TC:
 * 
 */
public class MaximalSquare {
    int min(int a, int b) {
        if (a > b) {
            return b;
        } else {
            return a;
        }
        
    }
    public int maximalSquare(char[][] matrix) {//Runtime: 2 ms, faster than 99.88% of Java online submissions for Maximal Square.
        int R = matrix.length+1;
        int C = matrix[0].length +1;
        int[][] dp = new int[R][C];
        //fill first row
        for (int i = 0; i < C; i++) {
            dp[0][i] = 0;
        }
        
        //fill first column
        for (int i = 0; i < R; i++) {
            dp[i][0] = 0;
        }
        
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                dp[i][j] = matrix[i-1][j-1] - '0';
            }
        }
        
        int max = 0;
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (dp[i][j] ==1) {
                    dp[i][j] = min(min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) +1;
                    if (dp[i][j]  > max) {
                        max = dp[i][j];
                        
                    }
                }
                 
            }
        }
        return max*max;
        
    }
}
