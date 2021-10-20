package com.interview.matrix;

/*
 * https://leetcode.com/problems/search-a-2d-matrix/
 * Category: Medium, Must Do
 * Related: https://leetcode.com/problems/search-a-2d-matrix-ii/ Medium
 * 
 * 
 */
public class Searcha2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
        int R = matrix.length;
        int C = matrix[0].length;
        int i = 0;
        int j = C-1;
        boolean found = false;
        while (i < R && j >= 0) {
            if (matrix[i][j] == target) {
                found = true;
            }
            
            if (matrix[i][j] < target) {
               i++; 
            } else {
                j--;
            }
        }
        return found;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
