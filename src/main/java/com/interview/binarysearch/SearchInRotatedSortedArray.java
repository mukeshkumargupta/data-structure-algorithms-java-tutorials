package com.interview.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 * Video Explanations:
 *   - Without pivot: https://www.youtube.com/watch?v=r3pMQ8-Ad5s
 *   - With pivot: https://www.youtube.com/watch?v=oTfPJKGEHcc
 * Category: Medium, Must Do, Top150, Tricky
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
public class SearchInRotatedSortedArray {

    /*
        Explanation of the Code:
        Initialization:

        Start with left = 0 and right = nums.length - 1.
        Binary Search:

        Calculate the midpoint mid and check if it matches the target.
        Determine which part of the array is sorted:
        If nums[left] <= nums[mid], the left part is sorted.
        Otherwise, the right part is sorted.
        Narrow Down:

        If the left part is sorted, check if the target lies within this range (nums[left] <= target < nums[mid]).
        If the right part is sorted, check if the target lies within this range (nums[mid] < target <= nums[right]).
        Update Pointers:

        Move left or right based on the search space.
        Return Result:

        If the target is found, return its index.
        Otherwise, return -1.
        Dry Run Example:
        Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 0
        Initial: left = 0, right = 6.
        First Iteration:
        mid = 3, nums[mid] = 7.
        nums[mid] > nums[right], so the minimum is in the right half.
        Target 0 is in the range [nums[mid + 1] to nums[right]].
        Update left = mid + 1 = 4.
        Second Iteration:
        mid = 5, nums[mid] = 1.
        nums[mid] > target, so search the left side.
        Update right = mid - 1 = 4.
        Third Iteration:
        mid = 4, nums[mid] = 0, which matches the target.
        Return 4.
        Complexity Analysis:
        Time Complexity:

        The array is divided in half at each iteration, so the time complexity is O(log n).
        Space Complexity:

        Only a few variables (left, right, mid) are used, so the space complexity is O(1).
     */

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the middle element is the target
            if (nums[mid] == target) {
                return mid;
            }

            // Check if the left side is sorted
            if (nums[left] <= nums[mid]) {
                // Target is in the left sorted part
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else { // Otherwise, search in the right part
                    left = mid + 1;
                }
            } else { // Right side is sorted
                // Target is in the right sorted part
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else { // Otherwise, search in the left part
                    right = mid - 1;
                }
            }
        }

        // If we reach here, the target was not found
        return -1;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

        // Test cases
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.search(nums1, 0)); // Output: 4

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.search(nums2, 3)); // Output: -1

        int[] nums3 = {1};
        System.out.println(solution.search(nums3, 1)); // Output: 0
    }
}
