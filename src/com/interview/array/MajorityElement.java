package com.interview.array;

/*
 * https://leetcode.com/problems/majority-element/
 * Category: Medium, Tricky
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int n = nums.length;
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
            }
            //System.out.println(count);
            if (count < 0) {
                candidate =  nums[i];
                count = 1;
            } 
        }
        return candidate;

    }
}
