package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/two-sum/
 * Category: Easy, Must Do, Facebook, Top150
 * Related: 
 * https://leetcode.com/problems/4sum/ Medium
 * https://leetcode.com/problems/two-sum-iii-data-structure-design/ Easy Locked
 * https://leetcode.com/problems/two-sum-less-than-k/ Easy Locked
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/ Medium VImp
 * https://leetcode.com/problems/count-good-meals/ Medium Imp
 * https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/ Easy VVImp
 * https://leetcode.com/problems/number-of-pairs-of-strings-with-concatenation-equal-to-target/ Medium VImp
 * 
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

 

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]
 

Constraints:

2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
 

Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        /*
         * Runtime: 3 ms, faster than 69.15% of Java online submissions for Two Sum.
Memory Usage: 42.6 MB, less than 18.54% of Java online submissions for Two Sum.
         */
        Map<Integer, Integer> lookup = new HashMap<>();
        
        
        int l = nums.length;
        
        int[] result = new int[2];
        for (int i = 0; i < l ; i ++) {
            if (lookup.containsKey(target - nums[i])) {
                result[0] = lookup.get(target - nums[i]);
                result[1] = i;
                break;
            }
            
            lookup.put(nums[i], i);
        }
        return result;
        
    }
    
}
