package com.interview.array;

/*
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 * https://www.youtube.com/watch?v=pTQB9wbIpfU
 * Category: Tricky, 
 * Related: https://leetcode.com/problems/meeting-rooms/ Easy
 * https://leetcode.com/problems/minimum-cost-to-hire-k-workers/ Hard
 * https://leetcode.com/problems/maximum-earnings-from-taxi/ Medium
 */
public class BestTimetoBuyandSellStockwithTransactionFee {
    /*
     *This is little slow but more redable code and next is optimized version where some variable is removed
     *Runtime: 7 ms, faster than 47.28% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
Memory Usage: 71.9 MB, less than 55.10% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
     */
    public int maxProfit(int[] prices, int fee) {
        int prevBuyPrice = -prices[0];
        int prevSellPrice = 0;
        for (int i=1; i<prices.length; i++) {

            int currBuyPrice = Math.max(prevBuyPrice, prevSellPrice - prices[i]);
            int currSellPrice = Math.max(prevSellPrice, prevBuyPrice + prices[i] - fee);

            prevBuyPrice = currBuyPrice;
            prevSellPrice = currSellPrice;
        }
        return prevSellPrice;
        
    }
    
    public int maxProfit(int[] prices, int fee) {
        /* Here one variable is removed
         * Runtime: 2 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
Memory Usage: 50.9 MB, less than 67.46% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
         */
        int prevBuyPrice = -prices[0];
        int prevSellPrice = 0;
        for (int i=1; i<prices.length; i++) {

            int currBuyPrice = Math.max(prevBuyPrice, prevSellPrice - prices[i]);
            prevSellPrice = Math.max(prevSellPrice, prevBuyPrice + prices[i] - fee);

            prevBuyPrice = currBuyPrice;
            //prevSellPrice = currSellPrice;
        }
        return prevSellPrice;
        
    }
    public int maxProfit(int[] prices, int fee) {
        /*
         * Runtime: 2 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
Memory Usage: 51.4 MB, less than 58.06% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
TC: O(N)
         */
        int l = prices.length;
        int obsp = -prices[0];//old buy state profit
        int ossp = 0;
        
        for (int i = 1; i < l; i++) {
            int temp = Math.max(ossp-prices[i], obsp);
            ossp = Math.max(prices[i]+obsp-fee, ossp);
            obsp = temp;
        }
        return ossp; 
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
