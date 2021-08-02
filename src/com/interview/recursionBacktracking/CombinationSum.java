package com.interview.recursionBacktracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Date 07/10/2017
 * @author Mukesh Kumar Gupta
 *
 * Given an input and total print all combinations with repetitions in this input
 * which sums to given total.
 * e.g
 * input - {2,3,5}
 * total - 10
 *
 * Output
 * [2,2,2,2,2],
 * [2,2,3,3],
 * [2,3,5],
 * [5,5]]
 * Reference
 * https://leetcode.com/problems/combination-sum/
 * Category: Medium, Tricky, Must Do, VVImp
 * Related:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/ Medium
 * https://leetcode.com/problems/combination-sum-ii/ Medium
 * https://leetcode.com/problems/combinations/ Medium
 * https://leetcode.com/problems/combination-sum-iii/ Medium
 * https://leetcode.com/problems/factor-combinations/ Medium
 * https://leetcode.com/problems/combination-sum-iv/ Medium
 */
public class CombinationSum {

    private void combinationSumUtil(int[] candidates, int i, int target, List<Integer> combination, List<List<Integer>> result) {
        if (i == candidates.length) {
            if (target == 0) {
                result.add(new ArrayList<>(combination));
            }
            return;
        }

        if (candidates[i]<=target) {//if pick it
            combination.add(candidates[i]);
            combinationSumUtil(candidates, i, target - candidates[i], combination, result);
            combination.remove(combination.size()-1);
        }
        combinationSumUtil(candidates, i+1, target, combination, result);//Why not else but does not work
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        combinationSumUtil(candidates, 0, target, combination, result);
        return result;
        
    }
}
