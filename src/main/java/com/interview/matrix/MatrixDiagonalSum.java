package com.interview.matrix;

/*
 * https://leetcode.com/problems/matrix-diagonal-sum/submissions/
 * Category: Easy
 */
public class MatrixDiagonalSum {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;

        // Traverse the primary and secondary diagonals
        for (int i = 0; i < n; i++) {
            sum += mat[i][i]; // Primary diagonal
            sum += mat[i][n - 1 - i]; // Secondary diagonal
        }

        // If matrix size is odd, subtract the middle element (counted twice)
        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }

        return sum;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
