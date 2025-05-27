package com.interview.dynamic.PartAFibonacciSeriesPattern;

import java.util.Arrays;

public class B_ClimbingStairs {
    /*
 * https://leetcode.com/problems/climbing-stairs/submissions/
 * Video: https://www.youtube.com/watch?v=mLfjzJsN8us&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=3
 * Category: Easy, Must Do, Google, Top150, Fundamental
 * Related:
 * https://leetcode.com/problems/min-cost-climbing-stairs/ Easy
 * https://leetcode.com/problems/fibonacci-number/ Easy
 * https://leetcode.com/problems/n-th-tribonacci-number/ Easy
 * https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/ Medium
 * https://leetcode.com/problems/count-number-of-ways-to-place-houses/ Medium
 * https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/ Medium
 * https://leetcode.com/problems/count-ways-to-build-good-strings/ Medium
 * https://leetcode.com/problems/frog-jump-ii/ Medium
 * https://leetcode.com/problems/find-number-of-ways-to-reach-the-k-th-stair/ Hard
 * https://leetcode.com/problems/the-number-of-ways-to-make-the-sum/ Medium Locked
 *
 * Try to solve using iterative
 * You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


Constraints:

1 <= n <= 45
*/
    public static class ClimbingStairs {
        int climbStairsUtil(int n, int[] dp) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
Memory Usage: 35.8 MB, less than 57.87% of Java online submissions for Climbing Stairs.
         */
            if (n == 0 || n == 1) {
                dp[n] = 1;
                return dp[n];
            }

            if (dp[n] != 0) {
                return dp[n];
            }
            dp[n] = climbStairsUtil(n - 1, dp) + climbStairsUtil(n - 2, dp);
            return dp[n];
        }

        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            return climbStairsUtil(n, dp);

        }

        public int climbStairsTabulation(int n){
            int dp[]=new int[n+1];
            Arrays.fill(dp,0);
            dp[0]= 1;
            dp[1]= 1;

            for(int i=2; i<=n; i++){
                dp[i] = dp[i-1]+ dp[i-2];
            }
            return  dp[n];
        }

        public int climbStairsIterativeSpaceOptimized(int n) {

            int prev2 = 1;
            int prev = 1;

            for(int i=2; i<=n; i++){
                int cur_i = prev2+ prev;
                prev2 = prev;
                prev= cur_i;
            }
            return prev;
        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }
    }
}
