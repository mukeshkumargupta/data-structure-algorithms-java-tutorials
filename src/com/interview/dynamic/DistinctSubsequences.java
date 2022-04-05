package com.interview.dynamic;

/*
 * https://leetcode.com/problems/distinct-subsequences/
 * Category: Hard, subsequences derivative, Tricky
 * Related: https://leetcode.com/problems/number-of-unique-good-subsequences/ Hard, VVImp
 * 
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.

A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

The test cases are generated so that the answer fits on a 32-bit signed integer.

 

Example 1:

Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
babgbag
babgbag
babgbag
babgbag
babgbag
 

Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
 */
public class DistinctSubsequences {
    public int numDistinctUtil(String s, String t, int i, int j) {
        /*
        TC: 2^M * 2 ^ N combination whene m, n is lkength
        SC: O(M+N) recursion stack space
        */
        
        if (j < 0) {
            return 1;
        }
        
        if (i < 0) {
            return 0;
        }
        if (s.charAt(i) == t.charAt(j)) { // if both same then two way you can select
            return numDistinctUtil(s, t, i-1, j-1) + numDistinctUtil(s, t, i-1, j);//last for not considering so j will not decrease
        } else {
            return numDistinctUtil(s, t, i-1, j);//if jth not equal then j will not decrease
        }
        
        
    }
    
    public int numDistinctUtilMemoIzation(String s, String t, int i, int j, int[][] dp) {
        /*
        TC O*M*N)
        SC: O(M*N) + recursion stack space O(M+N)
        
        */
        if (j < 0) {
            return 1;
        }
        
        if (i < 0) {
            return 0;
        }
        if (s.charAt(i) == t.charAt(j)) { // if both same then two way you can select
            dp[i][j]  = numDistinctUtilMemoIzation(s, t, i-1, j-1, dp) + numDistinctUtilMemoIzation(s, t, i-1, j, dp);//last for not considering so j will not decrease
        } else {
            dp[i][j] = numDistinctUtilMemoIzation(s, t, i-1, j, dp);//if jth not equal then j will not decrease
        }
        return dp[i][j];
        
        
    }
    
    public int numDistinctUtilMemoIzationWithZeroIndexingForTabulation(String s, String t, int i, int j, int[][] dp) {
        if (j == 0) {
            return 1;
        }
        
        if (i == 0) {
            return 0;
        }
        if (s.charAt(i-1) == t.charAt(j-1)) { // if both same then two way you can select
            dp[i][j]  = numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, i-1, j-1, dp) + numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, i-1, j, dp);//last for not considering so j will not decrease
        } else {
            dp[i][j] = numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, i-1, j, dp);//if jth not equal then j will not decrease
        }
        return dp[i][j];
        
        
    }
    

    public int numDistinctUtilTabulation(String s, String t,int[][] dp) {
        int R = s.length() +1;
        int C = t.length() +1;
        for (int i = 0; i < R; i++) {
            dp[i][0] = 1;
        }
        /*if (j == 0) {
            return 1;
        }*/
        
        /*if (i == 0) {//by default is zeo so not required
            return 0;
        }*/
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
                
            }
            
        }
        return dp[R-1][C-1];
        /*if (s.charAt(i-1) == t.charAt(j-1)) { // if both same then two way you can select
            dp[i][j]  = numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, i-1, j-1, dp) + numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, i-1, j, dp);//last for not considering so j will not decrease
        } else {
            dp[i][j] = numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, i-1, j, dp);//if jth not equal then j will not decrease
        }
        return dp[i][j];*/
        
        
    }
    
    public int numDistinctUtilTabulationSpace(String s, String t,int[][] dp) {
        int R = s.length() +1;
        int C = t.length() +1;
        int[] current = new int[C];
        int[] previous = null;
        for (int i = 0; i < R; i++) {
            //dp[i][0] = 1;
            current[0] = 1;
        }
        previous = current;
        /*if (j == 0) {
            return 1;
        }*/
        
        /*if (i == 0) {//by default is zeo so not required
            return 0;
        }*/
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    //dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                    current[j] = previous[j-1] + previous[j];
                } /*else {//Not required
                    current[j] = previous[j];
                }*/
                
            }
            previous = current ;
            
        }
        //return dp[R-1][C-1];
        return previous[C-1];
        /*if (s.charAt(i-1) == t.charAt(j-1)) { // if both same then two way you can select
            dp[i][j]  = numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, i-1, j-1, dp) + numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, i-1, j, dp);//last for not considering so j will not decrease
        } else {
            dp[i][j] = numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, i-1, j, dp);//if jth not equal then j will not decrease
        }
        return dp[i][j];*/
        
        
    }
    public int numDistinct(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        //int[][] dp = new int[sLength][tLength];// modified dp
        int[][] dp = new int[sLength+1][tLength+1];
        //return numDistinctUtil(s, t, sLength-1, tLength-1);//53 / 64 test cases passed.
        //return numDistinctUtilMemoIzation(s, t, sLength-1, tLength-1, dp); //53 / 64 test cases passed.
        //return numDistinctUtilMemoIzationWithZeroIndexingForTabulation(s, t, sLength, tLength, dp);//53 / 64 test cases passed.
        return numDistinctUtilTabulation(s, t, dp);
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
