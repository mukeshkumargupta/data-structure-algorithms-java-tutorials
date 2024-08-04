package com.interview.dynamic;


/**
 * @Date 08/01/2017
 * @author Mukesh Kumar Gupta
 * Leetcode: https://leetcode.com/problems/coin-change/
 * 
 * Related: https://leetcode.com/problems/minimum-cost-for-tickets/ Medium
 *
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 * Category: Must Do, Medium
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
    public int coinChange(int[] coins, int amount) {//44% runtime it is slow and if for given all value it ask then look making will be fast otherwise gree approach make sense
        
        if (amount == 0) {
            return 0;
        }
        
        int R = coins.length+1;
        int C = amount+1;
        int[][]T = new int[R][C];
        T[0][0] = 0;
        for ( int i =1; i < C ; i++) { //fill first row
            T[0][i] = Integer.MAX_VALUE;
        }
        
        for (int i = 1; i <  R; i++) {//fill first column
            T[i][0] = 0;
        }
        
        for (int i = 1; i < R ; i++) {
            for (int j = 1; j < C; j++ ) {
                if (coins[i-1] > j) {
                    T[i][j] = T[i-1][j];
                } else {
                    T[i][j] =  (T[i][j - coins[i-1]]) == Integer.MAX_VALUE ?  T[i-1][j]  : Math.min(1 + T[i][j - coins[i-1]],T[i-1][j]) ;
                }
            }
        }
        return T[R-1][C-1] == Integer.MAX_VALUE ? -1 : T[R-1][C-1];
        
    }
}
