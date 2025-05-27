package com.interview.dynamic.PartHDPOnStock;

import java.util.Arrays;

public class C_BuyandSellStockThird {
    /*
    https://www.youtube.com/watch?v=-uQGzhYj8BQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=38

    Category: Hard
    Related:
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/ Hard
    https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/description/ Hard
    https://leetcode.com/problems/maximum-profit-from-trading-stocks/description/

    Code
    Testcase
    Test Result
    Test Result
    123. Best Time to Buy and Sell Stock III
    Hard
    Topics
    Companies
    You are given an array prices where prices[i] is the price of a given stock on the ith day.

    Find the maximum profit you can achieve. You may complete at most two transactions.

    Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



    Example 1:

    Input: prices = [3,3,5,0,0,3,1,4]
    Output: 6
    Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
    Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
    Example 2:

    Input: prices = [1,2,3,4,5]
    Output: 4
    Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
    Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
    Example 3:

    Input: prices = [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.


    Constraints:

    1 <= prices.length <= 105
    0 <= prices[i] <= 105
 */
    public static class BuyandSellStockThird {

        /*
            Complexity Analysis
            Time Complexity: O(N*2*3)

            Reason: There are N*2*3 states therefore at max ‘N*2*3’ new problems will be solved.

            Space Complexity: O(N*2*3) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 3D array ( O(N*2*3)).
         */
        static int getAns(int[] Arr, int n, int ind, int buy, int cap, int[][][] dp) {
            // Base case: If we have processed all stocks or have no capital left, return 0 profit
            if (ind == n || cap == 0)
                return 0;

            // If the result for this state is already calculated, return it
            if (dp[ind][buy][cap] != -1)
                return dp[ind][buy][cap];

            int profit = 0;

            if (buy == 0) { // We can buy the stock
                profit = Math.max(0 + getAns(Arr, n, ind + 1, 0, cap, dp),
                        -Arr[ind] + getAns(Arr, n, ind + 1, 1, cap, dp));
            }

            if (buy == 1) { // We can sell the stock
                profit = Math.max(0 + getAns(Arr, n, ind + 1, 1, cap, dp),
                        Arr[ind] + getAns(Arr, n, ind + 1, 0, cap - 1, dp));
            }

            // Store the calculated profit in the dp array and return it
            return dp[ind][buy][cap] = profit;
        }

        static int maxProfit(int[] prices) {
            int n = prices.length;

            // Creating a 3D dp array of size [n][2][3]
            int[][][] dp = new int[n][2][3];

            // Initialize the dp array with -1
                /*for (int i = 0; i < n; i++) {
                    for (int j = 0; j < 2; j++) {
                        Arrays.fill(dp[i][j], -1);
                    }
                }*/ //This also way to initialized
            for (int[][] elm: dp) {
                for (int[] el: elm) {
                    Arrays.fill(el, -1);
                }
            }

            // Calculate and return the maximum profit
            return getAns(prices, n, 0, 0, 2, dp);
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*2*3)

            Reason: There are three nested loops that account for O(N*2*3) complexity.

            Space Complexity: O(N*2*3)

            Reason: We are using an external array of size ‘N*2*3’. Stack Space is eliminated.
         */
        static int maxProfitTabulation(int[] prices) {
            int n = prices.length;

            // Creating a 3D dp array of size [n+1][2][3] initialized to 0
            int[][][] dp = new int[n + 1][2][3];

            // Loop through the dp array, starting from the second last stock (ind=n-1)
            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    for (int cap = 1; cap <= 2; cap++) {

                        if (buy == 0) { // We can buy the stock
                            dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][0][cap],
                                    -prices[ind] + dp[ind + 1][1][cap]);
                        }

                        if (buy == 1) { // We can sell the stock
                            dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][1][cap],
                                    prices[ind] + dp[ind + 1][0][cap - 1]);
                        }
                    }
                }
            }

            // The maximum profit with 2 transactions is stored in dp[0][0][2]
            return dp[0][0][2];
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*2*3)

            Reason: There are three nested loops that account for O(N*2*3) complexity

            Space Complexity: O(1)

            Reason: We are using two external arrays of size ‘2*3’.
         */
        static int maxProfitSpaceOptimized(int[] prices) {
            int n = prices.length;

            // Create a 2D array 'ahead' and 'cur' to store profit values
            int[][] ahead = new int[2][3];
            int[][] cur = new int[2][3];

            // Loop through the prices array, starting from the second last stock (ind=n-1)
            for (int ind = n - 1; ind >= 0; ind--) {
                for (int buy = 0; buy <= 1; buy++) {
                    for (int cap = 1; cap <= 2; cap++) {

                        if (buy == 0) { // We can buy the stock
                            cur[buy][cap] = Math.max(0 + ahead[0][cap],
                                    -prices[ind] + ahead[1][cap]);
                        }

                        if (buy == 1) { // We can sell the stock
                            cur[buy][cap] = Math.max(0 + ahead[1][cap],
                                    prices[ind] + ahead[0][cap - 1]);
                        }
                    }
                }

                // Update 'ahead' with the values in 'cur'
                for (int i = 0; i < 2; i++) {
                    for (int j = 1; j < 3; j++) {
                        ahead[i][j] = cur[i][j];
                    }
                }
            }

            // The maximum profit with 2 transactions is stored in ahead[0][2]
            return ahead[0][2];
        }

        public static void main(String[] args) {
            int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
            int n = prices.length;

            // Calculate and print the maximum profit
            System.out.println("The maximum profit that can be generated is " + maxProfit(prices));
        }
    }
}
