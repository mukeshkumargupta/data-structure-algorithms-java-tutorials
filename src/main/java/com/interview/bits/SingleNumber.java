package com.interview.bits;

/*
 * https://leetcode.com/problems/single-number
 * Category: Easy, xor, Must Do
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
