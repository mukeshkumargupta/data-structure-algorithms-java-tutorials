package com.interview.array;

/*
 * https://leetcode.com/problems/single-number
 * Category: Easy, xor
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int length = nums.length;
        int result = nums[0];
        for (int i = 1; i < length; i++) {
            result = result ^ nums[i];
            
        }
        return result;
        
    }
}
