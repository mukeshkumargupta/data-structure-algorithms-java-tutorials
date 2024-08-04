package com.interview.recursionBacktracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Date 01/29/2017
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/permutations/
 * https://www.youtube.com/watch?v=f2ic2Rsc9pU&t=6s
 * Category: Medium, Must Do, Tricky, Top150
 * Related:
 * https://leetcode.com/problems/permutations-ii/ Medium
 * https://leetcode.com/problems/combinations/ Medium
 * 
 * 
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

 

Example 1:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:

Input: nums = [1]
Output: [[1]]
 */
public class Permutation {

    List<List<Integer>> result = new ArrayList<>();
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        
    }
    private void permuteUtil(int[] nums, int start, int end) {
        if (start == end) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(nums, start, i);
            permuteUtil(nums,start+1, end);
            swap(nums, start, i);
        }
        
    }
    public List<List<Integer>> permute(int[] nums) {//100% runtime
        permuteUtil(nums, 0, nums.length-1);
        return result;
    }
}
