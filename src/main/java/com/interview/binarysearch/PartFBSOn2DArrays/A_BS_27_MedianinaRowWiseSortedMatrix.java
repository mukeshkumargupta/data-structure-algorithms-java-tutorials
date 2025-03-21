package com.interview.binarysearch.PartFBSOn2DArrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class A_BS_27_MedianinaRowWiseSortedMatrix {
    /*
        https://leetcode.com/problems/median-of-a-row-wise-sorted-matrix/description/
        https://www.naukri.com/code360/problems/median-of-a-row-wise-sorted-matrix_1115473
        Category: Medium
        Complexity Analysis

        Time Complexity: O(MXN) + O(MXN(log(MXN))), where M = number of row in the given matrix, N = number of columns in the given matrix

        Reason: At first, we are traversing the matrix to copy the elements. This takes O(MXN) time complexity. Then we are sorting the linear array of size (MXN), that takes O(MXN(log(MXN))) time complexity

        Space Complexity: O(MXN) as we are using a temporary list to store the elements of the matrix.
     */
    private static class BruitForce {
        public static int median(int matrix[][], int m, int n) {
            List<Integer> lst = new ArrayList<>();

            // Traverse the matrix and
            // copy the elements to the list:
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    lst.add(matrix[i][j]);
                }
            }

            // Sort the list:
            Collections.sort(lst);
            return lst.get((m * n) / 2);
        }

        public static void main(String[] args) {
            int matrix[][] = {
                    {1, 2, 3, 4, 5},
                    {8, 9, 11, 12, 13},
                    {21, 23, 25, 27, 29}
            };
            int m = matrix.length, n = matrix[0].length;
            int ans = median(matrix, m, n);
            System.out.println("The median element is: " + ans);
        }
    }

    /*
    Complexity Analysis

    Time Complexity: O(log(109)) X O(M(logN)), where M = number of rows in the given matrix, N = number of columns in the given matrix

    Reason: Our search space lies between [0, 109] as the min(matrix) can be 0 and the max(matrix) can be 109. We are applying binary search in this search space and it takes O(log(109)) time complexity. Then we call countSmallEqual() function for every ‘mid’ and this function takes O(M(logN)) time complexity.

    Space Complexity : O(1) as we are not using any extra space
     */
    private static class Optimal {
        static int upperBound(int[] arr, int x, int n) {
            int low = 0, high = n - 1;
            int ans = n;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr[mid] > x) {
                    ans = mid;
                    // look for a smaller index on the left
                    high = mid - 1;
                } else {
                    low = mid + 1; // look on the right
                }
            }
            return ans;
        }

        static int countSmallEqual(int[][] matrix, int m, int n, int x) {
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                cnt += upperBound(matrix[i], x, n);
            }
            return cnt;
        }

        static int median(int[][] matrix, int m, int n) {
            int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;

            // point low and high to right elements
            for (int i = 0; i < m; i++) {
                low = Math.min(low, matrix[i][0]);
                high = Math.max(high, matrix[i][n - 1]);
            }

            int req = (n * m) / 2;
            while (low <= high) {
                int mid = (low + high) / 2;
                int smallEqual = countSmallEqual(matrix, m, n, mid);
                if (smallEqual <= req) low = mid + 1;
                else high = mid - 1;
            }
            return low;
        }

        public static void main(String[] args) {
            int[][] matrix = {
                    {1, 2, 3, 4, 5},
                    {8, 9, 11, 12, 13},
                    {21, 23, 25, 27, 29}
            };
            int m = matrix.length;
            int n = matrix[0].length;
            int ans = median(matrix, m, n);
            System.out.println("The median element is: " + ans);
        }
    }
}
