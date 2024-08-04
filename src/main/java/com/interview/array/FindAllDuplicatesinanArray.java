package com.interview.array;

import java.util.*;
/*
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * Category: Medium, Tricky
 * Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.

You must write an algorithm that runs in O(n) time and uses only constant extra space.

 

Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [2,3]
Example 2:

Input: nums = [1,1,2]
Output: [1]
Example 3:

Input: nums = [1]
Output: []
 

Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
Each element in nums appears once or twice.
 * Runtime: 11 ms, faster than 40.06% of Java online submissions for Find All Duplicates in an Array.
Memory Usage: 63.1 MB, less than 24.60% of Java online submissions for Find All Duplicates in an Array.
Next challenges:
https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/ Easy
 */
public class FindAllDuplicatesinanArray {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for (int elm : nums) {
            int val = Math.abs(elm);
            if (nums[val-1] > 0) {
                nums[val-1] = nums[val-1]*-1;
            } else {
                result.add(val);
            }
            
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
