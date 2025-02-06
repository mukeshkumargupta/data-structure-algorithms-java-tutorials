package com.interview.greedy;

import java.util.Arrays;

public class PartDJumpGamePattern {

    /*
     * Problem: https://leetcode.com/problems/jump-game/
     * Video Explanation: https://www.youtube.com/watch?v=tZAa_jJ3SwQ
     * Related Video: https://www.youtube.com/watch?v=muDPTDrpS28
     * Category: Medium, Tricky, Top150
     * Author: Mukesh Kumar Gupta
     * Date: 07/31/2017
     *
     * Problem Statement:
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to determine if you can reach the last index.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Examples:
     *
     * Example 1:
     * Input: nums = [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     *
     * Example 2:
     * Input: nums = [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, making it impossible to reach the last index.
     *
     * Constraints:
     * - 1 <= nums.length <= 10^4
     * - 0 <= nums[i] <= 10^5
     *
     * Additional Related Problems:
     * - https://leetcode.com/problems/jump-game-ii/ (Medium)
     * - https://leetcode.com/problems/jump-game-iii/ (Medium)
     * - https://leetcode.com/problems/jump-game-vii/ (Medium)
     * - https://leetcode.com/problems/intersection-of-three-sorted-arrays/ (Easy, locked)
     * - https://leetcode.com/problems/reduce-array-size-to-the-half/ (Medium, Very Important)
     * - Priority Queue Hint: To reduce array size to half, use a map to count frequency and prioritize removal by frequency.
     * Example Solution: https://leetcode.com/problems/reduce-array-size-to-the-half/discuss/1731840/Java-easy-Solution-oror-HashMap-oror-PriorityQueue
     * - https://leetcode.com/problems/array-with-elements-not-equal-to-average-of-neighbors/ (Medium)
     *
     * Note: There are multiple versions of the Jump Game problem. Try solving all variations, as each offers valuable problem-solving experience.
     */
    public static class JumpGame {

        public boolean canJump(int[] nums) { // 2 ms, faster than 75.12% of Java online submissions for Jump Game.
            int l = nums.length;
            int reachable = 0;

            for (int i = 0; i < l; i++) {
                if (i > reachable) {
                    return false;
                }

                reachable = Math.max(i + nums[i], reachable);
            }
            return true;


        }

        /*
         * Little Optimized
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Jump Game.
    Memory Usage: 42.3 MB, less than 73.05% of Java online submissions for Jump Game.
         */
        public boolean canJumpOptimized(int[] nums) {
            int reachable = 0;
            int l = nums.length;
            for (int i = 0; i < l; i++) {
                if (i > reachable) {
                    return false;
                }


                reachable = Math.max(i + nums[i], reachable);
                if (reachable >= l - 1) { //early break
                    return true;
                }
            }
            return true;

        }
    }


    /*
    Time Complexity: O(2^n) (exponential), as we explore all paths.
Space Complexity: O(n) (stack space for recursion).
     */
    public static class JumpGameII {
        public int jumpRecursion(int[] nums) {
            return jumpRecursionUtil(nums, 0);
        }

        private int jumpRecursionUtil(int[] nums, int idx) {
            int n = nums.length;
            // Base Case: If we reached the last index
            if (idx >= n - 1) {
                return 0;
            }

            int steps = nums[idx]; // Maximum jump we can take from current index
            int minJumps = Integer.MAX_VALUE;

            // Try all possible jumps and find the minimum jumps to reach the end
            for (int jump = 1; jump <= steps && idx + jump < n; jump++) {
                int jumps = jumpRecursionUtil(nums, idx + jump);
                if (jumps != Integer.MAX_VALUE) { // Only consider reachable paths
                    minJumps = Math.min(minJumps, 1 + jumps);
                }
            }

            return minJumps;
        }
    }

    /*
        Time Complexity: O(n^2) (each index calls every possible jump).
    Space Complexity: O(n) (recursion stack + dp array).
     */
    public int jumpMemoization(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1); // Initialize dp array with -1 (uncomputed states)
        return jumpMemoizationUtil(nums, 0, dp);
    }

    private int jumpMemoizationUtil(int[] nums, int idx, int[] dp) {
        int n = nums.length;
        if (idx >= n - 1) {
            return 0; // No more jumps are needed if we've reached the last index
        }

        if (dp[idx] != -1) {
            return dp[idx]; // Use cached result
        }

        int steps = nums[idx];
        int minJumps = Integer.MAX_VALUE;

        // Try all possible jumps and find the minimum jumps to reach the end
        for (int jump = 1; jump <= steps && idx + jump < n; jump++) {
            int jumps = jumpMemoizationUtil(nums, idx + jump, dp);
            if (jumps != Integer.MAX_VALUE) { // Only consider reachable paths
                minJumps = Math.min(minJumps, 1 + jumps);
            }
        }

        return dp[idx] = minJumps; // Store result and return
    }

    /*
        Time Complexity: O(n^2), as each index checks up to nums[i] future indices.
        Space Complexity: O(n) (for the dp array).
     */
    public int jumpTabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE); // Initialize all elements as unreachable
        dp[n - 1] = 0; // No jumps needed to reach the last index from itself

        // Iterate from the second last index to the first
        for (int i = n - 2; i >= 0; i--) {
            int steps = nums[i]; // Max steps we can take from index i

            // Try all possible jumps from this index
            for (int jump = 1; jump <= steps && i + jump < n; jump++) {
                if (dp[i + jump] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i + jump]);
                }
            }
        }

        return dp[0]; // Minimum jumps to reach the last index from index 0
    }

    /*
    min jump to reach end
    Time Complexity: O(n), as we traverse the array once.
    Space Complexity: O(1), as we only use a few variables for tracking.
    Summary of Approaches:
    Approach	Time Complexity	Space Complexity
    Recursion	O(2^n)	O(n)
    Memoization (Top-Down)	O(n^2)	O(n)
    Tabulation (Bottom-Up)	O(n^2)	O(n)
    Space Optimized (Greedy)	O(n)	O(1)
    Best Approach:
    The Greedy method is the best approach as it runs in O(n) time and uses O(1) space.
     */
    public int jumpGreedy(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0; // No jump needed if we're already at the last index

        int jumps = 0;        // Number of jumps we have taken
        int farthest = 0;     // The farthest point we can reach
        int currentEnd = 0;   // The boundary of the current jump

        for (int i = 0; i < n - 1; i++) {
            // Update the farthest position we can reach from the current position
            farthest = Math.max(farthest, i + nums[i]);

            // If we have reached the end of the current jump
            if (i == currentEnd) {
                jumps++;          // We must take another jump
                currentEnd = farthest; // Move to the farthest point we can reach
            }

            // Early exit if we already can reach the last index
            if (currentEnd >= n - 1) {
                return jumps;
            }
        }

        return jumps; // Return the total number of jumps
    }
}
