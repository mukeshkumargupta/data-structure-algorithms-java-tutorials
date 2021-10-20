package com.interview.matrix;

/*
 * Reference: https://leetcode.com/problems/unique-paths/
 * https://www.youtube.com/watch?v=rBAxUTqvlQA&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=8
 * Category: Medium
 * Related:https://leetcode.com/problems/unique-paths-ii/ Medium
 * https://leetcode.com/problems/dungeon-game/ Hard
 */

public class TotalPath {
    public int uniquePaths(int m, int n) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
        int[][] T = new int[m][n];
        for(int i=0; i < m; i++){ //fill first row
            T[i][0] = 1;
        }
        
        for(int i=0; i < n; i++){//fill first column
            T[0][i] = 1;
        }
        for(int i=1; i < m; i++){
            for(int j=1; j < n; j++){
                T[i][j] = T[i-1][j] + T[i][j-1];
            }
        }
        return T[m-1][n-1];
        
    }
    
}
