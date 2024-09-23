package com.interview.dynamic;

import java.util.Arrays;

/**
 * Date 03/28/2017
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/fibonacci-number/
 * Video: https://www.youtube.com/watch?v=tyB0ztf0DNY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=2
 * Category: Easy
 * Related: https://leetcode.com/problems/split-array-into-fibonacci-sequence/ Medium
 * https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/ Medium
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).

 

Example 1:

Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
Example 2:

Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
Example 3:

Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 

Constraints:

0 <= n <= 30
 * Fibonacci series
 * Given a number find the fibonacci series value for that number
 * e.g n = 3 -> 3
 *     n = 4 -> 5
 *     n = 5 -> 8
 *  
 * Solution
 * Recursively it can calculated very easily by f(n) = f(n-1) + f(n-2)
 * For Dp version we do not recalculate f(n-1) and f(n-2) but keep it in a and b
 * 
 * Test cases
 * 1) Negative number
 * 2) 0 
 * 3) 1
 * 4) Very high number
 * Category: Easy, Must do, Do own way of dp
 * 
 */
public class PartAFibonacciSeries {
    
   
    /**
     * Recursive and slow version. Recalculates same value over and over again.
     * Chokes for n greater than 60
     */
    public int fibonacciSeriesRecursive(int n){//Without memoization
        if(n == 1 || n == 0){
            return n;
        }
        return fibonacciSeriesRecursive(n-1) + fibonacciSeriesRecursive(n-2);
    }
    
    /**
     * Recursive and slow version. Recalculates same value over and over again.
     * Chokes for n greater than 60
     */
    public int fibonacciSeriesRecursiveWithMemoization(int n, int[] dp){//Use memoization
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
Memory Usage: 41.1 MB, less than 5.17% of Java online submissions for Fibonacci Number.
         */
        if(n == 1 || n == 0){
            dp[n] = n;
            return n;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] =  fibonacciSeriesRecursiveWithMemoization(n-1, dp) + fibonacciSeriesRecursiveWithMemoization(n-2, dp);
        return  dp[n];
    }

    /*
            Time Complexity: O(N)

        Reason: We are running a simple iterative loop

        Space Complexity: O(N)

        Reason: We are using an external array of size ‘n+1’.
     */
    public int fibonacciSeriesTabulation(int n){
        int dp[]=new int[n+1];
        Arrays.fill(dp,-1);
        dp[0]= 0;
        dp[1]= 1;

        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1]+ dp[i-2];
        }
        return  dp[n];
    }

    /*
        Time Complexity: O(N)

        Reason: We are running a simple iterative loop

        Space Complexity: O(1)

        Reason: We are not using any extra space
     */
    public int fibonacciSeriesTabulationSpaceOptimized(int n){
        int prev2 = 0;
        int prev = 1;

        for(int i=2; i<=n; i++){
            int cur_i = prev2+ prev;
            prev2 = prev;
            prev= cur_i;
        }
        return prev;
    }
    
    public static void main(String args[]){
        PartAFibonacciSeries fs = new PartAFibonacciSeries();
        System.out.println(fs.fibonacciSeriesTabulation(15));
        System.out.println(fs.fibonacciSeriesRecursive(15));
        int[] dp = new int[16];
        System.out.println(fs.fibonacciSeriesRecursiveWithMemoization(15, dp));
    }

    /*
     * https://leetcode.com/problems/climbing-stairs/submissions/
     * Video: https://www.youtube.com/watch?v=mLfjzJsN8us&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=3
     * Category: Easy, Must Do, Google, Top150, Fundamental
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
