package com.interview.binarysearch.PartBClassicBinarySearch;

/*
 * Leetcode 704 - Binary Search
 * https://leetcode.com/problems/binary-search/
 * https://www.youtube.com/watch?v=MHf6awe89xw&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=2
 *
 * Problem Statement:
 * You are given a sorted array nums and an integer target. Your task is to return
 * the index of target if it exists in nums; otherwise, return -1.
 *
 * Category: Easy
 * Related: https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/ (Medium)
 * https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/ Easy
 *
 * Example:
 * Input: nums = [-1, 0, 3, 5, 9, 12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4.
 *
 * Runtime: 8.29% (Initial Implementation)
 */
public class A_BinarySearchToFindX {
    /*
     * 1️⃣ Brute Force Approach (Linear Search)
     *
     * Approach:
     * - Iterate through the array sequentially.
     * - If the current element is equal to the target, return its index.
     * - If we reach the end without finding the target, return -1.
     *
     * Time Complexity: O(N) (Iterating through all elements)
     * Space Complexity: O(1) (Only using a few extra variables)
     *
     * Why This is Inefficient?
     * - If nums is very large (e.g., 10^6 elements), this approach will take too long.
     */
    private static class BruitForce {
        public int search(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    return i; // Found target, return index
                }
            }
            return -1; // Target not found
        }
    }

    /*
     * Better Recursive Binary Search
     *
     * Approach:
     * - Instead of using a loop, we can implement binary search recursively.
     * - The base case is when left > right (target not found).
     * - If nums[mid] == target, return mid.
     * - If nums[mid] < target, call the function recursively for the right half.
     * - If nums[mid] > target, call the function recursively for the left half.
     *
     * Time Complexity: O(log N)
     * Space Complexity: O(log N) (Recursive stack space)
     *
     * Pros:
     * ✅ More readable, follows a divide-and-conquer approach.
     *
     * Cons:
     * ⚠️ Uses extra memory due to recursive calls (O(log N) stack space).
     */
    private static class Better {
        public int search(int[] nums, int target) {
            return binarySearch(nums, 0, nums.length - 1, target);
        }

        private int binarySearch(int[] nums, int left, int right, int target) {
            if (left > right) return -1; // Base case

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // Found target
            } else if (nums[mid] < target) {
                return binarySearch(nums, mid + 1, right, target); // Search right half
            } else {
                return binarySearch(nums, left, mid - 1, target); // Search left half
            }
        }
    }

    /*
     * 3⃣ Optimized Approach (Binary Search - Iterative)
     *
     * Approach:
     * - Since nums is sorted, we can eliminate half the elements at each step.
     * - Use two pointers (left and right) to represent the search range.
     * - Find the middle index.
     * - If nums[mid] == target, return mid.
     * - If nums[mid] < target, search in the right half (left = mid + 1).
     * - If nums[mid] > target, search in the left half (right = mid - 1).
     * - Repeat until left > right.
     *
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     *
     * Why This is Better?
     * ✅ Faster: Eliminates half the array at each step.
     * ✅ Efficient: Runs in O(log N) time.
     * ✅ No Extra Space: Uses only a few integer variables.
     *
     * Final Thoughts:
     *
     * | Approach                | Time Complexity | Space Complexity | Pros                     | Cons                             |
     * |-------------------------|----------------|------------------|--------------------------|----------------------------------|
     * | Brute Force (Linear Search) | O(N)           | O(1)             | Simple, easy to implement | Very slow for large arrays       |
     * | Binary Search (Recursive)   | O(log N)       | O(log N)         | Clean and readable        | Extra memory due to recursion    |
     * | Binary Search (Iterative)   | O(log N)       | O(1)             | Fast, memory efficient    | Slightly complex logic           |
     *
     * 🔹 For Small Arrays: Linear Search is fine.
     * 🔹 For Large Arrays: Always prefer Binary Search (Iterative or Recursive).
     * 🔹 If Space is a Concern: Use Iterative Binary Search (no extra stack calls).
     */
    private static class OptimizeApproach {
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2; // Avoids integer overflow

                if (nums[mid] == target) {
                    return mid; // Found the target
                } else if (nums[mid] < target) {
                    left = mid + 1; // Search right half
                } else {
                    right = mid - 1; // Search left half
                }
            }
            return -1; // Target not found
        }
    }
    
}
