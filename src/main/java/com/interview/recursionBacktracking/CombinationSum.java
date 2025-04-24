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
 * Category: Medium, Tricky, Must Do, VVImp, Facebook, FAANG
 * Related:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/ Medium
 * https://leetcode.com/problems/combination-sum-ii/ Medium
 * https://leetcode.com/problems/combinations/ Medium
 * https://leetcode.com/problems/combination-sum-iii/ Medium
 * https://leetcode.com/problems/factor-combinations/ Medium
 * https://leetcode.com/problems/combination-sum-iv/ Medium
 */
public class CombinationSum {
    /*
     * Leetcode 39: Combination Sum
     *
     * Problem Statement:
     * Given an array of distinct integers `candidates` and a target integer `target`,
     * return a list of all unique combinations of candidates where the chosen numbers sum to `target`.
     * Each number in candidates may be used unlimited times.
     * The same number can appear multiple times in different combinations.
     * The order of combinations does not matter.
     *
     * Approach: Backtracking
     * - We use backtracking to explore all possible combinations.
     * - We maintain an index (`ind`) to ensure elements are picked in non-decreasing order
     *   (to avoid duplicate sets).
     *
     * Key Decisions:
     * - At each step, we can either:
     *   1. Include the current element (if it does not exceed the target).
     *   2. Skip it and move to the next element.
     * - If the target becomes `0`, we store the combination.
     *
     * Explanation:
     * 1. Base Case:
     *    - If `target == 0`, we found a valid combination, so we add it to the result.
     *
     * 2. Iterating Over Candidates:
     *    - Start from `ind` to avoid duplicate sets.
     *    - If `candidates[i] > target`, skip the number (optimization).
     *    - Otherwise:
     *      - Include the element (`current.add(candidates[i])`).
     *      - Make a recursive call with `target - candidates[i]` (allowing the same number).
     *      - Backtrack by removing the last element to explore other choices.
     *
     * Complexity Analysis:
     * - Time Complexity: O(2^N) (Exponential)
     *   - In the worst case, we explore all subsets.
     * - Space Complexity: O(N)
     *   - The recursion depth is at most `N` (length of candidates).
     *
     * Example Walkthrough:
     * Input:
     *   candidates = [2,3,6,7], target = 7
     *
     * Recursive Tree Execution:
     *   (0, 7) → Pick 2 → (0, 5) → Pick 2 → (0, 3) → Pick 2 → (0, 1) (Invalid)
     *                     → Pick 3 → (1, 0) ✅ [2,2,3] Added
     *          → Pick 3 → (1, 4) → Pick 3 → (1, 1) (Invalid)
     *          → Pick 6 → (2, 1) (Invalid)
     *          → Pick 7 → (3, 0) ✅ [7] Added
     *
     * Output:
     *   [[2,2,3], [7]]
     */
    public static class IntutuveApproach {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            backtrack(0, candidates, target, new ArrayList<>(), result);
            return result;
        }

        private void backtrack(int ind, int[] candidates, int target, List<Integer> current, List<List<Integer>> result) {
            // Base case: If target is met, store the current combination
            if (target == 0) {
                result.add(new ArrayList<>(current));
                return;
            }

            for (int i = ind; i < candidates.length; i++) {
                if (candidates[i] > target) continue; // Skip if the number exceeds target, here it could not be break , because may be next element
                // can give target  but part two of it since it is sorted so next element cant give target so it was break in part 2 of it

                // Choose the element
                current.add(candidates[i]);
                backtrack(i, candidates, target - candidates[i], current, result); // Pick the same element
                current.remove(current.size() - 1); // Backtrack to explore other possibilities
            }
        }
    }
    /*ignore below not intutive*/

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
