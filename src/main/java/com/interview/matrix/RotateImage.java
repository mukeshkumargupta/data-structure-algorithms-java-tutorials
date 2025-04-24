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
    /*
    ✅ Time and Space Complexity:
    ⏱ Time Complexity: O(n²)
    Transposing the matrix takes O(n²).

    Reversing each row (n rows of length n) also takes O(n²) in total.

    So, total time: O(n²).

    💾 Space Complexity: O(1)
    All operations are done in-place, using a few extra variables.

    No extra space proportional to the input size.
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row using two pointers
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    /*
    ⏱ Time and Space Complexity
    Time Complexity: O(n^2)

    Transpose: O(n^2)

    Reversing columns: O(n^2)

    Space Complexity: O(1)

    In-place swap, no extra space used.
     */
    public void rotateCounterclockwise(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each column (top to bottom using two-pointer approach)
        for (int j = 0; j < n; j++) {
            int top = 0, bottom = n - 1;
            while (top < bottom) {
                int temp = matrix[top][j];
                matrix[top][j] = matrix[bottom][j];
                matrix[bottom][j] = temp;
                top++;
                bottom--;
            }
        }
    }
}
