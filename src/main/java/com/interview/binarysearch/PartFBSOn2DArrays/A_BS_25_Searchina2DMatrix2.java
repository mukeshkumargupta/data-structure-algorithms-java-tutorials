package com.interview.binarysearch.PartFBSOn2DArrays;

import java.util.ArrayList;
import java.util.Arrays;

public class A_BS_25_Searchina2DMatrix2 {
    private static class BruitForce {
        /*
            Complexity Analysis

            Time Complexity: O(N X M), where N = given row number, M = given column number.
            Reason: In order to traverse the matrix, we are using nested loops running for n and m times respectively.

            Space Complexity: O(1) as we are not using any extra space.
         */
        public static boolean searchElement(ArrayList<ArrayList<Integer>> matrix, int target) {
            int n = matrix.size(), m = matrix.get(0).size();

            // traverse the matrix:
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matrix.get(i).get(j) == target)
                        return true;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            matrix.add(new ArrayList<>(Arrays.asList(1, 4, 7, 11, 15)));
            matrix.add(new ArrayList<>(Arrays.asList(2, 5, 8, 12, 19)));
            matrix.add(new ArrayList<>(Arrays.asList(3, 6, 9, 16, 22)));
            matrix.add(new ArrayList<>(Arrays.asList(10, 13, 14, 17, 24)));
            matrix.add(new ArrayList<>(Arrays.asList(18, 21, 23, 26, 30)));

            boolean result = searchElement(matrix, 8);
            System.out.println(result ? "true" : "false");
        }
    }

    /*
        Complexity Analysis

        Time Complexity: O(N*logM), where N = given row number, M = given column number.
        Reason: We are traversing all rows and it takes O(N) time complexity. And for all rows, we are applying binary search. So, the total time complexity is O(N*logM).

        Space Complexity: O(1) as we are not using any extra space.
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

        public static boolean searchElement(ArrayList<ArrayList<Integer>> matrix, int target) {
            int n = matrix.size();
            int m = matrix.get(0).size();

            for (int i = 0; i < n; i++) {
                boolean flag = binarySearch(matrix.get(i), target);
                if (flag == true) return true;
            }
            return false;
        }


        public static void main(String[] args) {
            ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
            matrix.add(new ArrayList<>(Arrays.asList(1, 4, 7, 11, 15)));
            matrix.add(new ArrayList<>(Arrays.asList(2, 5, 8, 12, 19)));
            matrix.add(new ArrayList<>(Arrays.asList(3, 6, 9, 16, 22)));
            matrix.add(new ArrayList<>(Arrays.asList(10, 13, 14, 17, 24)));
            matrix.add(new ArrayList<>(Arrays.asList(18, 21, 23, 26, 30)));

            boolean result = searchElement(matrix, 8);
            System.out.println(result ? "true" : "false");
        }
    }

    /*
        Approach:
        Start from matrix[0][numCols - 1] (top-right).
        If matrix[row][col] == target, return true.
        If matrix[row][col] > target, move left (decreasing column).
        If matrix[row][col] < target, move down (increasing row).
        Time Complexity:
        O(m + n) (worst case: traverse at most m rows and n columns).
     */
    private static class Optimized {
        public boolean searchMatrix(int[][] matrix, int target) {
            int numRows = matrix.length;
            int numCols = matrix[0].length;

            int row = 0, col = numCols - 1; // Start from top-right

            while (row < numRows && col >= 0) {
                if (matrix[row][col] == target) {
                    return true;
                } else if (matrix[row][col] > target) {
                    col--;  // Move left
                } else {
                    row++;  // Move down
                }
            }

            return false;
        }
    }
}
