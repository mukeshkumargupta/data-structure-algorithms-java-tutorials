package com.interview.matrix;

/*
 * https://leetcode.com/problems/rotate-image/
 * Category: Medium, VImp, Top150
 * Related: https://leetcode.com/problems/total-hamming-distance/ Medium, VVImp
 * https://leetcode.com/problems/sum-of-floored-pairs/ Hard Imp
 * https://leetcode.com/problems/stone-game-iii/ Hard , VVImp, Try all variant
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

 

Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
Example 3:

Input: matrix = [[1]]
Output: [[1]]
Example 4:

Input: matrix = [[1,2],[3,4]]
Output: [[3,1],[4,2]]
 

Constraints:

matrix.length == n
matrix[i].length == n
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
 */
public class RotateImage {
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
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
Memory Usage: 42.7 MB, less than 56.61% of Java online submissions for Rotate Image.
         */
        int R = matrix.length;
        int C = matrix[0].length;
        for (int i = 0; i < R; i++) {
            for (int j= 0; j < C; j++) {
                if (j > i) { //Here j >i to swap upper half with lower half or i < j swap lower to upper half, both condition will work
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
}
