package com.interview.array;

/*
 * Reference: https://leetcode.com/problems/maximum-subarray/
 * Category: Easy
 * Kadane algo
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
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
