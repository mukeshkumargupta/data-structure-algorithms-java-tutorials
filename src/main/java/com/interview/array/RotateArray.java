package com.interview.array;

/*
 * Reference: https://leetcode.com/problems/rotate-array/
 * http://naukri.com/code360/problems/left-rotate-an-array-by-one_5026278
 * https://www.youtube.com/watch?v=wvcQg43_V8U&list=PLgUwDviBIf0rENwdL0nEH0uGom9no0nyB&index=2
 * Category: Medium
 * Given an array, rotate the array to the right by k steps, where k is non-negative.

 

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 */
public class RotateArray {
    /*
    The problem "Rotate Array" on LeetCode requires rotating an array of integers to the right by a given number of steps, k. Here’s a breakdown of different solution approaches from brute force to optimal:

Problem
Given an array nums of integers and an integer k, rotate the array to the right by k steps.

Example:
Input: nums = [1, 2, 3, 4, 5, 6, 7], k = 3
Output: [5, 6, 7, 1, 2, 3, 4]
1. Brute Force Solution
Idea: Shift elements one step to the right k times.
Complexity: Time complexity is
𝑂
(
𝑛
×
𝑘
)
O(n×k), and space complexity is
𝑂
(
1
)
O(1) as it modifies the array in place.


     */

    public void rotateBruteForce(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        for (int i = 0; i < k; i++) {
            int last = nums[n - 1];
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }

    /*
    2. Intermediate Solution: Using Extra Array
Idea: Create a new array where each element is placed at its new position, then copy back.
Complexity:
𝑂
(
𝑛
)
O(n) for both time and space since we use an auxiliary array.


     */

    public void rotateWithExtraArray(int[] nums, int k) {
        int n = nums.length;
        int[] rotatedArray = new int[n];
        k %= n;

        for (int i = 0; i < n; i++) {
            rotatedArray[(i + k) % n] = nums[i];
        }
        System.arraycopy(rotatedArray, 0, nums, 0, n);
    }

    /*
        3. Optimized In-Place Rotation (O(N) time, O(1) space)
        Step 1: Reverse the entire array.
        Step 2: Reverse the first k elements.
        Step 3: Reverse the remaining n - k elements.
        ✅ Efficient O(N) time
        ✅ No extra space O(1)
        ✅ Best for large inputs
     */

    public void rotateOptimal(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        reverse(nums, 0, n - 1);      // Reverse the entire array
        reverse(nums, 0, k - 1);      // Reverse first k elements
        reverse(nums, k, n - 1);      // Reverse the rest

    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
