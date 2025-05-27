package com.interview.recursionBacktracking.C_permutationPatterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
https://www.youtube.com/watch?v=vhHrUeSdFGU
Category: Medium, Tricky, Google, FAANG
Related: Medium https://leetcode.com/problems/beautiful-arrangement-ii/ Medium
Suppose you have n integers labeled 1 through n. A permutation of those n integers perm (1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), either of the following is true:

perm[i] is divisible by i.
i is divisible by perm[i].
Given an integer n, return the number of the beautiful arrangements that you can construct.



Example 1:

Input: n = 2
Output: 2
Explanation:
The first beautiful arrangement is [1,2]:
    - perm[1] = 1 is divisible by i = 1
    - perm[2] = 2 is divisible by i = 2
The second beautiful arrangement is [2,1]:
    - perm[1] = 2 is divisible by i = 1
    - i = 2 is divisible by perm[2] = 1
Example 2:

Input: n = 1
Output: 1


Constraints:

1 <= n <= 15
 */
public class PaermutationPattern_BeautifulArrangement {

    /*
    Approach 1: Brute Force (Generating All Permutations)
    Intuition:
    Generate all possible permutations of numbers 1 to n.

    For each permutation, check whether the condition holds for every index i.

    Count valid permutations.

    Time Complexity:
    There are n! permutations, and checking each takes O(n).

    So, the total time complexity is O(n! * n).

    Why is this approach slow?
    It tries all possible permutations (n! possibilities).

    It swaps elements in a list to generate different orderings.

    It does unnecessary calculations for invalid permutations.
     */
    private static class BruitForce {
        int count = 0;

        public int countArrangement(int n) {
            List<Integer> nums = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                nums.add(i);
            }
            permute(nums, 0, n);
            return count;
        }

        private void permute(List<Integer> nums, int index, int n) {
            if (index == n) { // Check the permutation
                count++;
                return;
            }
            for (int i = index; i < n; i++) {
                Collections.swap(nums, index, i);
                if (nums.get(index) % (index + 1) == 0 || (index + 1) % nums.get(index) == 0) {
                    permute(nums, index + 1, n);
                }
                Collections.swap(nums, index, i); // Backtrack
            }
        }
    }

    /*
    Approach 2: Backtracking (Optimized)
    Intuition:
    Instead of generating all permutations, we build valid permutations incrementally.

    We only proceed if the current number at position i satisfies the condition.

    This prunes unnecessary recursive calls, reducing the number of computations.

    Time Complexity:
    Better than O(n!), closer to O(k), where k is the number of valid arrangements (much smaller than n!).

    Why is this approach faster?
    ✅ Prunes invalid states early: It does not generate all permutations, only valid ones.
    ✅ Uses a boolean array (used[]) to avoid unnecessary swaps.
    ✅ Recursion depth is at most n, making it much more efficient.
     */
    private static class Better {
        int count = 0;

        public int countArrangement(int n) {
            boolean[] used = new boolean[n + 1]; // Track used numbers
            backtrack(n, 1, used);
            return count;
        }

        private void backtrack(int n, int pos, boolean[] used) {
            if (pos > n) { // If we've placed all numbers, count this arrangement
                count++;
                return;
            }

            for (int num = 1; num <= n; num++) {
                if (!used[num] && (num % pos == 0 || pos % num == 0)) {
                    used[num] = true; // Mark number as used
                    backtrack(n, pos + 1, used);
                    used[num] = false; // Backtrack
                }
            }
        }
    }

    /*
        Problem: Beautiful Arrangement (LeetCode 526)

        Given an integer `n`, return the number of the beautiful arrangements that can be formed.
        A permutation of numbers `1 to n` is called a beautiful arrangement if for every index `i`:
            - `perm[i] % i == 0` OR `i % perm[i] == 0`

        ------------------------------------------------
        Approach 1: Brute Force (All Permutations)
        ------------------------------------------------
        Intuition:
        - Generate all `n!` permutations of {1,2,...,n}.
        - Check if each permutation satisfies the condition.

        Time Complexity:
        - O(n! * n), where `n!` is the number of permutations and `n` for validation.

        Space Complexity:
        - O(n) for storing the current permutation.

        */
    private static class Optimal {
        int[][] dp;

        public int countArrangement(int n) {
            dp = new int[n + 1][1 << (n + 1)];
            return dfs(n, 1, 0);
        }

        private int dfs(int n, int pos, int mask) {
            if (pos > n) return 1;
            if (dp[pos][mask] != 0) return dp[pos][mask];

            int ways = 0;
            for (int num = 1; num <= n; num++) {
                if ((mask & (1 << num)) == 0 && (num % pos == 0 || pos % num == 0)) {
                    ways += dfs(n, pos + 1, mask | (1 << num));
                }
            }
            return dp[pos][mask] = ways;
        }
    }
}
