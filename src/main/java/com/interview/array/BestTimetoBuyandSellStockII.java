package com.interview.array;

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
public class BestTimetoBuyandSellStockII {
    public int maxProfit(int[] prices) {
        /*
         * Runtime: 1 ms, faster than 75.29% of Java online submissions for Best Time to Buy and Sell Stock II.
Memory Usage: 38.8 MB, less than 64.38% of Java online submissions for Best Time to Buy and Sell Stock II.
         */
        int l = prices.length;
        if (l <= 1) {
            return 0;
        }
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < l; i++) {
            //keep updating buy when price drop and sold it also
            if (prices[i] < prices[i-1]) {
                profit += prices[i-1] - buy;
                buy = prices[i];
            }
        }
        //last case when it keep on increasing stock value so you need to sell as well
        profit += prices[l-1] - buy; //even you bough on last day then if you sell no effect
        return profit;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
