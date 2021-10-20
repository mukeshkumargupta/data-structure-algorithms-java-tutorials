package com.interview.hash;

/*
 * https://leetcode.com/problems/contains-duplicate-ii/
 * category: Easy
 * Related: https://leetcode.com/problems/contains-duplicate-iii/ Medium, Hash map solution hint, store index as key
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

 

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true

Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true

Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false

 */
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {//Runtime: 19 ms, faster than 39.14% of Java online submissions for Contains Duplicate II.
        Map<Integer, Integer> lookup = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (lookup.containsKey(nums[i])) {
                if (Math.abs(i - lookup.get(nums[i])) <= k ) {
                    return true;
                }
            } 
            lookup.put(nums[i], i);
            
        }
        return false;
        
    }
}
