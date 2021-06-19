package com.interview.array;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/3sum/
 * Category: Medium, Tricky
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
            
            int j = i + 1;
            int k = len - 1;
            
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    result.add(temp);
                    j++;
                    k--;
                    while (j < len && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (k >= 0 && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                } else {
                    k--;
                }
            }
            
        }
        return result;
    }
}
