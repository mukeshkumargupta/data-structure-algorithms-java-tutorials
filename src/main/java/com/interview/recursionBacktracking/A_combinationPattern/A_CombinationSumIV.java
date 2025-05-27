package com.interview.recursionBacktracking.A_combinationPattern;

import java.util.Arrays;

/*
    🔹 Category:
    - Medium, Facebook, FAANG

    🔗 Related Links:
    - https://totheinnovation.com/facebook-leetcode-questions/
    - https://leetcode.com/problems/combination-sum-iv/description/

    Related:
    https://leetcode.com/problems/combination-sum/ Medium
    https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/ Medium

    📌 Problem Statement:
    Given an array of distinct integers `nums` and a target integer `target`,
    return the number of possible combinations that add up to `target`.

    🔹 Constraints:
    - The test cases are generated so that the answer can fit in a 32-bit integer.

    📝 Example 1:
    Input: nums = [1,2,3], target = 4
    Output: 7

    📌 Explanation:
    The possible combination ways are:
    (1, 1, 1, 1)
    (1, 1, 2)
    (1, 2, 1)
    (1, 3)
    (2, 1, 1)
    (2, 2)
    (3, 1)
    Note: Different sequences are counted as different combinations.

    📝 Example 2:
    Input: nums = [9], target = 3
    Output: 0

    🔹 Constraints:
    - 1 <= nums.length <= 200
    - 1 <= nums[i] <= 1000
    - All elements of `nums` are unique.
    - 1 <= target <= 1000

    🔍 Follow-up:
    - What if negative numbers are allowed in the given array?
    - How does it change the problem?
    - What limitation do we need to add to allow negative numbers?
*/
public class A_CombinationSumIV {

    /*
        🔸 Brute Force: Recursion (Exponential)

        📌 Idea:
        - Try every possible combination recursively.
        - Subtract the current number from the target and call the function recursively.
        - If target == 0, we found a valid combination.

        🔍 Dry Run for nums = [1, 2, 3], target = 4:
        comb(4) = comb(3) + comb(2) + comb(1)
        comb(3) = comb(2) + comb(1) + comb(0)
        comb(2) = comb(1) + comb(0)
        comb(1) = comb(0)

        ⏳ Time Complexity:
        - Exponential O(3^target) (Worst case)

        🛑 Space Complexity:
        - O(target) (Recursion stack)

        🚫 Too slow! Overlapping subproblems lead to redundant calculations.
    */
    private static class BruitForce {
        public int combinationSum4(int[] nums, int target) {
            if (target == 0) return 1; // Found a valid combination

            int count = 0;
            for (int num : nums) {//since again u can select from begining so it is starting from first index, in previous version either u can select uniquique or u can select same elemnt multiple times
                if (target >= num) {
                    count += combinationSum4(nums, target - num);
                }
            }
            return count;
        }
    }

    /*
        🔹 Better Approach: Memoization (Top-Down DP)

        Since we recompute the same subproblems, we cache results in a memoization table.

        🔍 Optimizations:
        ✔ Avoids redundant computations → O(target * N) Time Complexity
        ✔ Reduces recursive depth with memoization → O(target) Space Complexity
    */
    private static class Better {
        public int combinationSum4(int[] nums, int target) {
            int[] memo = new int[target + 1];
            Arrays.fill(memo, -1);
            return helper(nums, target, memo);
        }

        private int helper(int[] nums, int target, int[] memo) {
            if (target == 0) return 1; // Base case: Found a valid way
            if (memo[target] != -1) return memo[target]; // Already computed

            int count = 0;
            for (int num : nums) {
                if (target >= num) {
                    count += helper(nums, target - num, memo);
                }
            }

            return memo[target] = count; // Store result in cache
        }
    }

    /*
        🔹 Optimized Approach: Bottom-Up DP (O(N * target))
        Instead of recursion, we use bottom-up dynamic programming.

        🔹 Key Idea:
        - Define dp[i] → number of ways to make sum i.
        - Transition formula:
            dp[i] += dp[i - num] for each num ∈ nums
        - Base case: dp[0] = 1 (1 way to make sum 0).

        🔍 Dry Run for nums = [1, 2, 3], target = 4

        dp[0] = 1

        dp[1] = dp[0] (1)  → [1]
        dp[2] = dp[1] (1) + dp[0] (1)  → [1+1, 2]
        dp[3] = dp[2] (2) + dp[1] (1) + dp[0] (1)  → [1+1+1, 1+2, 2+1, 3]
        dp[4] = dp[3] (4) + dp[2] (2) + dp[1] (1)  → [1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 2+2, 3+1]

        Final dp = [1, 1, 2, 4, 7]
        ✅ Result: 7

        🔹 Time & Space Complexity Comparison:

        | Approach                   | Time Complexity  | Space Complexity | Efficiency  |
        |----------------------------|-----------------|------------------|-------------|
        | Brute Force (Recursion)     | O(3^target)     | O(target)        | 🚫 Too slow |
        | Memoization (Top-Down DP)   | O(target * N)   | O(target)        | ✅ Better   |
        | Bottom-Up DP (Tabulation)   | O(target * N)   | O(target)        | 🚀 Best     |
    */
    private static class Optimal {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            dp[0] = 1; // 1 way to make target = 0

            for (int i = 1; i <= target; i++) {
                for (int num : nums) {
                    if (i >= num) {
                        dp[i] += dp[i - num];
                    }
                }
            }

            return dp[target];
        }
    }
}
