package com.interview.recursionBacktracking;

import java.util.*;
/*
 * https://leetcode.com/problems/subsets/
 * Category: Medium, Must Do, Top150
 * Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
Related:
https://leetcode.com/problems/generalized-abbreviation/ Medium Locked
https://leetcode.com/problems/letter-case-permutation/ Medium VVImp
https://leetcode.com/problems/find-array-given-subset-sums/ VImp
https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/ VImp

 

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.
 */
public class Subsets {
    private void subsets(int[] nums, int start, int end, List<Integer> ds, List<List<Integer>> result) {
        result.add(new ArrayList<>(ds));
        
        for (int i = start; i <= end; i++) {
            ds.add(nums[i]);
            subsets(nums, i+1, end, ds, result);
            ds.remove(ds.size()-1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsets(nums, 0, nums.length-1, new ArrayList<>(), result);
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
