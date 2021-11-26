package com.interview.matrix;

/*
 * https://leetcode.com/problems/search-a-2d-matrix/
 * https://www.youtube.com/watch?v=dcTJRw1704w
 * Category: Medium, Must Do
 * Related: https://leetcode.com/problems/search-a-2d-matrix-ii/ Medium both 1 and 2 are exactly same
 * Related: https://leetcode.com/problems/longest-repeating-substring/ Medium
 * https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/ Medium
 * https://leetcode.com/problems/number-of-spaces-cleaning-robot-cleaned/ Medium
 * 
 * 
 */
public class Searcha2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix.
        /*Runtime: 4 ms, faster than 100.00% of Java online submissions for Search a 2D Matrix II.
        Memory Usage: 44.4 MB, less than 91.23% of Java online submissions for Search a 2D Matrix II.
        */
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
