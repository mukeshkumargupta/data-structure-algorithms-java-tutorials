package com.interview.dynamic;

/*
 * https://www.youtube.com/watch?v=vRVfmbCFW7Y
 * Category: Hard, Must Do
 * https://www.youtube.com/watch?v=vRVfmbCFW7Y
 * https://www.codingninjas.com/codestudio/problems/matrix-chain-multiplication_975344?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTab=1
 */
public class MatrixChainMultiplication {
    public static int matrixMultiplicationUtil(int[] arr , int i, int j, int[][] dp) {
        // Write your code here
        if (i == j) {
            return 0;
        }
        if (dp[i][j] != 0) {
          return dp[i][j];  
        }
        int minCost = Integer.MAX_VALUE;
        for (int partition = i; partition <= j -1; partition++) {
            int eachPartitionCost = arr[i-1] * arr[partition] *  arr[j]  + matrixMultiplicationUtil(arr, i, partition, dp) + matrixMultiplicationUtil(arr, partition+1, j, dp);
            minCost = Math.min(minCost, eachPartitionCost);
        }
        return dp[i][j] = minCost;
    }
    public static int matrixMultiplication(int[] arr , int N) {
        // Write your code here
        int[][] dp = new int [N][N];
        /*
         * TC: o(M*N) , since two dimentional dp you are taking
         * SC o(M*N) + Recursion statck space
         */
        return matrixMultiplicationUtil(arr, 1, N-1,  dp);
    }
    
    
    public static int matrixMultiplicationUtilTabulation(int[] arr , int[][] dp) {
        // Write your code here
        /*
         * TC: O(M*N)
         * SC o(M*N)
         */
        int R = arr.length;
        int C = arr.length;
        for (int diagonal = 1 ; diagonal < R; diagonal++) {
            dp[diagonal][diagonal] = 0;
        }
        /*if (i == j) {
            return 0;
        }
        if (dp[i][j] != 0) {
          return dp[i][j];  
        }*/
        
        for (int i = R-1; i >=1; i--) {// It is reverse of top/dow, since i varies from 1 to N-1 so it will be reverse
            for (int j = i+1; j < C; j++) {/// It is reverse of top/dow, since j varies from N-1 to 1 and j is alswas of right
                int minCost = Integer.MAX_VALUE;
                for (int partition = i; partition <= j -1; partition++) {
                    int eachPartitionCost = arr[i-1] * arr[partition] *  arr[j]  + dp[i][partition] + dp[partition+1][j];
                    dp[i][j] = minCost = Math.min(minCost, eachPartitionCost);
                }
            }
        }
        
        return dp[1][C-1];//last one calculated value we need to return
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
