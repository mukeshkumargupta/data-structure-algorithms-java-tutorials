package com.interview.dynamic;


/**
 * @Date 08/01/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a total and coins of certain denominations find number of ways total
 * can be formed from coins assuming infinity supply of coins
 * Leetcode: https://leetcode.com/problems/coin-change/
 *
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 * Category: Must Do, Medium, Tricky
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0
Example 4:

Input: coins = [1], amount = 1
Output: 1
Example 5:

Input: coins = [1], amount = 2
Output: 2
 */
public class CoinChanging {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        
        int[][]T = new int[coins.length+1][amount+1];
        T[0][0] = 0;
        for ( int i =1; i <= amount ; i++) { //fill first row
            T[0][i] = Integer.MAX_VALUE;
        }
        
        for (int i = 1; i <  coins.length; i++) {//fill first column
            T[i][0] = 0;
        }
        
        for (int i = 0; i < coins.length ; i++) {
            for (int j = 1; j <= amount; j++ ) {
                if (coins[i] > j) {
                    T[i+1][j] = T[i][j];
                } else {
                    T[i+1][j] =  (T[i+1][j - coins[i]]) == Integer.MAX_VALUE ?  T[i][j]  : Math.min(1 + T[i+1][j - coins[i]],T[i][j]) ;
                }
            }
        }
        return T[coins.length][amount] == Integer.MAX_VALUE ? -1 : T[coins.length][amount];
        
    }
}
