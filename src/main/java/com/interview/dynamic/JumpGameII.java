package com.interview.dynamic;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/jump-game-ii/
 * Category: Medium, VVImp
 * https://www.youtube.com/watch?v=BRnRPLNGWIo&t=338s
 * Category: Medium, Tricky, Must Do
 * Related: https://leetcode.com/problems/unique-morse-code-words/ Easy
 * https://leetcode.com/problems/unique-morse-code-words/ Easy
 * https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/ Medium Imp Solve using memoization or tabulation
 * https://leetcode.com/problems/minimum-moves-to-make-array-complementary/ Imp
 * 
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
 * 
 */
public class JumpGameII {
    /*
        TC: O[N][N]
        SC: O[N][N] + O[N]
     */
    public int jumpUtil(int ind, int jump, int[] nums, int[][] dp) {
        if (ind >= nums.length -1) {
            return jump;
        }

        if (dp[ind][jump] != -1) {
            return dp[ind][jump];
        }

        int mini = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[ind]; i++) {
            mini = Math.min(mini, jumpUtil(ind+i, jump+1, nums, dp));
        }

        return dp[ind][jump] = mini;
    }

    public int jump(int[] nums) {//37 ms, faster than 37.16% of Java online submissions for Jump Game II.
        int l = nums.length;
        int[][] dp = new int[l][l];
        for (int[] elm: dp) {
            Arrays.fill(elm, -1);
        }

        return jumpUtil(0, 0, nums, dp);

    }

    public int jumpTabulation(int[] nums) {//Tried from left

        int l = nums.length;
        int[][] dp = new int[l][l];

        for () {
            for () {

            }
        }

    }

    public int jumpTabulationSlow(int[] nums) {//Tried from left
        /*
         * Runtime: 206 ms, faster than 13.03% of Java online submissions for Jump Game II.
Memory Usage: 42.3 MB, less than 72.99% of Java online submissions for Jump Game II.
         */
        int l = nums.length;
        int[] dp = new int[l];
        dp[0] = 0;

        for (int i = 1; i < l; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + nums[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] +1);
                }
                
            }
            
        }
        return dp[l-1];
        
    }
    public int jumpFast(int[] nums) {//37 ms, faster than 37.16% of Java online submissions for Jump Game II.
        int l = nums.length;
        int[] dp = new int[l];
        dp[l-1] = 0;
        for (int i = l-2; i >= 0; i--) {
            dp[i] = Integer.MAX_VALUE;// [2,3,0,1,4]
        }
        for (int i = l-2; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = i+1; j <= Math.min(l-1, (i + nums[i])); j++) {
                min = Math.min(min, dp[j]);
            }
            if (min != Integer.MAX_VALUE) {
                dp[i] = min +1;
            }
        }
        return dp[0];
        
    }
    
    public static void main(String[] argc) {
        JumpGameII instance = new JumpGameII();

        instance.jump(new int[]{1, 2});
        
    }
}
