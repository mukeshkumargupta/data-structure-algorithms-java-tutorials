package com.interview.recursionBacktracking;

import java.util.*;

/*
 * https://leetcode.com/problems/subsets-ii/
 * https://www.youtube.com/watch?v=RIn3gOkbhQE&t=1526s
 * Category: Must Do, Medium
 * Given an integer array nums that may contain 
, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.
Related: https://leetcode.com/problems/subsets/ Medium
https://leetcode.com/problems/find-array-given-subset-sums/ Hard

 

Example 1:

Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:

Input: nums = [0]
Output: [[],[0]]
 

Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10
 */
public class SubsetsII {
    private void subsets(int[] nums, int start, int end, List<Integer> ds, List<List<Integer>> result) {
        result.add(new ArrayList<>(ds));
        
        for (int i = start; i <= end; i++) {
            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }
            ds.add(nums[i]);
            subsets(nums, i+1, end, ds, result);
            ds.remove(ds.size()-1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsets(nums, 0, nums.length-1, new ArrayList<>(), result);
        return result;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
