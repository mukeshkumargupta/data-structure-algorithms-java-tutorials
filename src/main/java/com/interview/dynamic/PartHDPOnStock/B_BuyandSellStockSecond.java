package com.interview.dynamic.PartHDPOnStock;

import java.util.Arrays;

public class B_BuyandSellStockSecond {
    /*
     * Problem: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
     * Category: Medium, Facebook, Tricky, Buy-and-Sell Stock, Top150
     *
     * Related Problems:
     * - Best Time to Buy and Sell Stock III (Hard): https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
     * - Best Time to Buy and Sell Stock IV (Hard): https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
     * - Best Time to Buy and Sell Stock with Cooldown (Medium): https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
     *      Explanation Video: https://www.youtube.com/watch?v=GY0O57llkKQ
     * - Best Time to Buy and Sell Stock with Transaction Fee (Medium): https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
     *      Explanation Video: https://www.youtube.com/watch?v=pTQB9wbIpfU
     *
     * Problem Statement:
     * You are given an integer array `prices` where prices[i] represents the price of a stock on the i-th day.
     * On each day, you may choose to buy and/or sell the stock. You can only hold one share at a time,
     * but you may buy and sell multiple times as long as you only hold one share at any given time.
     * The goal is to maximize your profit.
     *
     * Example 1:
     * Input: prices = [7, 1, 5, 3, 6, 4]
     * Output: 7
     * Explanation:
     * - Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5 - 1 = 4.
     * - Buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6 - 3 = 3.
     * - Total profit is 4 + 3 = 7.
     *
     * Example 2:
     * Input: prices = [1, 2, 3, 4, 5]
     * Output: 4
     * Explanation:
     * - Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5 - 1 = 4.
     * - Total profit is 4.
     *
     * Example 3:
     * Input: prices = [7, 6, 4, 3, 1]
     * Output: 0
     * Explanation: No profitable transactions are possible.
     *
     * Constraints:
     * - 1 <= prices.length <= 3 * 10^4
     * - 0 <= prices[i] <= 10^4
     *
     * Video Explanation:
     * - https://www.youtube.com/watch?v=nGJmxkUJQGs&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=37
     */
    public static class BuyandSellStockSecond {
        /*
            Complexity Analysis
            Time Complexity: O(N*2)

            Reason: There are N*2 states therefore at max ‘N*2’ new problems will be solved and we are running a for loop for ‘N’ times to calculate the total sum

            Space Complexity: O(N*2) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*2)).
         */
        // Recursive function to calculate the maximum profit
        static long getMaximumProfitUtil(long[] Arr, int ind, int buy, int n, long[][] dp) {
            // Base case
            if (ind == n)
                return 0;

            // If the result is already computed, return it
            if (dp[ind][buy] != -1)
                return dp[ind][buy];

            long profit = 0;

            if (buy == 0) { // We can buy the stock
                profit = Math.max(0 + getMaximumProfitUtil(Arr, ind + 1, 0, n, dp),
                        -Arr[ind] + getMaximumProfitUtil(Arr, ind + 1, 1, n, dp));
            }

            if (buy == 1) { // We can sell the stock
                profit = Math.max(0 + getMaximumProfitUtil(Arr, ind + 1, 1, n, dp),
                        Arr[ind] + getMaximumProfitUtil(Arr, ind + 1, 0, n, dp));
            }

            // Store the result in the dp table and return it
            return dp[ind][buy] = profit;
        }

        // Function to calculate the maximum profit
        static long getMaximumProfit(long[] Arr, int n) {
            // Create a 2D vector for memoization (dp)
            long[][] dp = new long[Arr.length][2];
            for (long[] row : dp) {
                Arrays.fill(row, -1);
            }

            // Base case: If n is 0, return 0 profit
            if (n == 0)
                return 0;

            // Calculate the maximum profit using the recursive function
            long ans = getMaximumProfitUtil(Arr, 0, 0, n, dp);
            return ans;
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*2)

            Reason: There are two nested loops that account for O(N*2) complexity.

            Space Complexity: O(N*2)

            Reason: We are using an external array of size ‘N*2’. Stack Space is eliminated.
         */
        // Function to calculate the maximum profit
        static long getMaximumProfitTabulation(long[] Arr, int n) {
            // Create a 2D array for dynamic programming with dimensions [n+1][2]
            long[][] dp = new long[n + 1][2];

            // Initialize the entire dp table with -1
            for (long[] row : dp) {
                Arrays.fill(row, -1);
            }

            // Base condition: If we have no stocks to buy or sell, profit is 0
            dp[n][0] = dp[n][1] = 0;

            long profit = 0;

            // Iterate through the array in reverse to calculate the maximum profit
            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    if (buy == 0) { // We can buy the stock
                        profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
                    }

                    if (buy == 1) { // We can sell the stock
                        profit = Math.max(0 + dp[ind + 1][1], Arr[ind] + dp[ind + 1][0]);
                    }

                    dp[ind][buy] = profit;
                }
            }
            return dp[0][0]; // The maximum profit is stored at dp[0][0]
        }
        /*
            Complexity Analysis
            Time Complexity: O(N*2)

            Reason: There are two nested loops that account for O(N*2) complexity

            Space Complexity: O(1)

            Reason: We are using an external array of size ‘2’.
         */

        // Function to calculate the maximum profit
        static long getMaximumProfitSpaceOptimized(long[] Arr, int n) {
            // Create arrays 'ahead' and 'cur' to store the maximum profit ahead and current profit
            long[] ahead = new long[2];
            long[] cur = new long[2];

            // Base condition: If we have no stocks to buy or sell, profit is 0
            ahead[0] = ahead[1] = 0;

            long profit = 0;

            // Iterate through the array in reverse to calculate the maximum profit
            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    if (buy == 0) { // We can buy the stock
                        profit = Math.max(0 + ahead[0], -Arr[ind] + ahead[1]);
                    }

                    if (buy == 1) { // We can sell the stock
                        profit = Math.max(0 + ahead[1], Arr[ind] + ahead[0]);
                    }
                    cur[buy] = profit;
                }

                // Update the 'ahead' array with the current profit values
                System.arraycopy(cur, 0, ahead, 0, 2);
            }
            return cur[0]; // The maximum profit is stored in 'cur[0]'
        }
    }
}
