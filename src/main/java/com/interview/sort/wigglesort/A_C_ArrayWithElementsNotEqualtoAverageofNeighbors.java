package com.interview.sort.wigglesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem: Array With Elements Not Equal to Average of Neighbors
 * URL: https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/
 * Category: Medium, Tricky
 * Related: https://leetcode.com/problems/wiggle-sort/
 * Video Explanation: https://www.youtube.com/watch?v=Wmb3YdVYfqM
 *
 * Description:
 * You are given a 0-indexed array `nums` of distinct integers. You need to rearrange
 * the elements in the array such that no element is equal to the average of its neighbors.
 *
 * Formally, for every index `i` in the range `1 <= i < nums.length - 1`, the condition:
 *      (nums[i-1] + nums[i+1]) / 2 != nums[i]
 * must hold true.
 *
 * Return any rearrangement of `nums` that satisfies this condition.
 *
 * Constraints:
 * - 3 <= nums.length <= 10^5
 * - 0 <= nums[i] <= 10^5
 *
 * Example 1:
 * Input: nums = [1,2,3,4,5]
 * Output: [1,2,4,5,3]
 * Explanation:
 * - When i = 1, nums[i] = 2, and (1 + 4) / 2 = 2.5 ≠ 2.
 * - When i = 2, nums[i] = 4, and (2 + 5) / 2 = 3.5 ≠ 4.
 * - When i = 3, nums[i] = 5, and (4 + 3) / 2 = 3.5 ≠ 5.
 *
 * Example 2:
 * Input: nums = [6,2,0,9,7]
 * Output: [9,7,6,2,0]
 * Explanation:
 * - When i = 1, nums[i] = 7, and (9 + 6) / 2 = 7.5 ≠ 7.
 * - When i = 2, nums[i] = 6, and (7 + 2) / 2 = 4.5 ≠ 6.
 * - When i = 3, nums[i] = 2, and (6 + 0) / 2 = 3 ≠ 2.
 *
 * Notes:
 * - There are multiple ways to solve this problem, but time complexity is important.
 * - Sorting and rearranging in a specific order can be an optimal approach.
 */
public class A_C_ArrayWithElementsNotEqualtoAverageofNeighbors {
    /*
     * Brute Force Approach: Generate All Permutations
     *
     * Overview:
     * - Since we need to find a valid permutation where no element is equal to the average of its neighbors,
     *   we can generate all possible permutations of nums and check each one.
     *
     * Steps:
     * 1. Generate all permutations of nums using backtracking.
     * 2. Check each permutation to see if it meets the given condition.
     * 3. Return the first valid permutation found.
     *
     * Time Complexity Analysis:
     * - Generating all permutations: O(N!)
     * - Checking each permutation: O(N)
     * - Total Complexity: O(N! × N)
     * - This is not feasible for large values of N (e.g., N = 100,000).
     *
     * Example Run:
     * Input: nums = [1, 2, 3]
     *
     * Generated Permutations:
     * - [1, 2, 3]
     * - [1, 3, 2]  ✅ (Valid)
     * - [2, 1, 3]
     * - [2, 3, 1]  ✅ (Valid)
     * - [3, 1, 2]  ✅ (Valid)
     * - [3, 2, 1]
     *
     * Output (One of the Valid Permutations):
     * - [1, 3, 2]
     *
     * Why Is This Inefficient?
     * - For N = 5, there are 5! = 120 permutations.
     * - For N = 10, there are 10! = 3,628,800 permutations.
     * - For N = 100,000, this is completely infeasible.
     *
     * Conclusion:
     * - The brute force approach is impractical for large inputs.
     * - Instead, we should use the optimal O(N log N) approach.
     */
    private static class BruteForce {
        class Solution {
            public int[] rearrangeArray(int[] nums) {
                List<List<Integer>> permutations = new ArrayList<>();
                boolean[] used = new boolean[nums.length];

                // Generate all permutations
                backtrack(nums, new ArrayList<>(), permutations, used);

                // Check each permutation for validity
                for (List<Integer> perm : permutations) {
                    if (isValid(perm)) {
                        // Convert List to array
                        return perm.stream().mapToInt(Integer::intValue).toArray();
                    }
                }

                return nums; // Default case (should never reach here)
            }

            private void backtrack(int[] nums, List<Integer> current, List<List<Integer>> permutations, boolean[] used) {
                if (current.size() == nums.length) {
                    permutations.add(new ArrayList<>(current));
                    return;
                }
                for (int i = 0; i < nums.length; i++) {
                    if (!used[i]) {
                        used[i] = true;
                        current.add(nums[i]);
                        backtrack(nums, current, permutations, used);
                        current.remove(current.size() - 1);
                        used[i] = false;
                    }
                }
            }

            private boolean isValid(List<Integer> arr) {
                for (int i = 1; i < arr.size() - 1; i++) {
                    if ((arr.get(i - 1) + arr.get(i + 1)) / 2.0 == arr.get(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
    }

    /*
     * Optimal Approach: Sorting & Zig-Zag Merging
     *
     * Key Observations:
     * - The problem is similar to wiggle sort, where we arrange elements such that peaks and valleys alternate.
     * - Sorting the array gives a natural order to separate smaller and larger elements.
     *
     * Steps to Solve:
     * 1. Sort the array.
     * 2. Split the sorted array into two halves:
     *    - The smaller half (low numbers).
     *    - The larger half (high numbers).
     * 3. Interleave these two halves, placing larger numbers in between smaller numbers to avoid averaging issues.
     *
     * Implementation:
     * - Sort the nums array.
     * - Divide it into two halves:
     *   - First half contains smaller numbers.
     *   - Second half contains larger numbers.
     * - Interleave the two halves:
     *   - Pick from the smaller half → Pick from the larger half → Repeat.
     *
     * Time Complexity:
     * - Sorting takes O(N log N).
     * - Merging takes O(N).
     * - Overall Complexity: O(N log N).
     *
     * Example Walkthrough:
     * Input: nums = [1, 2, 3, 4, 5]
     *
     * Sorted: [1, 2, 3, 4, 5]
     *
     * Splitting:
     *  - First half (smaller values): [1, 2, 3]
     *  - Second half (larger values): [4, 5]
     *
     * Merging: [1, 4, 2, 5, 3]
     *
     * This ensures no element is the average of its neighbors.
     *
     * Final Thoughts:
     * Approach:
     * - Brute Force (Generate Permutations): O(N! × N) → Not feasible for large N
     * - Optimal (Sort & Merge in Zig-Zag Order): O(N log N) → Efficient for N ≤ 10⁵
     */
    private static class Optimal {
        //Note: https://leetcode.com/problems/wiggle-sort-ii/ this can also works as its is here
        public int[] rearrangeArray(int[] nums) {
            Arrays.sort(nums);

            int l = nums.length;
            int i = 1; int j = l-1;
            int[] result = new int[l];
            while (i < l ) {
                result[i] = nums[j];
                i +=2;
                j--;
            }
            i = 0;
            while (i < l) {
                result[i] = nums[j];
                i +=2;
                j--;
            }
            return result;
        }
    }
    
    
}
