package com.interview.matrix;

/*
 * https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/
 * Category: Easy, Must Know
 */
public class DetermineWhetherMatrixCanBeObtainedByRotation {
    void swap(int[][] matrix, int i, int start, int end) {
        while (start < end) {
            int temp = matrix[i][start];
            matrix[i][start] = matrix[i][end];
            matrix[i][end] = temp;
            start++;
            end--;
        }
        
    }
    public void rotate(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        for (int i = 0; i < R; i++) {
            for (int j= 0; j < C; j++) {
                if (j > i) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        //Row by row reverse
        for (int i = 0; i <R; i++) {
            swap(matrix, i, 0, C-1);
        }
        
    }
    boolean isEqualMatrix(int[][] mat, int[][] target) {
        //Compare each eleemnt
        int R1 = mat.length;
        int C1 = mat[0].length;
        for (int i = 0; i  < R1; i++) {
            for (int j =0; j < C1; j++) {
                if (mat[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
        
    }
    public boolean findRotation(int[][] mat, int[][] target) {
        int R1 = mat.length;
        int C1 = mat[0].length;
        int R2 = target.length;
        int C2 = target[0].length;
        if (R1 != R2 || C1 != C2) {
            return false;
        }
        
        boolean isEqual = false;
        for (int i = 0 ; i < 4; i++) {
            if (!isEqual) {
                rotate(mat); 
                isEqual = isEqualMatrix(mat, target);
            } 
            if (isEqual) {
               return true; 
            }
        }
        return false; 
    }
}
