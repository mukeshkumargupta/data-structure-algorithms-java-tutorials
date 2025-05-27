package com.interview.dynamic.PartHDPOnStock;

public class A_BestTimetoBuyandSellStock {
    public static class BestTimetoBuyandSellStock {
        /*
         * Problem: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
         * Video Explanation: https://www.youtube.com/watch?v=excAOvwF_Wk&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=37
         * Category: Easy, Top150, Tricky
         * Author: Mukesh Kumar Gupta
         * Date: 03/04/2017
         *
         * Problem Statement:
         * You are given an array `prices` where prices[i] is the price of a stock on the i-th day.
         * You want to maximize your profit by selecting a single day to buy one stock and a different future day to sell it.
         * Return the maximum profit possible from this transaction. If no profit can be achieved, return 0.
         *
         * Example 1:
         * Input: prices = [7, 1, 5, 3, 6, 4]
         * Output: 5
         * Explanation:
         * - Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5.
         * - Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
         *
         * Example 2:
         * Input: prices = [7, 6, 4, 3, 1]
         * Output: 0
         * Explanation: In this case, no transactions are made, so max profit = 0.
         *
         * Constraints:
         * - 1 <= prices.length <= 10^5
         * - 0 <= prices[i] <= 10^4
         *
         * Key Notes:
         * 1. Only one transaction is allowed.
         * 2. Infinite transactions are allowed if solving the problem variation.
         *
         * Complexity:
         * - Time Complexity: O(N) - Single pass through the array.
         * - Space Complexity: O(1) - Only a few extra variables used.
         *
         * Code:
         */

        static int maximumProfit(int []Arr){
            // Write your code here.
            int maxProfit = 0;
            int buy = Arr[0];

            for(int i=1;i<Arr.length;i++){
                int curProfit = Arr[i] - buy;
                maxProfit = Math.max(maxProfit,curProfit);
                buy = Math.min(buy,Arr[i]);
            }
            return maxProfit;
        }

        public static void main(String args[]) {

            int[] Arr  ={7,1,5,3,6,4};

            System.out.println("The maximum profit by selling the stock is "+
                    maximumProfit(Arr));
        }
    }
}
