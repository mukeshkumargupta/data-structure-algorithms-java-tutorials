package com.interview.array;

import java.util.*;
/*
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * Category: Medium, Tricky
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
