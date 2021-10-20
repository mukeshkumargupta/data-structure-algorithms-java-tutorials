package com.interview.dynamic;

/*
 * Reference: https://leetcode.com/problems/coin-change-2/
 * Category: Medium , Must Do, Tricky
 * Related:
 * https://leetcode.com/problems/remove-boxes/ Hard
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/ Medium done
 * https://leetcode.com/problems/k-concatenation-maximum-sum/ Medium
 * 
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

 

Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1

Example: 5
[1,2,5]

Note: here 1 and 2 make total 3 and 5 we make 1 so column 0 is set to 1 just for understanding
 */
public class CoinChange2 {
    public int change(int amount, int[] coins) {
        int R = coins.length+1;
        int C = amount+1;
        int[][]T = new int[R][C];

        T[0][0] = 1;
        for ( int i =1; i < C ; i++) { //fill first row
            T[0][i] = 0;
        }
        
        for (int i = 1; i <  R; i++) {//fill first column
            T[i][0] = 1;
        }
        
        for (int i = 1; i < R ; i++) {
            for (int j = 1; j < C; j++ ) {
                if (coins[i-1] > j) {
                    T[i][j] = T[i-1][j];
                } else {
                    T[i][j] = T[i][j - coins[i-1]] + T[i-1][j];//Tricky including + excluding
                }
            }
        }
        return T[R-1][C-1];
        
    }
}
