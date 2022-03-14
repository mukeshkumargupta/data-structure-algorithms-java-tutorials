package com.interview.dynamic;

/*
 * Reference: https://leetcode.com/problems/unique-paths/
 * https://www.youtube.com/watch?v=sdE0A2Oxofw
 * https://www.youtube.com/watch?v=rBAxUTqvlQA&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=8
 * Category: Medium, Fundamental
 * Related:https://leetcode.com/problems/unique-paths-ii/ Medium
 * https://leetcode.com/problems/dungeon-game/ Hard
 * https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/ Medium
 */

public class TotalPath {
    public int uniquePathsUtil(int i, int j, int[][] dp) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
Memory Usage: 40.9 MB, less than 5.23% of Java online submissions for Unique Paths.
         */
        if (i == 0 && j == 0) {
            return 1;
        }
        
        if (i < 0 || j < 0) {
            return 0;
        }
                 
         if (dp[i][j] != 0) {
            return dp[i][j];
         }
                 
                 
        int up = 0;
        int left = 0;
        if (i > 0) {
           up =  uniquePathsUtil(i-1, j, dp);
        }
        if (j > 0) {
           left =  uniquePathsUtil(i, j-1, dp);
        }
        return dp[i][j] = up + left;
    }
    public int uniquePathsRecursion(int m, int n) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
Memory Usage: 40.9 MB, less than 5.23% of Java online submissions for Unique Paths.
         */
        int[][] dp = new int[m][n];
        return uniquePathsUtil(m-1, n-1, dp);
    }
    
    public int uniquePaths(int m, int n) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
        int[][] dp = new int[m][n];
        for(int i=0; i < m; i++){ //fill first row
            dp[i][0] = 1;
        }
        
        for(int i=0; i < n; i++){//fill first column
            dp[0][i] = 1;
        }
        for(int i=1; i < m; i++){
            for(int j=1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
        
    }
    
}
