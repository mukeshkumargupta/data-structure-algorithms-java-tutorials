package com.interview.array;

/*
 * https://leetcode.com/problems/maximum-sum-circular-subarray/
 * Category: Medium, Must Do, Tricky
 */
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int length = nums.length;
        int maxSum = nums[0];
        int currentMaxSum = 0;
        int minSum = nums[0];
        int currentMinSum = 0;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += nums[i];
            currentMaxSum += nums[i];
            if (currentMaxSum > maxSum) {
                maxSum = currentMaxSum;
            }
            
            if (currentMaxSum < 0) {
                currentMaxSum =0;
            }
            
            currentMinSum += nums[i];
            if (currentMinSum < minSum) {
                minSum = currentMinSum;
            }
            
            if (currentMinSum > 0) {
                currentMinSum =0;
            }
        }
        
        int result = 0;
        if (sum == minSum) {
            result = maxSum;
            
        } else {
            result = Math.max(maxSum, sum - minSum);
        }
        return result; 
    }
}
