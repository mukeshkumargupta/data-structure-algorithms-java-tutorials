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
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
 

Example 1:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104
Accepted
584,031
Submissions
1,417,054
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
