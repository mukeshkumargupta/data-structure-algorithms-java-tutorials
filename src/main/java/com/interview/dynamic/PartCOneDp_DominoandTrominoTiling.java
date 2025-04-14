package com.interview.dynamic;

import java.util.Arrays;

/*
https://leetcode.com/problems/domino-and-tromino-tiling/?envType=study-plan-v2&envId=leetcode-75
Category: Medium, tricky, top75, Google , FAANG,
https://www.youtube.com/watch?v=Iwmn-gFL3c0&t=11s
Read this video for formula to get it right this is not problem of dp this is problem of deriving the formula itself
You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.


Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.



Example 1:


Input: n = 3
Output: 5
Explanation: The five different ways are show above.
Example 2:

Input: n = 1
Output: 1


Constraints:

1 <= n <= 1000
 */
public class PartCOneDp_DominoandTrominoTiling {
    private static class Memoization {
        int M = 1000000007;
        int solve (int n, int[] memo) {
            if (n ==1 || n ==2) {
                return n;
            }
            if (n ==3) {
                return 5;
            }

            if (memo[n] != -1) {
                return memo[n];
            }

            return memo[n] = (2 * solve(n-1, memo) % M  + solve (n-3, memo) % M) %M;

        }
        public int numTilings(int n) {
            int[] memo = new int[n+1];
            Arrays.fill(memo, -1);
            return solve(n, memo);

        }
    }

    private static class Tabulation {
        public int numTilings(int n) {
            return tabulation(n);

        }
        int M = 1000000007;
        int tabulation(int n) {
            if (n == 1 || n == 2) {
                return n;
            }

            if (n ==3) {
                return 5;
            }
            int[] dp = new int[n+1];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 5;
            for (int i = 4; i <= n; i++) {
                dp[i] = (2* dp[i-1] %M + dp[i-3] %M) %M;
            }
            return dp[n];
        }
    }
}
