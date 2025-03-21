package com.interview.binarysearch.PartEUpperBoundSearchSmallestGreaterElement;

/*
 * LeetCode 852 - Peak Index in a Mountain Array
 * 🔗 Problem Link
 *
 * Problem Explanation:
 * ---------------------
 * A mountain array is an array that:
 * 1. Strictly increases to a peak element.
 * 2. Strictly decreases after the peak.
 * 3. The peak is the largest element in the array.
 *
 * Example 1:
 * ----------
 * Input: arr = {0, 2, 5, 3, 1}
 *
 * Visual Representation:
 *     5  <-- Peak
 *    / \
 *   2   3
 *  /     \
 * 0       1
 *
 * Output: 2
 * Explanation: arr[2] = 5 is the peak (larger than neighbors arr[1] = 2 and arr[3] = 3).
 *
 * Example 2:
 * ----------
 * Input: arr = {0, 10, 5, 2}
 *
 * Visual Representation:
 *      10  <-- Peak
 *     /  \
 *    0    5
 *         \
 *          2
 *
 * Output: 1
 * Explanation: arr[1] = 10 is the peak.
 *
 * Key Observations:
 * -----------------
 * - Peak Condition: arr[i] > arr[i-1] and arr[i] > arr[i+1]
 * - Guaranteed Peak: The problem guarantees that a peak always exists, meaning the array is valid.
 */

public class A_PeakIndexinaMountainArray {

    /*
     * Approach 1: Brute Force (Linear Search)
     * --------------------------------------
     * Idea:
     * - Iterate from left to right.
     * - Find the first element that is greater than the next one (arr[i] > arr[i+1]).
     *
     * Time Complexity:
     * - O(N) → Worst case if the peak is at the end.
     *
     * Space Complexity:
     * - O(1) → No extra space used.
     */
    private static class BruteForce {
        public int peakIndexInMountainArray(int[] arr) {
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    return i; // Found the peak
                }
            }
            return -1; // Should never reach here in a valid mountain array
        }
    }

    /*
     * Approach 2: Binary Search (Optimized)
     * --------------------------------------
     * Idea:
     * - Instead of scanning the array linearly, use binary search to efficiently find the peak.
     *
     * Logic:
     * - Find mid = (low + high) / 2.
     * - If arr[mid] < arr[mid + 1], move right (peak is ahead).
     * - Else, move left (peak is at mid or before).
     *
     * Why This Works?
     * - Since arr[mid] < arr[mid + 1], we always move towards the peak.
     * - When low == high, we have found the peak index.
     *
     * Time Complexity:
     * - O(log N) → Binary search reduces search space exponentially.
     *
     * Space Complexity:
     * - O(1) → No extra space used.
     *
     * Comparison of Approaches:
     * ------------------------------------------------------
     * | Approach                 | Time Complexity | Space Complexity | Notes              |
     * |---------------------------|----------------|------------------|--------------------|
     * | Brute Force (Linear)      | O(N)           | O(1)             | Simple but slow   |
     * | Binary Search (Optimized) | O(log N)       | O(1)             | Faster & optimal  |
     * ------------------------------------------------------
     *
     * Final Thoughts:
     * - Brute Force is simple but inefficient for large arrays.
     * - Binary Search is the best approach 🚀.
     * - Always use binary search when searching in a sorted or structured array!
     */
    private static class Optimized {
        public int peakIndexInMountainArray(int[] arr) {
            int low = 0, high = arr.length - 1;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (arr[mid] < arr[mid + 1]) {
                    low = mid + 1; // Peak is ahead
                } else {
                    high = mid; // Peak is at mid or before
                }
            }
            return low; // Peak index
        }
    }
}
