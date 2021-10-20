package com.interview.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date 03/15/2017 
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/spiral-matrix/
 * Related: https://leetcode.com/problems/spiral-matrix-ii/ Medium
 * https://leetcode.com/problems/spiral-matrix-iii/ Medium
Given an m x n matrix, return all elements of the matrix in spiral order.

 

Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralPrinting {

    public List<Integer> spiralOrder(int[][] matrix) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Spiral Matrix.
Memory Usage: 37.3 MB, less than 64.28% of Java online submissions for Spiral Matrix.
         */
        int R = matrix.length;
        int C = matrix[0].length;
        int top = 0;
        int bottom = R -1;
        int left = 0;
        int right = C -1;
        
        int dir = 0; 
        List<Integer> result = new ArrayList<>();
        
        while (top <= bottom && left <= right) {
            if (dir == 0) {
                for (int j = left; j <= right; j++) {
                    result.add(matrix[top][j]);
                }
                dir = 1;
                top += 1; 
            }
            else if (dir == 1) {
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                }
                dir = 2;
                right -= 1; 
            }
            else if (dir == 2) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                dir = 3;
                bottom -= 1; 
            }
            else if (dir == 3) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                dir = 0;
                left += 1; 
            }
            
        }
        return result;
        
    }
    
    public static void main(String args[]){
        SpiralPrinting sp = new SpiralPrinting();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        List<Integer> result = sp.spiralOrder(matrix);
        System.out.print(result);
    }
}
