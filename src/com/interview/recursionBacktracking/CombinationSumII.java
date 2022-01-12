package com.interview.recursionBacktracking;

import java.util.*;

/*
 * https://leetcode.com/problems/combination-sum-ii/
 * https://www.youtube.com/watch?v=G1fRTGRxXU8
 * Category: Medium, Tricky, Must Do
 * Derived: This problem is just extension of SubsetsII so try first this
 * Related: https://leetcode.com/problems/maximum-vacation-days/ Hard
 * https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/ Hard
 * https://leetcode.com/problems/minimum-skips-to-arrive-at-meeting-on-time/ Hard
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5
Output: 
[
[1,2,2],
[5]
]


 * 
 */
public class CombinationSumII {
    
    private void combinationSumUtil(int[] candidates, int start, int target, List<Integer> ds, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(ds));
            return;
        }
        
        
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i-1]) {
                continue;
            }
            if (candidates[i] > target ) {
                break;
            }
            ds.add(candidates[i]);
            combinationSumUtil(candidates, i+1, target - candidates[i], ds, result);
            ds.remove(ds.size()-1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {//runtime 98.5
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        combinationSumUtil(candidates, 0, target, ds, result);
        return result;
        
    } 
}
