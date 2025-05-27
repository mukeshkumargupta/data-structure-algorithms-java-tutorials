package com.interview.dynamic.PartFSubsequencesBasedProblems;

import java.util.Arrays;

public class G_ZeroOneKnapsack {
    /*
 https://www.youtube.com/watch?v=GqOmJHQZivw&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=20
 Category: Medium
 */
    public static class ZeroOneKnapsack {
        /*
            Time Complexity: O(N*W)

            Reason: There are N*W states therefore at max ‘N*W’ new problems will be solved.

            Space Complexity: O(N*W) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*W)).
         */
        // Helper function to solve the knapsack problem recursively
        static int knapsackUtil(int[] wt, int[] val, int ind, int W, int[][] dp) {
            // Base case: If there are no items or the knapsack capacity is zero
            if (ind == 0) {
                if (wt[0] <= W) {
                    // Include the item if its weight is within the capacity
                    return val[0];
                } else {
                    // Otherwise, exclude the item
                    return 0;
                }
            }

            // If the result for this subproblem is already calculated, return it
            if (dp[ind][W] != -1) {
                return dp[ind][W];
            }

            // Calculate the maximum value when the current item is not taken
            int notTaken = 0 + knapsackUtil(wt, val, ind - 1, W, dp);

            // Calculate the maximum value when the current item is taken
            int taken = Integer.MIN_VALUE;
            if (wt[ind] <= W) {
                taken = val[ind] + knapsackUtil(wt, val, ind - 1, W - wt[ind], dp);
            }

            // Store and return the result for the current state
            dp[ind][W] = Math.max(notTaken, taken);
            return dp[ind][W];
        }

        // Function to solve the 0/1 Knapsack problem using dynamic programming
        static int knapsack(int[] wt, int[] val, int n, int W) {
            // Create a 2D DP array to store the maximum value for each subproblem
            int dp[][] = new int[n][W + 1];

            // Initialize the DP array with -1 to indicate that subproblems are not solved
            for (int row[] : dp) {
                Arrays.fill(row, -1);
            }

            // Call the recursive knapsackUtil function to solve the problem
            return knapsackUtil(wt, val, n - 1, W, dp);
        }

        /*
                 https://www.youtube.com/watch?v=8LusJS5-AGo Tishar video  looks intutive to me
            Time Complexity: O(N*W)

            Reason: There are two nested loops

            Space Complexity: O(N*W)

            Reason: We are using an external array of size ‘N*W’. Stack Space is eliminated.
         */

        // Function to solve the 0/1 Knapsack problem using dynamic programming
        static int knapsackTabulation(int[] wt, int[] val, int n, int W) {//Prefer this
            // Create a 2D DP array to store the maximum value for each subproblem
            int dp[][] = new int[n][W + 1];

            // Base Condition
            for (int i = wt[0]; i <= W; i++) {
                dp[0][i] = val[0];
            }

            for (int ind = 1; ind < n; ind++) {
                for (int cap = 0; cap <= W; cap++) {
                    // Calculate the maximum value when the current item is not taken
                    int notTaken = dp[ind - 1][cap];

                    // Calculate the maximum value when the current item is taken
                    int taken = Integer.MIN_VALUE;
                    if (wt[ind] <= cap) {
                        taken = val[ind] + dp[ind - 1][cap - wt[ind]];
                    }

                    // Store the maximum value for the current state
                    dp[ind][cap] = Math.max(notTaken, taken);
                }
            }

            // The result is stored in the last row and last column of the DP array
            return dp[n - 1][W];
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*W)

            Reason: There are two nested loops.

            Space Complexity: O(W)

            Reason: We are using an external array of size ‘W+1’ to store only one row.
         */

        // Function to solve the 0/1 Knapsack problem using dynamic programming
        static int knapsackSpaceOptimized(int[] wt, int[] val, int n, int W) {
            // Create an array to store the maximum value for each capacity (previous row)
            int prev[] = new int[W + 1];

            // Base Condition: Initialize the first row of the array
            for (int i = wt[0]; i <= W; i++) {
                prev[i] = val[0];
            }

            // Iterate through each item and capacity
            for (int ind = 1; ind < n; ind++) {
                for (int cap = W; cap >= 0; cap--) {
                    // Calculate the maximum value when the current item is not taken
                    int notTaken = prev[cap];

                    // Calculate the maximum value when the current item is taken
                    int taken = Integer.MIN_VALUE;
                    if (wt[ind] <= cap) {
                        taken = val[ind] + prev[cap - wt[ind]];
                    }

                    // Update the array with the maximum value for the current capacity
                    prev[cap] = Math.max(notTaken, taken);
                }
            }

            // The result is stored in the last element of the array
            return prev[W];
        }


        public static void main(String args[]) {
            int wt[] = {1, 2, 4, 5};
            int val[] = {5, 4, 8, 6};
            int W = 5;
            int n = wt.length;

            // Calculate and print the maximum value of items the thief can steal
            System.out.println("The Maximum value of items the thief can steal is " + knapsack(wt, val, n, W));
        }
    }
}
