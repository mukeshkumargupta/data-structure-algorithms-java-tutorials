package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/jump-game-vi/
 * https://www.youtube.com/watch?v=LiEcBMK5PQs
 https://chatgpt.com/c/d1d41ac9-e43d-42c4-b11b-ae0e5f033115 stet by step explanation
 * Category: Medium, Fundamental, Must Do
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
public class JumpGameVI {

    /*
    /*
Approach 1 Bruitforce
 */
    public int maxResult(int[] nums, int k) {
        return maxResultHelper(nums, k, 0);
    }

    private int maxResultHelperBruitforce(int[] nums, int k, int index) {
        if (index >= nums.length - 1) return nums[nums.length - 1];

        int maxScore = Integer.MIN_VALUE;
        for (int i = 1; i <= k; i++) {
            if (index + i < nums.length) {
                maxScore = Math.max(maxScore, nums[index] + maxResultHelper(nums, k, index + i));
            }
        }
        return maxScore;
    }

/*
Approach 2 Enhance bruitforce
 */
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, Integer.MIN_VALUE);
        return maxResultHelperEnhanceBruitforce(nums, k, 0, memo);
    }

    private int maxResultHelperEnhanceBruitforce(int[] nums, int k, int index, int[] memo) {
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

    public int maxResultTabulation(int[] nums, int k) {
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

    public int maxResultOptimal(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];  // Initialize the base case

        Deque<Integer> deque = new LinkedList<>();
        deque.add(0);  // Start with the first index

        for (int i = 1; i < n; i++) {
            // Remove indices that are out of the window of size k
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // The current index's score is the sum of nums[i] and the maximum score within the window
            dp[i] = nums[i] + dp[deque.peekFirst()];

            // Maintain the deque in decreasing order of dp values
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) {
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
