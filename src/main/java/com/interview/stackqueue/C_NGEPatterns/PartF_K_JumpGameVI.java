package com.interview.stackqueue.C_NGEPatterns;

import java.util.*;
/*
 * https://leetcode.com/problems/jump-game-vi/
 * https://www.youtube.com/watch?v=LiEcBMK5PQs
 https://chatgpt.com/c/d1d41ac9-e43d-42c4-b11b-ae0e5f033115 stet by step explanation
 * Category: Medium, Fundamental, Must Do,  monotonic stack/queue problem so it is kept under stack package
 * Related: https://leetcode.com/problems/jump-game-vii/
 * You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

 

Example 1:

Input: nums = [1,-1,-2,4,-7,3], k = 2
Output: 7
Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
Example 2:

Input: nums = [10,-5,-2,4,0,3], k = 3
Output: 17
Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
Example 3:

Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
Output: 0
 

Constraints:

1 <= nums.length, k <= 105
-104 <= nums[i] <= 104
 */
public class PartF_K_JumpGameVI {

    /*
        How it works:
        Starting from index 0, at every index, you try all possible jumps of size 1 to k and recursively compute the maximum score.

        No memoization. Pure recursion.

        Time Complexity:
        Worst case, at every index, you have k choices.

        So total number of recursive calls = k^n (k choices at each of n positions).

        Time = O(k^n) → Exponential time → TLE for larger inputs.

        Example:
        For n = 10 and k = 2, roughly 2^10 = 1024 calls!
        For n = 1000, it's infeasible.

        Space Complexity:
        Maximum recursion depth = n (at most one call per level).

        So Space = O(n) (stack space).
    */
    private static class BruitForce {
        public int maxResult(int[] nums, int k) {
            return maxResultHelper(nums, k, 0);
        }

        private int maxResultHelper(int[] nums, int k, int index) {
            if (index >= nums.length - 1) return nums[nums.length - 1];

            int maxScore = Integer.MIN_VALUE;
            for (int i = 1; i <= k; i++) {
                if (index + i < nums.length) {
                    maxScore = Math.max(maxScore, nums[index] + maxResultHelper(nums, k, index + i));
                }
            }
            return maxScore;
        }
    }

    /*
        How it works:
        Same recursion, but now use a memo[] array to store computed results for each index.

        If memo[index] != Integer.MIN_VALUE, you return stored result instead of recomputing.

        Time Complexity:
        For each index i, you compute once.

        For each computation, you check at most k jumps (1 to k).

        So total = O(n * k).

        Where:
        - n = number of indices
        - k = at each index, up to k options to check

        Space Complexity:
        Memo array memo[] of size n → O(n).

        Recursion stack depth at most n → O(n).

        So total Space = O(n).
    */
    private static class BetterAsMemoization {
        public int maxResult(int[] nums, int k) {
            int n = nums.length;
            int[] memo = new int[n];
            Arrays.fill(memo, Integer.MIN_VALUE);
            return maxResultHelper(nums, k, 0, memo);
        }

        private int maxResultHelper(int[] nums, int k, int index, int[] memo) {
            if (index >= nums.length - 1) return nums[nums.length - 1];

            if (memo[index] != Integer.MIN_VALUE) return memo[index];

            int maxScore = Integer.MIN_VALUE;
            for (int i = 1; i <= k; i++) {
                if (index + i < nums.length) {
                    maxScore = Math.max(maxScore, nums[index] + maxResultHelper(nums, k, index + i, memo));
                }
            }

            memo[index] = maxScore;
            return maxScore;
        }
    }

    /*
        How it works:
        Bottom-up DP.

        dp[i] = the maximum score to reach index i.

        To compute dp[i], you check all dp[j] for previous k steps (where i - j <= k).

        Time Complexity:
        For each i, you look back up to k steps.

        So again O(n * k).

        Where:
        - n = size of array
        - k = for each index, up to k backward checks

        Space Complexity:
        dp[] array of size n → O(n).

        No recursion stack here.

        So total Space = O(n).
    */
    private static class Tabulation {//Still TLE
        public int maxResult(int[] nums, int k) {
            int n = nums.length;
            int[] dp = new int[n];
            dp[0] = nums[0];  // Initialize the base case

            for (int i = 1; i < n; i++) {
                dp[i] = Integer.MIN_VALUE;
                for (int j = 1; j <= k; j++) {
                    if (i - j >= 0) {
                        dp[i] = Math.max(dp[i], dp[i - j] + nums[i]);
                    }
                }
            }

            return dp[n - 1];  // The maximum score to reach the last index
        }
    }

    /*
        How it works:
        Use a Deque to always maintain the maximum value of dp[j] where j is within k steps behind i.

        The deque is kept in decreasing order based on dp values.

        So:

        - Remove elements out of window (j < i - k).
        - Always have the max value at deque front.

        Time Complexity:
        Each element is added and removed at most once from deque.

        Therefore, all deque operations across the whole array are O(n) total.

        Other operations are constant time inside the loop.

        So Time = O(n).

        Space Complexity:
        - dp[] array of size n → O(n).
        - Deque at most holds k elements → at most O(k) → O(n) in worst case.

        So total Space = O(n).

        Summary Table

        | Approach              | Time Complexity | Space Complexity | Comments                                      |
        |------------------------|-----------------|------------------|-----------------------------------------------|
        | Brute Force            | O(k^n)           | O(n)             | Exponential, impractical for large inputs     |
        | Memoization (Better)   | O(n * k)         | O(n)             | Much faster, stores intermediate results      |
        | Tabulation             | O(n * k)         | O(n)             | Bottom-up DP, avoids recursion                |
        | Optimal (Deque)        | O(n)             | O(n)             | Most efficient using deque for sliding max    |

        Quick Intuition:
        - Brute Force = Try all paths → exponential.
        - Memoization = Save results → each index computed once.
        - Tabulation = Iterative DP → similar to memoization but bottom-up.
        - Optimal (Deque) = Sliding window max optimization → achieve O(n).
    */
    private static class Optimal {
        public int maxResult(int[] nums, int k) {
            int n = nums.length;
            int[] dp = new int[n];
            dp[0] = nums[0];  // Initialize the base case

            Deque<Integer> deque = new LinkedList<>();
            deque.add(0);  // Start with the first index

            for (int i = 1; i < n; i++) {//Think how dp works tabulation approach
                // Remove indices that are out of the window of size k
                while (!deque.isEmpty() && deque.peekFirst() < i - k) {//here i -k not i-k +1 because this question slight different here total k jump excluding the current index while in sliding window maximum it talk about including k window
                    deque.pollFirst();
                }

                // The current index's score is the sum of nums[i] and the maximum score within the window
                dp[i] = nums[i] + dp[deque.peekFirst()];

                // Maintain the deque in decreasing order of dp values
                while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {//Tricky different from sliding window max
                    deque.pollLast();
                }

                // Add the current index to the deque
                deque.addLast(i);
            }

            return dp[n - 1];  // The maximum score to reach the last index
        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }
    }
}
