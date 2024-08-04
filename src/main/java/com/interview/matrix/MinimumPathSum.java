package com.interview.matrix;

import java.util.*;
/**
 * https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/530/week-3/3301/
 * Variant Problem: https://www.geeksforgeeks.org/min-cost-path-dp-6/  (Here diagonal is also included)
 * Other variant question Find all path, then find path then start from bottom right and go to 0,0
 * Reference: https://www.youtube.com/watch?v=lBRtnuxg-gU
 * Note: If it asked path direction then start from R-1 C_1
 * Category: Tricky
 * Status: Done
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
    
    private static int min(int x, int y, int z) // Will be used in variant question 
    { 
       return min(min(x, y), z);
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
    
    //Variant Problem: https://www.geeksforgeeks.org/min-cost-path-dp-6/  (Here diagonal is also included)
    public int minPathSum_V1(int[][] grid) {
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
                               tc[i][j-1], tc[i-1][j-1]) + grid[i][j]; 
  
        return tc[m-1][n-1]; 
    }
    
    public List<Integer> minPathSumDirection(int[][] grid) {
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
        
        //Find path
        List<Integer> pathDirection = new ArrayList<Integer>();
        pathDirection.add(grid[m-1][n-1]);
        //Then see from where it came from total cost matrix
        i = m-1;
        j = n-1;
        //need to implement
        while(true) {
            //If both same then take minimum and then add it
            if (i-1 >= 0 && j-1 >= 0) {
                //pathDirection.add
            }
            break;
        }
        return pathDirection;
    }
    
    public static void main(String[] args) {
        /*int cost[][]= {{1, 2, 3}, 
                {4, 8, 2}, 
                {1, 5, 3}};*/
        
        int cost[][]= {{1, 2, 3}, 
                {1, 8, 2}, 
                {1, 3, 3}}; //Output should be 9 whil ecome from top to botton while in botton to top u go output will come 11 so be careful.
        MinimumPathSum mps = new MinimumPathSum();
        System.out.println(mps.minPathSum(cost)); 
        System.out.println(mps.minPathSum_V1(cost)); //Variant Problem: https://www.geeksforgeeks.org/min-cost-path-dp-6/  (Here diagonal is also included)
        
        
    }
    
}
