package com.interview.array;

/*
 * https://leetcode.com/problems/build-array-from-permutation/
 * Category: Easy
 */
public class BuildArrayfromPermutation {
    public int[] buildArray(int[] nums) {
        int l = nums.length;
        int[] result = new int[l];
        
        for (int i = 0; i < l ; i++) {
            result[i] = nums[nums[i]];
        }
        return result;
        
    }
}
