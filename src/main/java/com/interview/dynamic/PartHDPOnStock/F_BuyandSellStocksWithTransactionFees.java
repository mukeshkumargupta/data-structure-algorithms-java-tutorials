package com.interview.dynamic.PartHDPOnStock;

import java.util.Arrays;

public class F_BuyandSellStocksWithTransactionFees {
        /*
        https://www.youtube.com/watch?v=k4eK-vEmnKg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=41
        https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
         * https://www.youtube.com/watch?v=pTQB9wbIpfU
         * Category: Tricky, Medium
         * Related: https://leetcode.com/problems/meeting-rooms/ Easy
         * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/ Hard
         * https://leetcode.com/problems/maximum-earnings-from-taxi/ Medium
     */

    public static class BuyandSellStocksWithTransactionFees {
        /*
            Complexity Analysis
            Time Complexity: O(N*2)

            Reason: There are N*2 states therefore at max ‘N*2’ new problems will be solved and we are running a for loop for ‘N’ times to calculate the total sum

            Space Complexity: O(N*2) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*2)).
         */
        // Function to calculate the maximum profit
        static int getAns(int[] Arr, int ind, int buy, int n, int fee, int[][] dp) {
            // Base case
            if (ind == n) {
                return 0;
            }

            // If the result is already calculated, return it
            if (dp[ind][buy] != -1) {
                return dp[ind][buy];
            }

            int profit = 0;

            if (buy == 0) { // We can buy the stock
                profit = Math.max(0 + getAns(Arr, ind + 1, 0, n, fee, dp), -Arr[ind] + getAns(Arr, ind + 1, 1, n, fee, dp));
            }

            if (buy == 1) { // We can sell the stock
                profit = Math.max(0 + getAns(Arr, ind + 1, 1, n, fee, dp), Arr[ind] - fee + getAns(Arr, ind + 1, 0, n, fee, dp));
            }

            // Store the result in dp and return it
            dp[ind][buy] = profit;
            return profit;
        }

        // Function to calculate the maximum profit with a transaction fee
        static int maximumProfit(int n, int fee, int[] Arr) {
            int dp[][] = new int[n][2];

            // Initialize dp array with -1 to mark states as not calculated yet
            for (int row[] : dp) {
                Arrays.fill(row, -1);
            }

            if (n == 0) {
                return 0;
            }

            int ans = getAns(Arr, 0, 0, n, fee, dp);
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
        static int maximumProfitTabulation(int n, int fee, int[] Arr) {
            // Handle the base case when n is 0
            if (n == 0) {
                return 0;
            }

            int dp[][] = new int[n + 1][2];

            // Iterate through the array backwards
            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    int profit = 0;

                    if (buy == 0) { // We can buy the stock
                        profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
                    }

                    if (buy == 1) { // We can sell the stock
                        profit = Math.max(0 + dp[ind + 1][1], Arr[ind] - fee + dp[ind + 1][0]);
                    }

                    dp[ind][buy] = profit;
                }
            }

            // The maximum profit is stored in dp[0][0]
            return dp[0][0];
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*2)

            Reason: There are two nested loops that account for O(N*2) complexity

            Space Complexity: O(1)

            Reason: We are using an external array of size ‘2’.
         */
        // Function to calculate the maximum profit
        static long maximumProfitSpaceOptimized(int n, int fee, int[] Arr) {
            // Handle the base case when n is 0
            if (n == 0) {
                return 0;
            }

            long ahead[] = new long[2];
            long cur[] = new long[2];

            // Initialize base conditions
            ahead[0] = ahead[1] = 0;
            long profit = 0;

            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    if (buy == 0) { // We can buy the stock
                        profit = Math.max(0 + ahead[0], -Arr[ind] + ahead[1]);
                    }

                    if (buy == 1) { // We can sell the stock
                        profit = Math.max(0 + ahead[1], Arr[ind] - fee + ahead[0]);
                    }
                    cur[buy] = profit;
                }

                ahead = (long[]) (cur.clone());
            }
            return cur[0];
        }


        public static void main(String args[]) {
            int prices[] = {1, 3, 2, 8, 4, 9};
            int n = prices.length;
            int fee = 2;

            System.out.println("The maximum profit that can be generated is " + maximumProfit(n, fee, prices));
        }
    }
}
