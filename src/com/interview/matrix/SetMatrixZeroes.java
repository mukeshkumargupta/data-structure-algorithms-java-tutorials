package com.interview.matrix;

/*
 * https://leetcode.com/problems/set-matrix-zeroes/submissions/
 * Category: Medium, Tricky
 * Related:https://leetcode.com/problems/game-of-life/
 * 
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.

You must do it in place.

 

Example 1:


Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]
Example 2:


Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 

Constraints:

m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-231 <= matrix[i][j] <= 231 - 1
 

Follow up:

A straightforward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        boolean status = true;
        for (int i = 0; i < R; i++) {
            if (matrix[i][0] == 0) {
                status = false;
            }
            for (int j = 1; j < C; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
            
        }
        
        for (int i = R - 1; i >= 0; i--) {
            for (int j = C - 1; j >= 1; j--) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (status == false) {
                matrix[i][0] = 0;
            }
        }
    }
    
    public void setZeroes1(int[][] matrix) {// Runtime: 1 ms, faster than 96.08% of Java online submissions for Set
                                            // Matrix Zeroes. Space can be optimized
        int R = matrix.length;
        int C = matrix[0].length;
        int[] Rows = new int[R];
        int[] Cols = new int[C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (matrix[i][j] == 0) {
                    Rows[i] = 1;
                    Cols[j] = 1;
                }
            }
            
        }
        
        for (int i = R - 1; i >= 0; i--) {
            for (int j = C - 1; j >= 0; j--) {
                if (Rows[i] == 1 || Cols[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
