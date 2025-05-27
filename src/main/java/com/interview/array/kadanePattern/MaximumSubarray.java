package com.interview.array.kadanePattern;

/*
 * Reference: https://leetcode.com/problems/maximum-subarray/
 * Category: Medium, Top150
 * Kadane algo
 * Derived question, tell me start and end index, for subsequesnce so second code on ewill be halpful
 * Related: https://leetcode.com/problems/degree-of-an-array/ Easy Imp
 * https://leetcode.com/problems/longest-turbulent-subarray/ Medium VImp
 * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/ Medium VVImp
 * https://leetcode.com/problems/maximum-subarray-sum-after-one-operation/ Medium, Locked
 * https://leetcode.com/problems/longest-alternating-subarray/ Easy
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        /*
         * TC: O(N)
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Subarray.
Memory Usage: 50.5 MB, less than 99.98% of Java online submissions for Maximum Subarray.
         */
        int maxSoFar = nums[0];
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            maxSoFar = Math.max(maxSoFar, currentMax);
        }

        return maxSoFar;
    }

    public int[] maxSubArrayWithStartEndIndex(int[] nums) {
        /*
         * TC: O(N)
         * Runtime: 1 ms, faster than 100.00% of Java submissions for Maximum Subarray.
         * Memory Usage: O(1)
         */
        int maxSoFar = nums[0];
        int currentMax = nums[0];
        int startIndex = 0, endIndex = 0, tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > currentMax + nums[i]) {
                currentMax = nums[i];
                tempStart = i; // Start a new subarray
            } else {
                currentMax += nums[i];
            }

            if (currentMax > maxSoFar) {
                maxSoFar = currentMax;
                startIndex = tempStart; // Update final start index
                endIndex = i;           // Update end index
            }
        }

        return new int[]{maxSoFar, startIndex, endIndex};
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaximumSubarray sol = new MaximumSubarray();
        int maxSum = sol.maxSubArray(nums);
        System.out.println("Maximum subarray sum is: " + maxSum);
    }
    
}
