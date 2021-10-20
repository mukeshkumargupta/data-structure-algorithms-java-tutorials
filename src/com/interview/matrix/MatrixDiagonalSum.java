package com.interview.matrix;

/*
 * https://leetcode.com/problems/matrix-diagonal-sum/submissions/
 * Category: Easy
 */
public class MatrixDiagonalSum {
    public int diagonalSum(int[][] mat) {//100 runtime
        int R = mat.length;
        
        int sum = 0;
        for (int i = 0; i < R; i++) {
            sum += mat[i][i];
        }
        int c = 0;
        for (int i = R-1; i >=0; i--) {
            sum += mat[i][c++];
        }
        
        if (R % 2 != 0) {
           sum -=  mat[R/2][R/2];
        }
        return sum;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
