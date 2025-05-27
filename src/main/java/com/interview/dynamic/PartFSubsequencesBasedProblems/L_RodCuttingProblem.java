package com.interview.dynamic.PartFSubsequencesBasedProblems;

import java.util.Arrays;

public class L_RodCuttingProblem {
       /*
    https://www.naukri.com/code360/problems/rod-cutting-problem_800284?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
         * https://practice.geeksforgeeks.org/problems/rod-cutting0840/1
         * Related Question: https://leetcode.com/problems/minimum-cost-to-cut-a-stick/ Hard
         * Category: Must Do
         * https://www.youtube.com/watch?v=eQuJb5gBkkc at 1:5:52 Best explanation after it
         *
         * Given a rod of length N inches and an array of prices, price[] that contains prices of all pieces of size smaller than N. Determine the maximum value obtainable by cutting up the rod and selling the pieces.



        Example 1:

        Input:
        N = 8
        Price[] = {1, 5, 8, 9, 10, 17, 17, 20}
        Output:
        22
        Explanation:
        The maximum obtainable value is 22 by
        cutting in two pieces of lengths 2 and
        6, i.e., 5+17=22.
        Example 2:

        Input:
        N=8
        Price[] = {3, 5, 8, 9, 10, 17, 17, 20}
        Output: 24
        Explanation:
        The maximum obtainable value is
        24 by cutting the rod into 8 pieces
        of length 1, i.e, 8*3=24.

        Your Task:
        You don't need to read input or print anything. Your task is to complete the function cutRod() which takes the array A[] and its size N as inputs and returns the maximum price obtainable.


        Expected Time Complexity: O(N2)
        Expected Auxiliary Space: O(N)


        Constraints:
        1 ≤ N ≤ 1000
        1 ≤ Ai ≤ 105
     */

    public static class RodCuttingProblem {
        /*
            Time Complexity: O(N*W)

            Reason: There are N*W states therefore at max ‘N*W’ new problems will be solved.

            Space Complexity: O(N*W) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*W)).
         */
        // Recursive function to solve the unbounded knapsack problem
        static int cutRodUtil(int ind, int n, int[] price, int[][] dp) {
            // Base case: If there are no more items to consider
            if (ind == 0) {
                // Calculate and return the maximum value possible
                return ((int) (n / 1)) * price[0];
            }

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind][n] != -1)
                return dp[ind][n];

            // Calculate the maximum value when the current item is not taken
            int notTaken = 0 + cutRodUtil(ind - 1, n, price, dp);

            // Initialize the maximum value when the current item is taken as the minimum integer value
            int taken = Integer.MIN_VALUE;
            int rodLength = ind + 1;

            // If the weight of the current item is less than or equal to the available capacity (W),
            // calculate the maximum value when the current item is taken
            if (rodLength <= n)
                taken = price[ind] + cutRodUtil(ind, n - rodLength, price, dp);

            // Store the result in the dp array and return it
            return dp[ind][n] = Math.max(notTaken, taken);
        }


        static int cutRod(int n, int[] price) {
            // Create a 2D array to store results of subproblems
            int[][] dp = new int[n][n + 1];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the knapsackUtil function to solve the problem
            return cutRodUtil(n-1, n, price, dp);
        }

        /*
            Time Complexity: O(N*W)

            Reason: There are two nested loops

            Space Complexity: O(N*W)

            Reason: We are using an external array of size ‘N*W’. Stack Space is eliminated.
         */

        // Function to solve the unbounded knapsack problem
        static int cutRodTabulation(int n, int[] price) {
            // Create a 2D array to store results of subproblems
            int[][] dp = new int[n][n + 1];

            // Base condition: Initialize the dp array for the first item
            for (int N = 0; N <= n; N++) {
                dp[0][N] = (N / 1) * price[0];
            }

            // Fill the dp array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int cap = 0; cap <= n; cap++) {
                    // Calculate the maximum value when the current item is not taken
                    int notTaken = 0 + dp[ind - 1][cap];

                    // Initialize the maximum value when the current item is taken as the minimum integer value
                    int taken = Integer.MIN_VALUE;
                    int rodLength = ind + 1;
                    // If the weight of the current item is less than or equal to the current capacity (cap),
                    // calculate the maximum value when the current item is taken
                    if (rodLength <= cap)
                        taken = price[ind] + dp[ind][cap - rodLength];

                    // Store the result in the dp array
                    dp[ind][cap] = Math.max(notTaken, taken);
                }
            }

            return dp[n - 1][n]; // Return the maximum value that can be obtained
        }

        //Both are same i means same as above
        //https://www.youtube.com/watch?v=IRwVmTmN6go, but practive takeyouforward solution , optimization2DP to 1 DP
        public static int cutRodRedableCode(int price[], int n) {
            int R = n;  // Number of different rod lengths (0-based index)
            int C = n + 1;  // Rod length from 0 to n
            int[][] dp = new int[R][C];

            // Initialize base case: Only using length 1 rods (first row)
            for (int j = 0; j < C; j++) {
                dp[0][j] = price[0] * j;  // Fill first row
            }

            // Fill DP table
            for (int i = 1; i < R; i++) {
                for (int j = 0; j < C; j++) { // j is the rod length
                    int notTake = dp[i - 1][j]; // Exclude current length
                    int take = 0;
                    if (j >= (i + 1)) { // If we can include length i+1
                        take = price[i] + dp[i][j - (i + 1)];
                    }
                    dp[i][j] = Math.max(notTake, take);
                }
            }
            return dp[R - 1][C - 1]; // Maximum profit
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*N+1)

            Reason: There are two nested loops.

            Space Complexity: O(N+1)

            Reason: We are using an external array of size ‘W+1’ to store only one row.
         */

        // Function to solve the unbounded knapsack problem
        static int cutRodSpaceOptimized(int n, int[] price) {
            // Create a 2D array to store results of subproblems
            int[] pre = new int [n + 1];
            int[] cur = new int [n + 1];

            // Base condition: Initialize the dp array for the first item
            for (int N = 0; N <= n; N++) {
                pre[N] = (N / 1) * price[0];
            }

            // Fill the dp array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int cap = 0; cap <= n; cap++) {
                    // Calculate the maximum value when the current item is not taken
                    int notTaken = 0 + pre[cap];

                    // Initialize the maximum value when the current item is taken as the minimum integer value
                    int taken = Integer.MIN_VALUE;
                    int rodLength = ind + 1;
                    // If the weight of the current item is less than or equal to the current capacity (cap),
                    // calculate the maximum value when the current item is taken
                    if (rodLength <= cap)
                        taken = price[ind] + cur[cap - rodLength];

                    // Store the result in the dp array
                    cur[cap] = Math.max(notTaken, taken);
                }
                pre = cur;
            }

            return pre[n]; // Return the maximum value that can be obtained
        }


        public static void main(String args[]) {
            int wt[] = { 2, 4, 6 };
            int val[] = { 5, 11, 13 };
            int W = 10;

            int n = wt.length;

            // Call the unboundedKnapsack function and print the result
            //System.out.println("The Maximum value of items, the thief can steal is " + sol.unboundedKnapsack(n, W, val, wt));
        }
    }
}
