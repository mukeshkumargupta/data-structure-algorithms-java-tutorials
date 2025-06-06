package com.interview.hash.E_ContainsDuplicatePatterns;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/contains-duplicate/
 * Category: Easy
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

 

Example 1:

Input: nums = [1,2,3,1]
Output: true
Example 2:

Input: nums = [1,2,3,4]
Output: false
Example 3:

Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true
 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Boolean> lookUp = new HashMap<>();
        for (int num : nums) {
            if (lookUp.containsKey(num)) {
                return true;
                
            } else {
                lookUp.put(num, true);
            }
            
        }
        return false;
            
        
    }
}
