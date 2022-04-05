package com.interview.array;
/*
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * https://www.youtube.com/watch?v=GY0O57llkKQ
 * Category: Medium, Tricky
 * Related: https://leetcode.com/problems/paint-house-ii/ Hard
 * https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/ Hard
 * https://leetcode.com/problems/find-the-winner-of-the-circular-game/ Medium
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.

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
 */
public class BestTimetoBuyandSellStockwithCooldown {
    public int maxProfitSlow(int[] prices) {
        /*
         * Runtime: 1 ms, faster than 78.37% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
Memory Usage: 42 MB, less than 5.13% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
         */
        int prevBuyPrice = -prices[0];
        int prevSellPrice = 0;
        int prevCoolDownPrice = 0;
        for (int i=1; i<prices.length; i++) {
            int currBuyPrice = 0;
            int currSellPrice = 0;
            int currCoolDownPrice = 0;
            currBuyPrice = Math.max(prevBuyPrice, prevCoolDownPrice - prices[i]);
            currSellPrice = Math.max(prevSellPrice, prevBuyPrice + prices[i]);
            currCoolDownPrice = Math.max(prevCoolDownPrice, prevSellPrice);
            prevBuyPrice = currBuyPrice;
            prevSellPrice = currSellPrice;
            prevCoolDownPrice = currCoolDownPrice;
        }
        return prevSellPrice;
    }
    public int maxProfit(int[] prices) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
Memory Usage: 40.1 MB, less than 7.96% of Java online submissions for Best Time to Buy and Sell Stock with Cooldown.
TC O(N)
         */
        int bDay = 0 - prices[0];
        int sDay = 0;
        int cDay = 0;
        
        for(int i=1;i<prices.length;i++){
            bDay = Math.max(cDay-prices[i] , bDay);
            cDay = Math.max(sDay,cDay);
            sDay = Math.max(bDay+prices[i],sDay); 
        }
        return sDay;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
