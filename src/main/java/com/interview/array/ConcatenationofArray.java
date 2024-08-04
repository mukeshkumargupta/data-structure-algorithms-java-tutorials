package com.interview.array;

/*
 * https://leetcode.com/problems/concatenation-of-array/
 * Category: Easy
 */
public class ConcatenationofArray {
    public int[] getConcatenation(int[] nums) {
        int l =nums.length;
        int doublel = 2*l;
        int[] result = new int[doublel];
        int start = 0;
        for (int i = 0; i < l; i++) {
            result[start] = nums[i];
            result[l+start] = nums[i];
            start++;
        }
        return result;
        
    }
}
