package com.interview.array;

/*
 * Reference: https://leetcode.com/problems/maximum-subarray/
 * Category: Easy, Top150
 * Kadane algo
 * Related: https://leetcode.com/problems/degree-of-an-array/ Easy Imp
 * https://leetcode.com/problems/longest-turbulent-subarray/ Medium VImp
 * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/ Medium VVImp
 * https://leetcode.com/problems/maximum-subarray-sum-after-one-operation/ Medium, Locked
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        /*
         * TC: O(N)
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Maximum Subarray.
Memory Usage: 50.5 MB, less than 99.98% of Java online submissions for Maximum Subarray.
         */
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = maxSubArray(nums);
        System.out.println("Maximum subarray sum is: " + maxSum);
    }
    
}
