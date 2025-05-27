package com.interview.dynamic.PartFSubsequencesBasedProblems;

import java.util.Arrays;

public class K_UnboundedKnapsack {
        /*
    DP 23. Unbounded Knapsack | 1-D Array Space Optimised Approach
    https://www.youtube.com/watch?v=OgvOZ6OrJoY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=24
     */

    public static class UnboundedKnapsack {
        /*
        Complexity Analysis
        Time Complexity: O(N*W)

        Reason: There are N*W states therefore at max ‘N*W’ new problems will be solved.

        Space Complexity: O(N*W) + O(N)

        Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*W)).
         */
        // Recursive function to solve the unbounded knapsack problem
        static int knapsackUtil(int[] wt, int[] val, int ind, int W, int[][] dp) {
            // Base case: If there are no more items to consider
            if (ind == 0) {
                // Calculate and return the maximum value possible
                return ((int) (W / wt[0])) * val[0];
            }

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind][W] != -1)
                return dp[ind][W];

            // Calculate the maximum value when the current item is not taken
            int notTaken = 0 + knapsackUtil(wt, val, ind - 1, W, dp);

            // Initialize the maximum value when the current item is taken as the minimum integer value
            int taken = Integer.MIN_VALUE;

            // If the weight of the current item is less than or equal to the available capacity (W),
            // calculate the maximum value when the current item is taken
            if (wt[ind] <= W)
                taken = val[ind] + knapsackUtil(wt, val, ind, W - wt[ind], dp);

            // Store the result in the dp array and return it
            return dp[ind][W] = Math.max(notTaken, taken);
        }

        // Function to find the maximum value of items that the thief can steal
        static int unboundedKnapsack(int n, int W, int[] val, int[] wt) {
            // Create a 2D array to store results of subproblems
            int[][] dp = new int[n][W + 1];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the knapsackUtil function to solve the problem
            return knapsackUtil(wt, val, n - 1, W, dp);
        }

        /*
        Time Complexity: O(N*W)

        Reason: There are two nested loops

        Space Complexity: O(N*W)

        Reason: We are using an external array of size ‘N*W’. Stack Space is eliminated.
         */

        // Function to solve the unbounded knapsack problem
        static int unboundedKnapsackTabulation(int n, int W, int[] val, int[] wt) {
            // Create a 2D array to store results of subproblems
            int[][] dp = new int[n][W + 1];

            // Base condition: Initialize the dp array for the first item
            for (int i = wt[0]; i <= W; i++) {
                dp[0][i] = ((int) i / wt[0]) * val[0];
            }

            // Fill the dp array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int cap = 0; cap <= W; cap++) {
                    // Calculate the maximum value when the current item is not taken
                    int notTaken = 0 + dp[ind - 1][cap];

                    // Initialize the maximum value when the current item is taken as the minimum integer value
                    int taken = Integer.MIN_VALUE;

                    // If the weight of the current item is less than or equal to the current capacity (cap),
                    // calculate the maximum value when the current item is taken
                    if (wt[ind] <= cap)
                        taken = val[ind] + dp[ind][cap - wt[ind]];

                    // Store the result in the dp array
                    dp[ind][cap] = Math.max(notTaken, taken);
                }
            }

            return dp[n - 1][W]; // Return the maximum value that can be obtained
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*W)

            Reason: There are two nested loops.

            Space Complexity: O(W)

            Reason: We are using an external array of size ‘W+1’ to store only one row.
         */
        // Function to solve the unbounded knapsack problem
        static int unboundedKnapsackSpaceOptimized(int n, int W, int[] val, int[] wt) {
            // Create an array to store the maximum value for each capacity from 0 to W
            int cur[] = new int[W + 1];

            // Base condition: Initialize the cur array for the first item
            for (int i = wt[0]; i <= W; i++) {
                cur[i] = ((int) i / wt[0]) * val[0];
            }

            // Fill the cur array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int cap = 0; cap <= W; cap++) {
                    // Calculate the maximum value when the current item is not taken
                    int notTaken = cur[cap];

                    // Initialize the maximum value when the current item is taken as the minimum integer value
                    int taken = Integer.MIN_VALUE;

                    // If the weight of the current item is less than or equal to the current capacity (cap),
                    // calculate the maximum value when the current item is taken
                    if (wt[ind] <= cap)
                        taken = val[ind] + cur[cap - wt[ind]];

                    // Store the result in the cur array
                    cur[cap] = Math.max(notTaken, taken);
                }
            }

            return cur[W]; // Return the maximum value that can be obtained with the given capacity W
        }

        public static void main(String args[]) {
            int wt[] = { 2, 4, 6 };
            int val[] = { 5, 11, 13 };
            int W = 10;

            int n = wt.length;

            // Call the unboundedKnapsack function and print the result
            System.out.println("The Maximum value of items, the thief can steal is " + unboundedKnapsack(n, W, val, wt));
        }
    }
}
