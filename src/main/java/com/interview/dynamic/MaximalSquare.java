package com.interview.dynamic;

/*
 * https://leetcode.com/problems/maximal-square/
 * Category: Medium, Must Do
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
        int[][] T = new int[R][C];
        //fill first row
        for (int i = 0; i < C; i++) {
            T[0][i] = 0;
        }
        
        //fill first column
        for (int i = 0; i < R; i++) {
            T[i][0] = 0;
        }
        
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                T[i][j] = matrix[i-1][j-1] - '0';
            }
        }
        
        int max = 0;
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (T[i][j] ==1) {
                    T[i][j] = min(min(T[i-1][j-1], T[i-1][j]), T[i][j-1]) +1;
                    if (T[i][j]  > max) {
                        max = T[i][j];
                        
                    }
                } else {
                    T[i][j] = 0;
                }
                 
            }
        }
        return max*max;
        
    }
}
