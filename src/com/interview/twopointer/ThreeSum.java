package com.interview.twopointer;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/3sum/
 * Category: Medium, Tricky, Google
 * Derived: Find all triplet whose sum is given sum, less than su,. greater than sum or zero 
 * Given an array of distinct integers. The task is to count all the triplets such that sum of two elements equals the third element.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len - 2; i++) {
            // If find solution for previous one the ignore current
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            
            int left = i + 1;
            int right = len - 1;
            
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);
                    left++;
                    right--;
                    while (left < len && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (right >= 0 && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
            
        }
        return result;
    }


    

    

}
