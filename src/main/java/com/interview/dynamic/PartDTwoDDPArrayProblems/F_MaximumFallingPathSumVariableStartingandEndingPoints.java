package com.interview.dynamic.PartDTwoDDPArrayProblems;

import java.util.Arrays;

public class F_MaximumFallingPathSumVariableStartingandEndingPoints {
    /*
https://www.youtube.com/watch?v=N_aJ5qQbYA0&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=13
Time Complexity: O(N*N)

Reason: At max, there will be M*N calls of recursion to solve a new problem,

Space Complexity: O(N) + O(N*M)

Reason: We are using a recursion stack space: O(N), where N is the path length and an external DP Array of size ‘N*M’.
 */
    public static class MaximumFallingPathSumVariableStartingandEndingPoints {
        static int getMaxUtil(int i, int j, int C, int[][] matrix, int[][] dp) {
            // Base Conditions
            if (j < 0 || j >= C)
                return (int) Math.pow(-10, 9);//This is constrant so it will not overflow -10^4 <= matrix[i][j] <= 10^4 max 10 power -8 it can go so take less than that
            if (i == 0)
                return matrix[0][j];

            if (dp[i][j] != -1)
                return dp[i][j];

            // Calculate three possible paths: moving up, left diagonal, and right diagonal
            int up =  getMaxUtil(i - 1, j, C, matrix, dp);
            int leftDiagonal = getMaxUtil(i - 1, j - 1, C, matrix, dp);
            int rightDiagonal = getMaxUtil(i - 1, j + 1, C, matrix, dp);

            // Store the maximum of the three paths in dp
            return dp[i][j] = matrix[i][j] + Math.max(up, Math.max(leftDiagonal, rightDiagonal));
        }

        // Function to find the maximum path sum in the matrix
        static int getMaxPathSum(int[][] matrix) {
            int R = matrix.length;
            int C = matrix[0].length;

            int dp[][] = new int[R][C];
            for (int row[] : dp)
                Arrays.fill(row, -1);

            int maxi = Integer.MIN_VALUE;

            // For each starting column, find the maximum path sum and update maxi
            for (int j = 0; j < C; j++) {
                int ans = getMaxUtil(R - 1, j, C, matrix, dp);
                maxi = Math.max(maxi, ans);
            }

            return maxi;
        }

        /*
        Time Complexity: O(N*M)

        Reason: There are two nested loops

        Space Complexity: O(N*M)

        Reason: We are using an external array of size ‘N*M’. The stack space will be eliminated.
         */
        // Function to find the maximum path sum in the matrix using dynamic programming
        static int getMaxPathSumTabulation(int[][] matrix) {
            int R = matrix.length;
            int C = matrix[0].length;

            int dp[][] = new int[R][C];

            // Initializing the first row - base condition
            for (int j = 0; j < C; j++) {
                dp[0][j] = matrix[0][j];
            }

            // Calculate the maximum path sum for each cell in the matrix
            for (int i = 1; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    int up = dp[i - 1][j];

                    int leftDiagonal = (int) Math.pow(-10, 9);
                    if (j - 1 >= 0) {
                        leftDiagonal = dp[i - 1][j - 1];
                    }

                    int rightDiagonal = (int) Math.pow(-10, 9);
                    if (j + 1 < C) {
                        rightDiagonal = dp[i - 1][j + 1];
                    }

                    // Store the maximum of the three paths in dp
                    dp[i][j] = matrix[i][j] + Math.max(up, Math.max(leftDiagonal, rightDiagonal));
                }
            }

            // Find the maximum value in the last row of dp
            int maxi = Integer.MIN_VALUE;
            for (int j = 0; j < C; j++) {
                maxi = Math.max(maxi, dp[R - 1][j]);
            }

            return maxi;
        }

        /*
        Space optimized and working on codeninza platform
         */
        public static int getMaxPathSumSpaceOptimized(int[][] matrix) {
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
                    int up = prev[j];//dp[i-1][j];
                    int ld = (int) Math.pow(-10, 9);
                    if (j-1 >=0) {
                        ld = prev[j-1];//dp[i-1][j-1];
                    }
                    int rd = (int) Math.pow(-10, 9);
                    if (j+1 <= C-1) {
                        rd = prev[j+1];//dp[i-1][j+1];
                    }
                    //dp[i][j] = matrix[i][j] + Math.max(up, Math.max(ld, rd));
                    current[j] = matrix[i][j] + Math.max(up, Math.max(ld, rd));
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


        public static void main(String args[]) {
            int matrix[][] = {{1, 2, 10, 4},
                    {100, 3, 2, 1},
                    {1, 1, 20, 2},
                    {1, 2, 2, 1}};

            // Call the getMaxPathSum function and print the result
            System.out.println(getMaxPathSum(matrix));
        }
    }
}
