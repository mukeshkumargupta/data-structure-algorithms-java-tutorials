package com.interview.binarysearch.PartBLowerBoundUpperBound;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Date: 07/31/2017
 * Author: Mukesh Kumar Gupta
 *
 * Problem:
 * https://www.naukri.com/code360/problems/first-and-last-position-of-an-element-in-sorted-array_1082549
 * LeetCode problem link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Related:
 * https://leetcode.com/problems/first-bad-version/ Easy
 * https://leetcode.com/problems/plates-between-candles/ Medium
 * https://leetcode.com/problems/find-target-indices-after-sorting-array/ Easy it seems lower bound
 *
 * Complexity:
 * - Time Complexity: O(log n) due to binary search.
 * - Space Complexity: O(1) as no additional space is used.
 *
 * Source:
 * - https://www.youtube.com/watch?v=OE7wUUpJw6I&list=PLIA-9QRQ0RqFaV3j0I4FMiPQQkG-jIouX&index=3
 *
 * Tags: Medium, Must Do, Top150
 *
 * Examples:
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3, 4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1, -1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1, -1]
 */
public class A_BS_3_A_FindFirstandLastPositionofElementinSortedArray {

    private static class BruitForce {

        /*
            Complexity Analysis

            Time Complexity: O(N), N = size of the given array
            Reason: We are traversing the entire array.

            Space Complexity: O(1) as we are not using any extra space.
         */
        public static int[] firstAndLastPosition(ArrayList<Integer> arr, int n, int k) {
            int first = -1, last = -1;
            for (int i = 0; i < n; i++) {
                if (arr.get(i) == k) {
                    if (first == -1) first = i;
                    last = i;
                }
            }
            return new int[] {first, last};
        }


        public static void main(String[] args) {
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(new Integer[] {2, 4, 6, 8, 8, 8, 11, 13}));
            int n = 8, k = 8;
            int[] ans = firstAndLastPosition(arr, n, k);
            System.out.println("The first and last positions are: "
                    + ans[0] + " " + ans[1]);
        }
    }

    private static class Approach2UsingUpperLowerBound {

        /*
            Complexity Analysis

            Time Complexity: O(2*logN), where N = size of the given array.
            Reason: We are basically using a lower-bound and upper-bound algorithm.

            Space Complexity: O(1) as we are using no extra space
         */
        public static int upperBound(ArrayList<Integer> arr, int n, int x) {
            int low = 0, high = n - 1;
            int ans = n;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr.get(mid) > x) {
                    ans = mid;
                    // look for smaller index on the left
                    high = mid - 1;
                } else {
                    low = mid + 1; // look on the right
                }
            }
            return ans;
        }

        public static int lowerBound(ArrayList<Integer> arr, int n, int x) {
            int low = 0, high = n - 1;
            int ans = n;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr.get(mid) >= x) {
                    ans = mid;
                    // look for smaller index on the left
                    high = mid - 1;
                } else {
                    low = mid + 1; // look on the right
                }
            }
            return ans;
        }

        public static int[] firstAndLastPosition(ArrayList<Integer> arr, int n, int k) {
            int lb = lowerBound(arr, n, k);
            if (lb == n || arr.get(lb) != k) return new int[] { -1, -1};//Tricky there could be changes u got lowerbound index but not matched just lower so u need to ensure it by checking target not found
            int ub = upperBound(arr, n, k);
            return new int[] {lb, ub - 1};
        }


        public static void main(String[] args) {
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(new Integer[] {2, 4, 6, 8, 8, 8, 11, 13}));
            int n = 8, k = 8;
            int[] ans = firstAndLastPosition(arr, n, k);
            System.out.println("The first and last positions are: "
                    + ans[0] + " " + ans[1]);
        }
    }

    private static class Approach3 {

        /*
            Complexity Analysis

            Time Complexity: O(2*logN), where N = size of the given array.
            Reason: We are basically using the binary search algorithm twice.

            Space Complexity: O(1) as we are using no extra space.
         */
        public static int firstOccurrence(ArrayList<Integer> arr, int n, int k) {
            int low = 0, high = n - 1;
            int first = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr.get(mid) == k) {
                    first = mid;
                    // look for smaller index on the left
                    high = mid - 1;
                } else if (arr.get(mid) < k) {
                    low = mid + 1; // look on the right
                } else {
                    high = mid - 1; // look on the left
                }
            }
            return first;
        }

        public static int lastOccurrence(ArrayList<Integer> arr, int n, int k) {
            int low = 0, high = n - 1;
            int last = -1;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr.get(mid) == k) {
                    last = mid;
                    // look for larger index on the right
                    low = mid + 1;
                } else if (arr.get(mid) < k) {
                    low = mid + 1; // look on the right
                } else {
                    high = mid - 1; // look on the left
                }
            }
            return last;
        }

        public static int[] firstAndLastPosition(ArrayList<Integer> arr, int n, int k) {
            int first = firstOccurrence(arr, n, k);
            if (first == -1) return new int[] { -1, -1};
            int last = lastOccurrence(arr, n, k);
            return new int[] {first, last};
        }


        public static void main(String[] args) {
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(new Integer[] {2, 4, 6, 8, 8, 8, 11, 13}));
            int n = 8, k = 8;
            int[] ans = firstAndLastPosition(arr, n, k);
            System.out.println("The first and last positions are: "
                    + ans[0] + " " + ans[1]);
        }
    }
}
