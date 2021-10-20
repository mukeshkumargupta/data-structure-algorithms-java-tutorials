package com.interview.hash;

import java.util.*;

/*
 * https://leetcode.com/problems/contains-duplicate-iii/
 * https://www.youtube.com/watch?v=Cu7g9ovYHNI
 * Category: Medium, Google, Must Do, Sliding window
 * Related:
 * https://leetcode.com/problems/sliding-puzzle/ Hard
 * https://leetcode.com/problems/maximize-distance-to-closest-person/ Medium
 * https://leetcode.com/problems/maximum-number-of-removable-characters/ Medium
 * 
 * Given an integer array nums and two integers k and t, return true if there are two distinct indices i and j in the array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.

 

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 

Constraints:

0 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
0 <= k <= 104
0 <= t <= 231 - 1
Accepted
183,772
Submis
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        /*
         * Runtime: 44 ms, faster than 40.13% of Java online submissions for Contains Duplicate III.
Memory Usage: 43 MB, less than 45.21% of Java online submissions for Contains Duplicate III.
        In tree set insertion deletetion and search is logk (uses balanced bst internally)
         */
        TreeSet<Long> set = new TreeSet<>();
        
        for (int i = 0; i < nums.length; i++) {
            Long floor = set.floor((long)nums[i]);//it return equal or imediate less value found in set if not then return null
            if (floor != null && nums[i] - floor <= t) {
                return true;
            }
            
            Long ceil = set.ceiling((long)nums[i]);//it return equal or imediate greater value found in set if not then return null
            if (ceil != null &&  ceil - nums[i] <= t) {
                return true;
            }
            set.add((long)nums[i]);
            
            if (set.size() > k) {
                set.remove((long)nums[i-k]);
            }
            
        }
        return false;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ContainsDuplicateIII instance = new ContainsDuplicateIII();
        //int[] input = {1,2,3,1};
        int[] input = {1,5,9,1,5,9};
        //[1,2,3,1], k = 3, t = 0
        //[1,5,9,1,5,9], k = 2, t = 3
        instance.containsNearbyAlmostDuplicate(input, 2, 3);
        
    }
    
}
