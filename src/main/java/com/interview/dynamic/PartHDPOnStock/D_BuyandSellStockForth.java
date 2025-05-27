package com.interview.dynamic.PartHDPOnStock;

import java.util.Arrays;

public class D_BuyandSellStockForth {
        /*
        https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
        https://www.youtube.com/watch?v=IV1dHbk5CDc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=39
        Category: Hard
        Related:
        2291. Maximum Profit From Trading Stocks
        You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

        Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

        Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



        Example 1:

        Input: k = 2, prices = [2,4,1]
        Output: 2
        Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
        Example 2:

        Input: k = 2, prices = [3,2,6,5,0,3]
        Output: 7
        Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.


        Constraints:

        1 <= k <= 100
        1 <= prices.length <= 1000
        0 <= prices[i] <= 1000
     */

    public static class BuyandSellStockForth {
        /*
            Time Complexity: O(N*2*3)

                Reason: There are N*2*K states therefore at max ‘N*2*K’ new problems will be solved.

                Space Complexity: O(N*2*K) + O(N)

                Reason: We are using a recursion stack space(O(N)) and a 3D array ( O(N*2*K)).
         */
        // Function to calculate the maximum profit
        public static int maximumProfit(int[] prices, int n, int k) {
            // Creating a 3D array dp of size [n][2][k+1]
            int[][][] dp = new int[n][2][k + 1];

            // Initialize dp with -1 to mark states as not calculated yet
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }

            return getAns(prices, n, 0, 0, k, dp);
        }

        // Recursive function to calculate the maximum profit
        public static int getAns(int[] prices, int n, int ind, int buy, int cap, int[][][] dp) {
            // Base case
            if (ind == n || cap == 0) {
                return 0;
            }

            // If the result is already calculated, return it
            if (dp[ind][buy][cap] != -1) {
                return dp[ind][buy][cap];
            }

            int profit;

            if (buy == 0) { // We can buy the stock
                profit = Math.max(0 + getAns(prices, n, ind + 1, 0, cap, dp),
                        -prices[ind] + getAns(prices, n, ind + 1, 1, cap, dp));
            } else { // We can sell the stock
                profit = Math.max(0 + getAns(prices, n, ind + 1, 1, cap, dp),
                        prices[ind] + getAns(prices, n, ind + 1, 0, cap - 1, dp));
            }

            // Store the result in dp and return it
            dp[ind][buy][cap] = profit;
            return profit;
        }

        /*
            Time Complexity: O(N*2*k)

            Reason: There are three nested loops that account for O(N*2*K) complexity.

            Space Complexity: O(N*2*k)

            Reason: We are using an external array of size ‘N*2*K’. Stack Space is eliminated.
         */
        // Function to calculate the maximum profit
        public static int maximumProfitTabulation(int[] prices, int n, int k) {
            // Creating a 3D array dp of size [n+1][2][k+1] initialized to 0
            int[][][] dp = new int[n + 1][2][k + 1];

            // As dp array is initialized to 0, we have already covered the base case

            // Iterate through the array and fill in the dp array
            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    for (int cap = 1; cap <= k; cap++) {
                        if (buy == 0) { // We can buy the stock
                            dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][0][cap],
                                    -prices[ind] + dp[ind + 1][1][cap]);
                        } else { // We can sell the stock
                            dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][1][cap],
                                    prices[ind] + dp[ind + 1][0][cap - 1]);
                        }
                    }
                }
            }

            return dp[0][0][k];
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*2*K)

            Reason: There are three nested loops that account for O(N*2*K) complexity

            Space Complexity: O(K)

            Reason: We are using two external arrays of size ‘2*K’.
         */

        // Function to calculate the maximum profit
        public static int maxProfitSpaceOptimized(int[] prices, int n, int k) {
            // Create two 2D arrays: 'ahead' and 'cur' to store intermediate results
            int[][] ahead = new int[2][k + 1];
            int[][] cur = new int[2][k + 1];

            // Iterate through the array backwards
            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    for (int cap = 1; cap <= k; cap++) {
                        if (buy == 0) { // We can buy the stock
                            cur[buy][cap] = Math.max(0 + ahead[0][cap],
                                    -prices[ind] + ahead[1][cap]);
                        } else { // We can sell the stock
                            cur[buy][cap] = Math.max(0 + ahead[1][cap],
                                    prices[ind] + ahead[0][cap - 1]);
                        }
                    }
                }
                // Update the 'ahead' array with the values from 'cur'
                for (int i = 0; i < 2; i++) {
                    System.arraycopy(cur[i], 0, ahead[i], 0, k + 1);
                }
            }

            return ahead[0][k];
        }

        public static void main(String[] args) {
            int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
            int n = prices.length;
            int k = 2;

            System.out.println("The maximum profit that can be generated is " + maximumProfit(prices, n, k));
        }
    }
}
