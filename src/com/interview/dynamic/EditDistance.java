package com.interview.dynamic;

import java.util.List;

/**
 * Date 07/07/2017
 * @author Mukesh Kumar Gupta
 * Reference: https://leetcode.com/problems/edit-distance/submissions/
 * Category: Hard, Must Do, Google, Facebook
 * Related:
 * https://leetcode.com/problems/one-edit-distance/ Medium
 * https://leetcode.com/problems/delete-operation-for-two-strings/ Medium
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/ Medium
 * https://leetcode.com/problems/uncrossed-lines/ Medium
 *
 * Given two strings how many minimum edits(update, delete or add) is needed to convert one string to another
 *
 * Time complexity is O(m*n)
 * Space complexity is O(m*n)
 *
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 * https://en.wikipedia.org/wiki/Edit_distance
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {//Runtime: 4 ms, faster than 90.37% of Java online submissions for Edit Distance.
        

        int C = word1.length() +1; // Make it column side
        int R = word2.length() +1; //Make it row side
        int[][]dp = new int[R][C];
        dp[0][0] = 0;
        
        for ( int i =1; i < C ; i++) { //fill first row
            dp[0][i] = i;
        }
        
        for (int i = 1; i <  R; i++) {//fill first column
            dp[i][0] = i;
        }
        
        for (int i = 1; i < R ; i++) {
            for (int j = 1; j < C; j++ ) {
                if (word2.charAt(i-1) == word1.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i][j-1]) , dp[i-1][j]) +1;
                }
            }
        }
        return dp[R-1][C-1];
        
    }
    

}
