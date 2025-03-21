package com.interview.binarysearch.PartFBSOn2DArrays;

import java.util.ArrayList;
import java.util.Arrays;

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
public class A_BS_24_Searcha2DMatrix {
    /*
    🔹 Approach 1: Brute Force (O(N × M))
    🔸 Idea
    Simply traverse the matrix row by row and column by column.
    If we find the target, return true; otherwise, return false.
    🔹 Explanation
    Iterate through every cell of the matrix.
    If matrix[rowIndex][colIndex] == target, return true.
    If no match is found, return false.
    🔹 Complexity
    Time Complexity: O(N × M) (Checking each element)
    Space Complexity: O(1)
     */
    private static class BruitForce {
        public boolean searchMatrix(int[][] matrix, int target) {
            int numRows = matrix.length;
            int numCols = matrix[0].length;

            for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
                for (int colIndex = 0; colIndex < numCols; colIndex++) {
                    if (matrix[rowIndex][colIndex] == target) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


    /*
        🔹 Approach 2: Binary Search Per Row (O(N log M))
        🔸 Idea
        Since each row is sorted, we can apply binary search to find target in each row.
        🔹 Explanation
        Apply binary search in each row separately.
        If the target exists in any row, return true.
        If no match is found, return false.
        🔹 Complexity
        Time Complexity: O(N log M) (Binary search per row)
        Space Complexity: O(1)
     */
    private static class Better {
        public static boolean binarySearch(ArrayList<Integer> nums, int target) {
            int n = nums.size(); //size of the array
            int low = 0, high = n - 1;

            // Perform the steps:
            while (low <= high) {
                int mid = (low + high) / 2;
                if (nums.get(mid) == target) return true;
                else if (target > nums.get(mid)) low = mid + 1;
                else high = mid - 1;
            }
            return false;
        }

        public static boolean searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
            int n = matrix.size();
            int m = matrix.get(0).size();

            for (int i = 0; i < n; i++) {
                if (matrix.get(i).get(0) <= target && target <= matrix.get(i).get(m - 1)) {
                    return binarySearch(matrix.get(i), target);
                }
            }
            return false;
        }


        public static void main(String[] args) {
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            matrix.add(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
            matrix.add(new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
            matrix.add(new ArrayList<>(Arrays.asList(9, 10, 11, 12)));

            boolean result = searchMatrix(matrix, 8);
            System.out.println(result ? "true" : "false");
        }
    }


    private static class Optimized {
        /*
        Time Complexity: O(log(NxM)), where N = given row number, M = given column number.
        Reason: We are applying binary search on the imaginary 1D array of size NxM.

        Space Complexity: O(1) as we are not using any extra space.
         */
        public static boolean searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target) {
            int n = matrix.size();
            int m = matrix.get(0).size();

            //apply binary search:
            int low = 0, high = n * m - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                int row = mid / m, col = mid % m;
                if (matrix.get(row).get(col) == target) return true;
                else if (matrix.get(row).get(col) < target) low = mid + 1;
                else high = mid - 1;
            }
            return false;
        }


        public static void main(String[] args) {
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            matrix.add(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
            matrix.add(new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
            matrix.add(new ArrayList<>(Arrays.asList(9, 10, 11, 12)));

            boolean result = searchMatrix(matrix, 8);
            System.out.println(result ? "true" : "false");
        }
    }
}
