package com.interview.bits;
/*
 * Reference: https://leetcode.com/problems/missing-number/
 * Category: Easy
 */

public class MissingNumbers {
    public int missingNumber(int[] nums) {
        int result = 0;
        int l = nums.length;
        for (int i = 0 ; i < l ; i++) {
            result ^= nums[i];
        }
        for (int i = 0 ; i <= l ; i++) {
            result ^= i;
        }
        return result;
        
    }

}
