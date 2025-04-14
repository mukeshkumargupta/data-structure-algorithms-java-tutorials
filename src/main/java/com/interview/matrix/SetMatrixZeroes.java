package com.interview.matrix;

/*
 * Problem: Set Matrix Zeroes
 * Leetcode Link: https://leetcode.com/problems/set-matrix-zeroes/
 * https://www.youtube.com/watch?v=M65xBewcqcI&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=14
 * Category: Medium, Tricky
 *
 * Related Problems:
 * - Game of Life: https://leetcode.com/problems/game-of-life/
 * - Number of Laser Beams in a Bank (Medium, Very Important): https://leetcode.com/problems/number-of-laser-beams-in-a-bank/
 * - Minimum Operations to Remove Adjacent Ones in Matrix (Hard, Locked): https://leetcode.com/problems/minimum-operations-to-remove-adjacent-ones-in-matrix/
 * - Remove All Ones with Row and Column Flips II (Medium, Locked): https://leetcode.com/problems/remove-all-ones-with-row-and-column-flips-ii/
 *
 * Problem Description:
 * Given an m x n integer matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
 * The operation must be performed in place.
 *
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * Example 2:
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * Constraints:
 * - 1 <= m, n <= 200
 * - -2^31 <= matrix[i][j] <= 2^31 - 1
 *
 * Follow-Up:
 * - A naive solution using O(m*n) extra space is not efficient.
 * - A better approach uses O(m + n) extra space.
 * - Can you solve it using O(1) extra space?
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int R = matrix.length;
        int C = matrix[0].length;
        boolean status = true;
        for (int i = 0; i < R; i++) {
            if (matrix[i][0] == 0) {//Here first column is taken but you can take either of one like first row
                status = false;
            }
            for (int j = 1; j < C; j++) {//Tricky: j start from 1
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
            
        }
        
        for (int i = R - 1; i >= 0; i--) {
            for (int j = C - 1; j >= 1; j--) { //Tricky
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (status == false) {//Just opposite how it was marked see video if not understood
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
