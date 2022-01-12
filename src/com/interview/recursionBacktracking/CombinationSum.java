package com.interview.recursionBacktracking;

import java.util.*;

/**
 * Date 07/10/2017
 * @author Mukesh Kumar Gupta
 *
 * Given an input and total print all combinations with repetitions in this input
 * which sums to given total. Note: if ask count then it seems coin change help here but here it is asked all print
 * https://www.youtube.com/watch?v=OyZFFqQtu98&t=1296s
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

    private void combinationSumUtil(int[] candidates, int start, int target, List<Integer> ds, List<List<Integer>> result) {
        if (start == candidates.length) {
            if (target == 0) {
                result.add(new ArrayList<>(ds));
            }
            return;
        }

        if (candidates[start]<=target) {//if pick it
            ds.add(candidates[start]);
            combinationSumUtil(candidates, start, target - candidates[start], ds, result);
            ds.remove(ds.size()-1);
        }
        combinationSumUtil(candidates, start+1, target, ds, result);//Not pick case
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /*
         * Runtime: 2 ms, faster than 98.89% of Java online submissions for Combination Sum.
Memory Usage: 38.9 MB, less than 93.75% of Java online submissions for Combination Sum.
Next challenges:
         */
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> ds = new ArrayList<>();
        combinationSumUtil(candidates, 0, target, ds, result);
        return result;
        
    }
}
