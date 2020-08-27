package com.interview.matrix;
/**
 * https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3301/
 * Variant Problem: https://www.geeksforgeeks.org/min-cost-path-dp-6/  (Here diagonal is also included)
 * */
public class MinimumPathSum {
    
    /* A utility function that returns minimum of 3 integers */
    private static int min(int x, int y) 
    { 
        if (x <y ) {
            return x;
        } else {
            return y;
        }
    } 
  
    
    public int minPathSum(int[][] grid) {
        int m = grid.length; //Row
        int n = grid[0].length; //Column
        int i, j; 
        int tc[][] = new int[m][n]; 
        tc[0][0] = grid[0][0]; 
        
        /* Initialize first column of total cost(tc) array */
        for (i = 1; i < m; i++) 
            tc[i][0] = tc[i-1][0] + grid[i][0]; 
  
        /* Initialize first row of tc array */
        for (j = 1; j < n; j++) 
            tc[0][j] = tc[0][j-1] + grid[0][j]; 
  
        /* Construct rest of the tc array */
        for (i = 1; i < m; i++) 
            for (j = 1; j < n; j++) 
                tc[i][j] = min(
                               tc[i-1][j], 
                               tc[i][j-1]) + grid[i][j]; 
  
        return tc[m-1][n-1]; 
    }
    
    public static void main(String[] args) {
        int cost[][]= {{1, 2, 3}, 
                {4, 8, 2}, 
                {1, 5, 3}};
        MinimumPathSum mps = new MinimumPathSum();
        System.out.println(mps.minPathSum(cost)); 
        
    }
    
}
