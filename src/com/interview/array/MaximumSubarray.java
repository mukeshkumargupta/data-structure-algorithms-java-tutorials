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
        int maxSoFarSum = nums[0];
        int N = nums.length;
        int maxTillSum = 0;
        for (int k = 0; k < N; k++) {
            maxTillSum += nums[k];
            if (maxTillSum > maxSoFarSum) {
                maxSoFarSum = maxTillSum;
            }
            if (maxTillSum < 0) {
                maxTillSum = 0;
            }
        }
        return maxSoFarSum;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
