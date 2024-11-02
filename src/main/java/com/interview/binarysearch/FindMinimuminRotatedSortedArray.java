package com.interview.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * LeetCode Problem: Find Minimum in Rotated Sorted Array
 * URL: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ - Achieved 100% runtime efficiency
 * Video Explanations:
 *   - Without pivot: https://www.youtube.com/watch?v=r3pMQ8-Ad5s
 *   - With pivot: https://www.youtube.com/watch?v=oTfPJKGEHcc
 * Category: Medium, Must Do, Top150
 * Related Problems:
 *   - Search in Rotated Sorted Array: https://leetcode.com/problems/search-in-rotated-sorted-array/
 *   - Find Minimum in Rotated Sorted Array II: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * Problem Summary:
 * Given a sorted array that has been rotated between 1 and n times, find the minimum element.
 * The algorithm should have O(log n) time complexity.
 *
 * Example Rotations:
 *   - Original array: [0,1,2,4,5,6,7]
 *   - Rotated: [4,5,6,7,0,1,2] (rotated 4 times)
 *   - Rotated: [0,1,2,4,5,6,7] (rotated 7 times)
 *
 * Requirements:
 * - n == nums.length, 1 <= n <= 5000
 * - -5000 <= nums[i] <= 5000, all elements are unique
 * - The array is rotated between 1 and n times.
 *
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: Original array was [1,2,3,4,5], rotated 3 times.
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: Original array was [0,1,2,4,5,6,7], rotated 4 times.
 *
 * Example 3:
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: Original array was [11,13,15,17], rotated 4 times.
 *
 * Constraints:
 * - Unique elements in nums
 * - Rotated between 1 and n times
 */
public class FindMinimuminRotatedSortedArray {

    public static int search(ArrayList<Integer> arr, int n, int k) {
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            // if mid points to the target
            if (arr.get(mid) == k)
                return mid;

            // if left part is sorted
            if (arr.get(low) <= arr.get(mid)) {
                if (arr.get(low) <= k && k <= arr.get(mid)) {
                    // element exists
                    high = mid - 1;
                } else {
                    // element does not exist
                    low = mid + 1;
                }
            } else { // if right part is sorted
                if (arr.get(mid) <= k && k <= arr.get(high)) {
                    // element exists
                    low = mid + 1;
                } else {
                    // element does not exist
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(7, 8, 9, 1, 2, 3, 4, 5, 6));
        int n = 9, k = 1;
        int ans = search(arr, n, k);
        if (ans == -1)
            System.out.println("Target is not present.");
        else
            System.out.println("The index is: " + ans);
    }
}
