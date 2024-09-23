package com.interview.dynamic;

import java.util.Arrays;

public class PartHDPOnStock {
    public static class BestTimetoBuyandSellStock {
        /*
        https://www.youtube.com/watch?v=excAOvwF_Wk&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=37
        https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
        Category: Easy
         */
        /**
         * Date 03/04/2017
         * @author Mukesh Kumar Gupta
         * Category: Easy, Top150
         * Best time to buy and sell stocks.
         * 1) Only 1 transaction is allowed
         * 2) Infinite number transactions are allowed
         *
         * Time complexity O(n)
         * Space complexity O(1)
         *
         * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
         * Category: Easy, Tricky
         * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
         *
         * You are given an array prices where prices[i] is the price of a given stock on the ith day.

        You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

        Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.



        Example 1:

        Input: prices = [7,1,5,3,6,4]
        Output: 5
        Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
        Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
        Example 2:

        Input: prices = [7,6,4,3,1]
        Output: 0
        Explanation: In this case, no transactions are done and the max profit = 0.


        Constraints:

        1 <= prices.length <= 105
        0 <= prices[i] <= 104
        Accepted
        1,691,213
        Submissions
        3,186,754
         */

        /*
            Output:

            The maximum profit by selling the stock is 5

            Time Complexity: O(N)

            Reason: We are performing a single iteration

            Space Complexity: O(1)

            Reason: No extra space is used.
         */

        static int maximumProfit(int []Arr){
            // Write your code here.
            int maxProfit = 0;
            int mini = Arr[0];

            for(int i=1;i<Arr.length;i++){
                int curProfit = Arr[i] - mini;
                maxProfit = Math.max(maxProfit,curProfit);
                mini = Math.min(mini,Arr[i]);
            }
            return maxProfit;
        }

        public static void main(String args[]) {

            int[] Arr  ={7,1,5,3,6,4};

            System.out.println("The maximum profit by selling the stock is "+
                    maximumProfit(Arr));
        }
    }

    /*
    https://www.youtube.com/watch?v=nGJmxkUJQGs&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=37
    https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/submissions/
     */
    /*
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * Category: Medium, Facebook, Tricky, buy-and-sell, Top150
 *
 * Related: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/ Hard
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/ Hard
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/ Medium  well explained: https://www.youtube.com/watch?v=GY0O57llkKQ
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/ Medium well explained: https://www.youtube.com/watch?v=pTQB9wbIpfU
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.


Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
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
            long[][] dp = new long [Arr.length][2];
            for (long[] row: dp) {
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
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < 2; j++) {
                        Arrays.fill(dp[i][j], -1);
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

    /*
        https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
        https://www.youtube.com/watch?v=IV1dHbk5CDc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=39
        Category: Hard
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

    public static class BuyandSellStocksWithCooldown {
        /*
        https://www.youtube.com/watch?v=IGIe46xw3YY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=40
        Category: Medium
        Related:
        https://leetcode.com/problems/earliest-second-to-mark-indices-i/description/ Medium
        https://leetcode.com/problems/count-special-quadruplets/description/ Easy
        https://leetcode.com/problems/power-of-heroes/description/ Hard
        https://leetcode.com/problems/paint-house-ii/ Hard
         * https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/ Hard
         * https://leetcode.com/problems/find-the-winner-of-the-circular-game/ Medium
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
