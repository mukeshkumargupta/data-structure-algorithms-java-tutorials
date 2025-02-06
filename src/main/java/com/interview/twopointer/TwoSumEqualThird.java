package com.interview.twopointer;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Given an array of distinct integers. The task is to count all the triplets such that
 * the sum of two elements equals the third element.
 *
 * Problem: https://practice.geeksforgeeks.org/problems/count-the-triplets4615/1
 *
 * Category: Must Do
 * Example 1:
 * Input:
 * N = 4
 * arr[] = {1, 5, 3, 2}
 * Output: 2
 * Explanation: There are 2 triplets:
 * 1 + 2 = 3 and 3 + 2 = 5
 *
 * Example 2:
 * Input:
 * N = 3
 * arr[] = {2, 3, 4}
 * Output: 0
 * Explanation: No such triplet exists.
 *
 * Your Task:
 * You don't need to read input or print anything. Your task is to complete the function countTriplet()
 * which takes the array arr[] and N as inputs and returns the triplet count.
 *
 * Expected Time Complexity: O(N^2)
 * Expected Auxiliary Space: O(1)
 *
 * Constraints:
 * 1 ≤ N ≤ 10^3
 * 1 ≤ arr[i] ≤ 10^5
 */
public class TwoSumEqualThird {

    /**
     * Approach 1: Brute Force
     * - Use three nested loops to check all possible triplets.
     * - Time Complexity: O(N^3)
     * - Space Complexity: O(1)
     */
    public int countTripletBruteForce(int[] arr, int n) {
        int count = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (arr[i] + arr[j] == arr[k]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Approach 2: Using Hashing
     * - Use a HashSet for quick lookup of sums.
     * - Steps:
     *   1. Add all elements to a HashSet.
     *   2. For each pair (i, j), check if their sum exists in the HashSet.
     * - Time Complexity: O(N^2)
     * - Space Complexity: O(N)
     */
    public int countTripletBetter(int[] arr, int n) {
        HashSet<Integer> set = new HashSet<>();
        int count = 0;

        // Add all elements to the set for quick lookup
        for (int num : arr) {
            set.add(num);
        }

        // Check for each pair if their sum exists in the set
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = arr[i] + arr[j];
                if (set.contains(sum)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Approach 3: Optimized Two-Pointer Technique
     * - Steps:
     *   1. Sort the array to allow the use of two pointers.
     *   2. Treat arr[i] as the target sum, and use two pointers to find pairs
     *      that sum up to arr[i].
     * - Time Complexity: O(N^2)
     * - Space Complexity: O(1)
     */
    public int countTriplet(int[] arr, int n) {
        Arrays.sort(arr);
        int count = 0;

        // Iterate over the array from the largest element to the smallest
        for (int i = n - 1; i > 1; i--) {
            int left = 0;
            int right = i - 1;

            // Use two pointers to find valid pairs
            while (left < right) {
                if (arr[i] == arr[left] + arr[right]) {
                    count++;
                    left++;
                    right--;
                } else if (arr[i] > arr[left] + arr[right]) {
                    left++; // Increase the sum by moving the left pointer
                } else {
                    right--; // Decrease the sum by moving the right pointer
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        TwoSumEqualThird solution = new TwoSumEqualThird();

        // Example 1
        int[] arr1 = {1, 5, 3, 2};
        System.out.println("Count of triplets: " + solution.countTriplet(arr1, arr1.length)); // Output: 2

        // Example 2
        int[] arr2 = {2, 3, 4};
        System.out.println("Count of triplets: " + solution.countTriplet(arr2, arr2.length)); // Output: 0
    }
}


