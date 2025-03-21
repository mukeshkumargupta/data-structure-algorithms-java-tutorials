package com.interview.binarysearch.PartFBSOn2DArrays;

/*
    https://leetcode.com/problems/find-a-peak-element-ii/description/
    Category: Medium
    Related:
    https://leetcode.com/problems/find-peak-element/ Medium
    https://leetcode.com/problems/find-the-peaks/ Easy
    A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.

    Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length 2 array [i,j].

    You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.

    You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.

    Example 1:

    Input: mat = [[1,4],[3,2]]
    Output: [0,1]
    Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
    Example 2:



    Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
    Output: [1,1]
    Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.


    Constraints:

    m == mat.length
    n == mat[i].length
    1 <= m, n <= 500
    1 <= mat[i][j] <= 105
    No two adjacent cells are equal.
 */
public class A_BS_26_FindPeakElement {
    /*
    🔹 Approach 1: Brute Force (O(N × M))
    🔸 Idea
    Traverse the entire matrix.
    Check each element to see if it is greater than its adjacent elements.
    If yes, return its position.
    🔹 Complexity
    Time Complexity: O(N × M) (Checking every element)
    Space Complexity: O(1)
    🚀 Brute Force works but is too slow for large matrices!
     */

    private static class BruitForce {
        public int[] findPeakGrid(int[][] matrix) {
            int numRows = matrix.length;
            int numCols = matrix[0].length;

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    if (isPeak(matrix, row, col)) {
                        return new int[]{row, col};
                    }
                }
            }
            return new int[]{-1, -1}; // Should never happen
        }

        private boolean isPeak(int[][] matrix, int row, int col) {
            int numRows = matrix.length;
            int numCols = matrix[0].length;

            int value = matrix[row][col];

            // Check Up
            if (row > 0 && matrix[row - 1][col] > value) return false;
            // Check Down
            if (row < numRows - 1 && matrix[row + 1][col] > value) return false;
            // Check Left
            if (col > 0 && matrix[row][col - 1] > value) return false;
            // Check Right
            if (col < numCols - 1 && matrix[row][col + 1] > value) return false;

            return true;
        }
    }

    /*
        🔹 Approach 2: Binary Search on Columns (O(N log M))
        🔸 Idea (Striver’s Approach)
        Perform binary search on columns to find the column with the maximum peak candidate.
        Check adjacent rows to determine whether it is a peak.
        If not, move left or right based on adjacent values.

        🔹 Explanation
        - Perform binary search on columns.
        - In each column, find the row index with the maximum value.
        - Check if it is a peak by comparing it to left and right.
        - If it's not a peak, move left or right accordingly.

        🔹 Complexity
        - Finding max row in a column → O(N)
        - Binary search on columns → O(log M)
        - Total Complexity → O(N log M)
        - Space Complexity → O(1)
        🚀 Much faster than brute force and works well for large matrices!

        🔹 Final Comparison
        | Approach                 | Time Complexity  | Space Complexity | Best for?                     |
        |--------------------------|-----------------|------------------|--------------------------------|
        | Brute Force              | O(N × M)        | O(1)             | Small matrices                 |
        | Binary Search on Columns | O(N log M)      | O(1)             | Large matrices, efficient search |

        🔹 Final Verdict
        ✅ Best Approach: Binary Search on Columns (O(N log M))
        ✅ Most practical solution for large inputs.
    */
    private static class Optimized {
        public int[] findPeakGrid(int[][] matrix) {
            int numRows = matrix.length;
            int numCols = matrix[0].length;
            int left = 0, right = numCols - 1;

            while (left <= right) {
                int midCol = left + (right - left) / 2;
                int maxRow = getMaxRow(matrix, midCol); // Get row index with max value in midCol

                int leftValue = (midCol > 0) ? matrix[maxRow][midCol - 1] : -1;
                int rightValue = (midCol < numCols - 1) ? matrix[maxRow][midCol + 1] : -1;
                int midValue = matrix[maxRow][midCol];

                if (midValue > leftValue && midValue > rightValue) {
                    return new int[]{maxRow, midCol}; // Found the peak
                } else if (midValue < leftValue) {
                    right = midCol - 1; // Move left
                } else {
                    left = midCol + 1; // Move right
                }
            }

            return new int[]{-1, -1}; // Should never reach here
        }

        private int getMaxRow(int[][] matrix, int col) {
            int maxRow = 0;
            for (int row = 1; row < matrix.length; row++) {
                if (matrix[row][col] > matrix[maxRow][col]) {
                    maxRow = row;
                }
            }
            return maxRow;
        }
    }
}
