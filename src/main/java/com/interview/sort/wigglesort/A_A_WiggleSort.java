package com.interview.sort.wigglesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Date: 03/23/2017
 * Author: Mukesh Kumar Gupta
 *
 * Problem: Convert an unsorted array into a wiggle sorted array such that:
 *          nums[0] ≤ nums[1] ≥ nums[2] ≤ nums[3] ≥ nums[4] …
 *
 * Time Complexity: O(n) (Depends on KthElementInArray time complexity)
 * Space Complexity: O(1)
 * Difficulty: Medium, Tricky
 *
 * Problem Understanding:
 * - We need to rearrange the given array nums to satisfy the wiggle pattern.
 *
 * Example:
 * Input:
 * nums = [3, 5, 2, 1, 6, 4]
 *
 * Possible Outputs:
 * [3, 5, 1, 6, 2, 4]
 * [2, 6, 1, 5, 3, 4]
 * (Multiple valid solutions exist)
 *
 * Related LeetCode Problems:
 * - LeetCode 280: Wiggle Sort (https://leetcode.com/problems/wiggle-sort/)
 * - LeetCode 324: Wiggle Sort II (https://leetcode.com/problems/wiggle-sort-ii/)
 *
 * Explanation Video:
 * - https://www.youtube.com/watch?v=eOlp2q08EDU
 */
public class A_A_WiggleSort {
    /*
        Brute Force Approach (Generate All Permutations)

        Idea:
        - Since we need a valid arrangement where no element is misplaced, we can:
          1. Generate all permutations of nums.
          2. Check each permutation to see if it follows the required wiggle pattern.
          3. Return the first valid one.

        Steps:
        1. Use backtracking to generate all possible arrangements of nums.
        2. For each arrangement, check if it satisfies the wiggle pattern.
        3. If a valid permutation is found, return it.

        Time Complexity Analysis:
        - Generating all permutations: O(N!)
        - Checking each permutation: O(N)
        - Overall Complexity: O(N! × N)

        Why is this Inefficient?
        - For N = 5: There are 5! = 120 permutations.
        - For N = 10: There are 10! = 3,628,800 permutations.
        - Not feasible for large N (e.g., N = 100,000).
    */
    private static class WiggleSortBruteForce {
        public void wiggleSort(int[] nums) {
            List<List<Integer>> permutations = new ArrayList<>();
            permute(nums, 0, permutations);

            for (List<Integer> perm : permutations) {
                if (isValidWiggle(perm)) {
                    for (int i = 0; i < nums.length; i++) {
                        nums[i] = perm.get(i);
                    }
                    return;
                }
            }
        }

        private void permute(int[] nums, int start, List<List<Integer>> result) {
            if (start == nums.length) {
                List<Integer> perm = new ArrayList<>();
                for (int num : nums) perm.add(num);
                result.add(perm);
                return;
            }
            for (int i = start; i < nums.length; i++) {
                swap(nums, i, start);
                permute(nums, start + 1, result);
                swap(nums, i, start);
            }
        }

        private boolean isValidWiggle(List<Integer> nums) {
            for (int i = 1; i < nums.size(); i++) {
                if ((i % 2 == 1 && nums.get(i) < nums.get(i - 1)) ||
                        (i % 2 == 0 && nums.get(i) > nums.get(i - 1))) {
                    return false;
                }
            }
            return true;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public static void main(String[] args) {
            WiggleSortBruteForce ws = new WiggleSortBruteForce();
            int[] nums = {3, 5, 2, 1, 6, 4};
            ws.wiggleSort(nums);
            System.out.println(Arrays.toString(nums));
        }
    }

    /*
        Better Approach (Sorting + Swapping)

        Idea:
        - Sort the array.
        - Swap adjacent elements to form the wiggle pattern.

        Steps:
        1. Sort the array (O(N log N)).
        2. Swap every adjacent pair (O(N)).
        3. This ensures that smaller values alternate with larger ones.

        Time Complexity:
        - Sorting: O(N log N)
        - Swapping: O(N)
        - Overall Complexity: O(N log N)

        Example Walkthrough:

        Input:  [3, 5, 2, 1, 6, 4]
        Sorted: [1, 2, 3, 4, 5, 6]
        Swapped: [1, 3, 2, 5, 4, 6]

        - A valid wiggle order is achieved.

        Limitations:
        - Sorting is unnecessary; we can achieve this in O(N) time.
    */
    private static class Better {
        public void wiggleSort(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length - 1; i += 2) {
                swap(nums, i, i + 1);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public static void main(String[] args) {
            Better ws = new Better();
            int[] nums = {3, 5, 2, 1, 6, 4};
            ws.wiggleSort(nums);
            System.out.println(Arrays.toString(nums));
        }
    }

    /*
        Optimal Approach (Single Pass, O(N))

        Idea:
        - Instead of sorting, process elements in one pass and enforce the wiggle property.
        - Odd indices (i % 2 == 1): nums[i] >= nums[i-1]
        - Even indices (i % 2 == 0): nums[i] <= nums[i-1]
        - Swap elements only if necessary.

        Steps:
        1. Iterate through the array.
        2. If an odd index has nums[i] < nums[i-1], swap.
        3. If an even index has nums[i] > nums[i-1], swap.
        4. This ensures the wiggle pattern without sorting.

        Time Complexity:
        - O(N) (single pass)
        - O(1) space (in-place swaps)

        Example Walkthrough:

        Input:  [3, 5, 2, 1, 6, 4]

        Processing:
        Index 1: [3, 5, 2, 1, 6, 4] ✅
        Index 2: [3, 5, 2, 1, 6, 4] ✅
        Index 3: [3, 5, 1, 2, 6, 4] 🔄 (swap)
        Index 4: [3, 5, 1, 2, 6, 4] ✅
        Index 5: [3, 5, 1, 2, 4, 6] 🔄 (swap)

        Output: [3, 5, 1, 2, 4, 6]

        Final Comparison:

        Approach            Time Complexity    Space Complexity    Feasibility
        ----------------------------------------------------------------------
        Brute Force         O(N! × N)         O(N!)               ❌ Not feasible
        Sorting + Swap      O(N log N)        O(1)                ✅ Decent
        One-Pass (Optimal)  O(N)              O(1)                ✅ Best
    */
    private static class Optimal {
        public void wiggleSort(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                if ((i % 2 == 1 && nums[i] < nums[i - 1]) ||
                        (i % 2 == 0 && nums[i] > nums[i - 1])) {
                    swap(nums, i, i - 1);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public static void main(String[] args) {
            Optimal ws = new Optimal();
            int[] nums = {3, 5, 2, 1, 6, 4};
            ws.wiggleSort(nums);
            System.out.println(Arrays.toString(nums));
        }
    }
}
