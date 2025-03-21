package com.interview.binarysearch.PartBLowerBoundUpperBound;


/*
 * Date: 07/31/2017
 * Author: Mukesh Kumar Gupta
 *
 * Problem:
 * https://www.naukri.com/code360/problems/implement-upper-bound_8165383
 *
 * Complexity:
 * - Time Complexity: O(log n) due to binary search.
 * - Space Complexity: O(1) as no additional space is used.
 *
 * Source:
 * - https://www.youtube.com/watch?v=OE7wUUpJw6I&list=PLIA-9QRQ0RqFaV3j0I4FMiPQQkG-jIouX&index=3
 * - Related LeetCode problem link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * Tags: Medium, Must Do, Top150
 */
public class A_BS_2_B_UpperBound {
    /*
    Complexity Analysis

    Time Complexity: O(N), where N = size of the given array.
    Reason: In the worst case, we have to travel the whole array. This is basically the time complexity of the linear search algorithm.

    Space Complexity: O(1) as we are using no extra space.
     */
    private static class BruitForce {


        public static int upperBound(int []arr, int n, int x) {
            for (int i = 0; i < n; i++) {
                if (arr[i] > x) {
                    // upper bound found:
                    return i;
                }
            }
            return n;
        }

        public static void main(String[] args) {
            int[] arr = {3, 5, 8, 15, 19};
            int n = 5, x = 9;
            int ind = upperBound(arr, n, x);
            System.out.println("The lower bound is the index: " + ind);
        }
    }

    /*
        Complexity Analysis

        Time Complexity: O(logN), where N = size of the given array.
        Reason: We are basically using the Binary Search algorithm.

        Space Complexity: O(1) as we are using no extra space.
     */

    private static class Optimized {


        public static int upperBound(int []arr, int n, int x) {
            int low = 0, high = n - 1;
            int ans = n;

            while (low <= high) {
                int mid = (low + high) / 2;
                // maybe an answer
                if (arr[mid] > x) {
                    ans = mid;
                    //look for smaller index on the left
                    high = mid - 1;
                } else {
                    low = mid + 1; // look on the right
                }
            }
            return ans;
        }

        public static void main(String[] args) {
            int[] arr = {3, 5, 8, 15, 19};
            int n = 5, x = 9;
            int ind = upperBound(arr, n, x);
            System.out.println("The lower bound is the index: " + ind);
        }
    }
}

