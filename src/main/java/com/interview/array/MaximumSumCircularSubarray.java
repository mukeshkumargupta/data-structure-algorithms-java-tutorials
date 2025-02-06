package com.interview.array;

/*
 * https://leetcode.com/problems/maximum-sum-circular-subarray/
 * Category: Medium, Must Do
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

 

Example 1:

Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3
Example 2:

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
Example 3:

Input: nums = [3,-1,2,-1]
Output: 4
Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
Example 4:

Input: nums = [3,-2,2,-3]
Output: 3
Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
Example 5:

Input: nums = [-2,-3,-1]
Output: -1
Explanation: Subarray [-1] has maximum sum -1
 

Constraints:

n == nums.length
1 <= n <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
Accepted
89,449
Submissions
250,488
 */
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = nums[0];
        int maxSum = nums[0], currentMax = nums[0];
        int minSum = nums[0], currentMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            totalSum += num;

            // Kadane’s Algorithm for Maximum Subarray Sum
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);

            // Kadane’s Algorithm for Minimum Subarray Sum
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
        }

        // If all elements are negative, return maxSum directly
        return (totalSum != minSum) ? Math.max(maxSum, totalSum - minSum) : maxSum;
    }

    public static void main(String[] args) {
        MaximumSumCircularSubarray solver = new MaximumSumCircularSubarray();
        System.out.println(solver.maxSubarraySumCircular(new int[]{1, -2, 3, -2})); // Output: 3
        System.out.println(solver.maxSubarraySumCircular(new int[]{5, -3, 5}));    // Output: 10
        System.out.println(solver.maxSubarraySumCircular(new int[]{3, -1, 2, -1}));// Output: 4
        System.out.println(solver.maxSubarraySumCircular(new int[]{3, -2, 2, -3}));// Output: 3
        System.out.println(solver.maxSubarraySumCircular(new int[]{-2, -3, -1}));  // Output: -1
    }
}
