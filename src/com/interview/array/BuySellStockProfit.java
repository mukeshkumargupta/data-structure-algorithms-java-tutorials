package com.interview.array;

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
public class BuySellStockProfit {

    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int l = prices.length;
        int maxProfit = 0;
        for (int i = 1; i < l; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                if ( (prices[i] - buy) > maxProfit) {
                    maxProfit = prices[i] - buy;
                }
            }
        }
        return maxProfit;
        
    }
    
    public int allTimeProfit(int arr[]){
        int profit = 0;
        for(int i=1; i < arr.length;i++){
            if(arr[i-1] < arr[i]){
                profit += arr[i] - arr[i-1];
            }
        }
        return profit;
    }
    
    public static void main(String args[]){
        int arr[] = {7,10,15,5,11,2,7,9,3};
        int arr1[] = {6,4,1,3,5,7,3,1,3,4,7,9,2,5,6,0,1,2};
        BuySellStockProfit bss = new BuySellStockProfit();
        System.out.println(bss.maxProfit(arr));
        System.out.print(bss.allTimeProfit(arr1));
        
    }
}
