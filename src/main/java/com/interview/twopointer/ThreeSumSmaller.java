package com.interview.twopointer;

import java.util.Arrays;
/**
 * Given an array of n integers nums and a target, find the number of index triplets i, left, right
 * with 0 <= i < left < right < n that satisfy the condition nums[i] + nums[left] + nums[right] < target.
 *
 * https://leetcode.com/problems/3sum-smaller/
 * https://www.youtube.com/watch?v=_A8obVmv6mc 
 * Category: Medium,triplet, Tricky
 * Related: https://leetcode.com/problems/increasing-triplet-subsequence/ Medium
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/ Medium
 * https://leetcode.com/problems/count-good-triplets/ Easy
 * Count three sum greater than target, Need to try
 * 
 */

public class ThreeSumSmaller {
    

    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length -2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] >= target) {
                    right--;
                } else {
                    count += right - left;//Tricky
                    left++;
                }
            }
        }
        return count;
    }
    
}
