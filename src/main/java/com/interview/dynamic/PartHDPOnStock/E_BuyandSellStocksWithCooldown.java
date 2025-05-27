package com.interview.dynamic.PartHDPOnStock;

import java.util.Arrays;

public class E_BuyandSellStocksWithCooldown {
    public static class BuyandSellStocksWithCooldown {
        /*
        https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
        https://www.youtube.com/watch?v=IGIe46xw3YY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=40
        Category: Medium
        Related:
        https://leetcode.com/problems/earliest-second-to-mark-indices-i/description/ Medium
        https://leetcode.com/problems/count-special-quadruplets/description/ Easy
        https://leetcode.com/problems/power-of-heroes/description/ Hard
        https://leetcode.com/problems/paint-house-ii/ Hard
         * https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/ Hard
         * https://leetcode.com/problems/find-the-winner-of-the-circular-game/ Medium
         More challenges
        712. Minimum ASCII Delete Sum for Two Strings
        2403. Minimum Time to Kill All Monsters
        3357. Minimize the Maximum Adjacent Element Difference
         * You are given an array prices where prices[i] is the price of a given stock on the ith day.
        You are given an array prices where prices[i] is the price of a given stock on the ith day.

        Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

        After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
        Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



        Example 1:

        Input: prices = [1,2,3,0,2]
        Output: 3
        Explanation: transactions = [buy, sell, cooldown, buy, sell]
        Example 2:

        Input: prices = [1]
        Output: 0


        Constraints:

        1 <= prices.length <= 5000
        0 <= prices[i] <= 1000
            Complexity Analysis
            Time Complexity: O(N*2)

            Reason: There are N*2 states therefore at max ‘N*2’ new problems will be solved and we are running a for loop for ‘N’ times to calculate the total sum

            Space Complexity: O(N*2) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*2)).
         */
        // Recursive function to calculate the maximum profit
        static int getAns(int[] Arr, int ind, int buy, int n, int[][] dp) {
            // Base case
            if (ind >= n) {
                return 0;
            }

            // If the result is already calculated, return it
            if (dp[ind][buy] != -1) {
                return dp[ind][buy];
            }

            int profit = 0;

            if (buy == 0) { // We can buy the stock
                profit = Math.max(0 + getAns(Arr, ind + 1, 0, n, dp),
                        -Arr[ind] + getAns(Arr, ind + 1, 1, n, dp));
            }

            if (buy == 1) { // We can sell the stock
                profit = Math.max(0 + getAns(Arr, ind + 1, 1, n, dp),
                        Arr[ind] + getAns(Arr, ind + 2, 0, n, dp));
            }

            // Store the result in dp and return it
            dp[ind][buy] = profit;
            return profit;
        }

        static int stockProfit(int[] Arr) {
            int n = Arr.length;
            int[][] dp = new int[n][2];

            // Initialize dp array with -1 to mark states as not calculated yet
            for (int[] row : dp) {
                Arrays.fill(row, -1);
            }

            int ans = getAns(Arr, 0, 0, n, dp);
            return ans;
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*2)

            Reason: There are two nested loops that account for O(N*2) complexity.

            Space Complexity: O(N*2)

            Reason: We are using an external array of size ‘N*2’. Stack Space is eliminated.
         */
        // Function to calculate the maximum profit from stock trading
        static int stockProfitTabulation(int[] Arr) {
            int n = Arr.length;
            int dp[][] = new int[n + 2][2];

            // Iterate through the array backwards
            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    int profit = 0;

                    if (buy == 0) { // We can buy the stock
                        profit = Math.max(0 + dp[ind + 1][0], -Arr[ind] + dp[ind + 1][1]);
                    }

                    if (buy == 1) { // We can sell the stock
                        profit = Math.max(0 + dp[ind + 1][1], Arr[ind] + dp[ind + 2][0]);
                    }

                    dp[ind][buy] = profit;
                }
            }

            // The maximum profit is stored in dp[0][0]
            return dp[0][0];
        }

        /*
            Time Complexity: O(N*2)

            Reason: There are two nested loops that account for O(N*2) complexity

            Space Complexity: O(1)

            Reason: We are using three external arrays of size ‘2’.
         */

        // Function to calculate the maximum profit from stock trading
        static int stockProfitSpaceOptimized(int[] Arr) {
            int n = Arr.length;
            int[] cur = new int[2];
            int[] front1 = new int[2];
            int[] front2 = new int[2];

            // Iterate through the array backwards
            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    int profit = 0;

                    if (buy == 0) { // We can buy the stock
                        profit = Math.max(0 + front1[0], -Arr[ind] + front1[1]);
                    }

                    if (buy == 1) { // We can sell the stock
                        profit = Math.max(0 + front1[1], Arr[ind] + front2[0]);
                    }

                    cur[buy] = profit;
                }

                // Update front1 and front2 arrays
                System.arraycopy(front1, 0, front2, 0, 2);
                System.arraycopy(cur, 0, front1, 0, 2);
            }

            // The maximum profit is stored in cur[0]
            return cur[0];
        }


        public static void main(String args[]) {
            int prices[] = {4, 9, 0, 4, 10};

            System.out.println("The maximum profit that can be generated is " + stockProfit(prices));
        }
    }
}
