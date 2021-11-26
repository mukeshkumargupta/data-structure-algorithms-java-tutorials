package com.interview.dynamic;

import java.util.*;

/**
 * Date 10/19/2017
 * @author Mukesh Kumar Gupta
 *

 *
 * https://leetcode.com/problems/perfect-squares/
 * https://www.youtube.com/watch?v=1xfx6M_GqFk&t=423s Very good problem, Must Draw Recursion Tree to understand
 * Category: Medium, Must Do
 * Related: https://leetcode.com/problems/ugly-number-ii/ Medium
 * Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

 

Example 1:

Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 

Constraints:

1 <= n <= 104
 */
public class MinimumNumberOfPerfectSquares {
    //Tabulation
    /*Runtime: 33 ms, faster than 71.17% of Java online submissions for Perfect Squares.
    Memory Usage: 38 MB, less than 85.02% of Java online submissions for Perfect Squares.
    TC: N*sqrt of N
    */
    
    public int numSquares(int n) {
        int[] dp = new int[n+1];

        
        for (int i = 1; i <= n ; i++ ) {
            dp[i] = i;
            for (int j = 1; j*j <=i; j++) {//O sqrt of N
                dp[i] = Math.min (dp[i], 1 + dp[i - j*j]);
            }
            
        }
        return dp[n];
        
    }
    
    public int numSquaresUtil(int n, int[] dp) {
        if ( n <= 3) {
            dp[n] = n;
            return dp[n];
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int ans = n;
        for (int i = 1; i*i <=n; i++) {
            ans = Math.min (ans, 1 + numSquaresUtil(n - i*i, dp));
        }
        dp[n] = ans;
        return dp[n];
        
    }

    public int numSquaresWithMemoization(int n) {
        /*
         * Runtime: 67 ms, faster than 38.56% of Java online submissions for Perfect Squares.
Memory Usage: 39.2 MB, less than 58.36% of Java online submissions for Perfect Squares.
         */
        int[] dp = new int[n+1];
        for (int i = 0; i < n+1; i++) {
            dp[i] = -1;
        }
        return numSquaresUtil(n, dp );
        
    }
    public int numSquaresWithoutMemoization(int n) {
        //Time limit exhasted
        if ( n <= 3) {
            return n;
        }
        int ans = n;
        for (int i = 1; i*i <=n; i++) {
            ans = Math.min (ans, 1 + numSquaresWithoutMemoization(n - i*i));
        }
        return ans;
        
    }

    public int numSquaresUsingBFS(int n) {
        List<Integer> perfectSquares = new ArrayList<>();
        int i = 1;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        while (true) {
            int square = i * i;
            if (square == n) {
                return 1;
            }
            if (square > n) {
                break;
            }
            perfectSquares.add(square);
            queue.offer(square);
            visited.add(square);
            i++;
        }
        int distance = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int j = 0; j < size; j++) {
                int TreeNode = queue.poll();
                for (int square : perfectSquares) {
                    int sum = TreeNode + square;
                    if (sum == n) {
                        return distance;
                    } else if (!visited.contains(sum)) {
                        visited.add(sum);
                        queue.add(sum);
                    } else if (sum > n) {
                        break;
                    }
                }
            }
        }
        return distance;
    }
}
