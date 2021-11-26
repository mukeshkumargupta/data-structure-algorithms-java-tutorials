package com.interview.recursionBacktracking;

/*
 * https://leetcode.com/problems/climbing-stairs/submissions/
 * Category: Easy, Must Do, Google
 * Related: https://leetcode.com/problems/min-cost-climbing-stairs/ Easy
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
public class ClimbingStairs {
    int climbStairsUtil(int n, int[] dp) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Climbing Stairs.
Memory Usage: 35.8 MB, less than 57.87% of Java online submissions for Climbing Stairs.
         */
        if (n == 0 || n ==1) {
            dp[n] = 1;
            return dp[n];
        }
        
        if (dp[n] != 0) {
            return dp[n];
        }
        dp[n] = climbStairsUtil(n -1, dp) + climbStairsUtil(n-2, dp) ; 
        return dp[n];
    }
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return climbStairsUtil(n, dp);
        
    }
    
    public int climbStairsIterative(int n) {
        if (n == 0 || n ==1) {
            return 1;
        }
        int a = 1; int b=1;
        
        for (int i = 2; i <=n; i++) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
