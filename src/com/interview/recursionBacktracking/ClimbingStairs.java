package com.interview.recursionBacktracking;

/*
 * https://leetcode.com/problems/climbing-stairs/submissions/
 * Category: Easy, Must Do, Google
 * Related: https://leetcode.com/problems/min-cost-climbing-stairs/ Easy
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
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
