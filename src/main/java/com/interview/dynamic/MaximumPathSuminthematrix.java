package com.interview.dynamic;
/*
 * https://www.codingninjas.com/codestudio/problems/maximum-path-sum-in-the-matrix_797998
 * Category: Medium, Fundamental,
 * https://www.youtube.com/watch?v=N_aJ5qQbYA0 
 */
public class MaximumPathSuminthematrix {


    /*
     * Without memoization:
     * Since each cell you are exploring 3 path and suppose no of cell is R where R = R*C
     * so TC O(R*(3*3*  R time) i.e 3 ^R
     * SC O(R) where maximum depth of recursion We are using recursion to explore the path so for each step, we are pushing the function into the stack, and there can be total ‘N’ steps Because for each step we are going one step down. So overall space complexity is O(R).
     */
    private static int getMaxPathSumUtil(int i, int j, int[][] matrix, int[][] dp) {
        if (j >= matrix[0].length || j < 0) {
             return (int)-1e8;//Integer.MIN_VALUE;
         }
         
         if (i == 0) {
             return matrix[0][j];
         }
                  
          if (dp[i][j] != 0) {
             return dp[i][j];
          }
                  
         int up =  matrix[i][j] + getMaxPathSumUtil(i-1, j, matrix, dp);
         int ld = matrix[i][j] + getMaxPathSumUtil(i-1, j-1, matrix, dp);
         int rd = matrix[i][j] + getMaxPathSumUtil(i-1, j+1, matrix, dp);

         return dp[i][j] = Math.max(up, Math.max(ld, rd));   
     }
     
     public static int getMaxPathSumRecursiveMemoization(int[][] matrix) {
         /*
          * TC: O(R*C) because each cell travesing , in actual it will take more time because u are pushing and poping which might take more time
          * SC: O(R*C) for dp table + O(R) for oxilary space
          */
         // Write your code here
         int R = matrix.length;
         int C = matrix[0].length;
         int[][] dp = new int[R][C];
         
         int maxSum = (int)-1e8;
         for(int i=0; i < C; i++){ //Ending can from any last row 
             maxSum = Math.max(maxSum, getMaxPathSumUtil(R-1, i, matrix, dp));
         }
         return maxSum;
     }
     
     
    /*
     * Tabulation
     */
    public static int getMaxPathSum(int[][] matrix) {
        // Write your code here
        int R = matrix.length;
        int C = matrix[0].length;
        int[][] dp = new int[R][C];
        
        for(int i=0; i < C; i++){ //Starting can be from any first row
            dp[0][i] = matrix[0][i];
        }

        for(int i=1; i < R; i++){
            for(int j=0; j < C; j++){
                int up = matrix[i][j] + dp[i-1][j];
                int ld = matrix[i][j];
                if (j-1 >=0) {
                    ld += dp[i-1][j-1];
                } else {
                    ld = Integer.MIN_VALUE;
                }
                int rd = matrix[i][j];
                if (j+1 <= C-1) {
                    rd += dp[i-1][j+1];
                } else {
                    rd = Integer.MIN_VALUE;
                }
                dp[i][j] = Math.max(up, Math.max(ld, rd));
            }
        }
        
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i < C; i++){ //Ending can from any last row 
            maxSum = Math.max(maxSum, dp[R-1][i]);
        }
        return maxSum;
    }
    
    public static int getMaxPathSumSpaceOptimization(int[][] matrix) {
        // Write your code here
        int R = matrix.length;
        int C = matrix[0].length;
        int[] prev = null;
        int[] current = new int[C];
        
        for(int i=0; i < C; i++){ //Starting can be from any first row
            current[i] = matrix[0][i];
        }
        prev = current;

        for(int i=1; i < R; i++){
            current = new int[C];
            for(int j=0; j < C; j++){
                int up = matrix[i][j] + prev[j];//dp[i-1][j];
                int ld = matrix[i][j];
                if (j-1 >=0) {
                    ld += prev[j-1];//dp[i-1][j-1];
                } else {
                    ld = Integer.MIN_VALUE;
                }
                int rd = matrix[i][j];
                if (j+1 <= C-1) {
                    rd += prev[j+1];//dp[i-1][j+1];
                } else {
                    rd = Integer.MIN_VALUE;
                }
                //dp[i][j] = Math.max(up, Math.max(ld, rd));
                current[j] = Math.max(up, Math.max(ld, rd));
            }
            prev = current;
        }
        
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i < C; i++){ //Ending can from any last row 
            //maxSum = Math.max(maxSum, dp[R-1][i]);
            maxSum = Math.max(maxSum, current[i]);
        }
        return maxSum;
    }
    
    
    //this is recursion statin g from 0, 0
//  x-directions and y-directions
    static int dR[] = { 1, 1, 1 };
    static int dC[] = { 0, -1, 1 };


    public static boolean isValid(int row, int col, int R, int C) {
        return (row >= 0 && col >= 0 && row < R && col < C);
    }

    public static int getMaxPathSumHelper(int row, int col, int[][] matrix, int[][] dp, int R, int C) {

        // Base case: we are at last row
        if (row == R - 1) {
            return matrix[row][col];
        }

        // Check whether we have already a solution or not?
        if (dp[row][col] != Integer.MIN_VALUE) {
            return dp[row][col];
        }

        int maxPathSum, currPathSum = Integer.MIN_VALUE;

        // Get the maximum path sum from (row,col) to the any cell of last row,
        // excluding (row,col) cell
        for (int i = 0; i < 3; i++) {
            // Get new cell
            int nextRow = row + dR[i];
            int nextCol = col + dC[i];

            // Check whether new cell is a valid cell or not?
            if (isValid(nextRow, nextCol, R, C)) {
                currPathSum = Math.max(currPathSum, getMaxPathSumHelper(nextRow, nextCol, matrix, dp, R, C));
            }
        }

        // Include (row, col) cell into maximum path sum
        maxPathSum = matrix[row][col] + currPathSum;

        // Store in dp to avoid redundant calls
        dp[row][col] = maxPathSum;

        return maxPathSum;
    }

    public static int getMaxPathSumStartingTopLeft(int[][] matrix) {
        /*
         * Time Complexity
O(N*M), Where ‘N’ is the number of rows and ‘M’ is the number of columns in the given matrix.
 

There will be only N*M distinct recursion calls in recursion because we are saving the result so there will be no duplicate calls. So overall time complexity will be O(N*M).

Space Complexity
O(N*M), Where ‘N’ is the number of rows and ‘M’ is the number of columns in the given matrix.
 

We will be memorizing the result for N*M cells using O(N*M) space.
         */
        int maxPathSum = Integer.MIN_VALUE, currPathSum;

        int R = matrix.length;
        int C = matrix[0].length;

        // For storing the output of each cell so that we can avoid redundant calls
        int dp[][] = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = 0; i < C; i++) {
            currPathSum = getMaxPathSumHelper(0, i, matrix, dp, R, C);
            if (currPathSum > maxPathSum) {
                maxPathSum = currPathSum;
            }
        }

        return maxPathSum;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
