package com.interview.dynamic.PartFSubsequencesBasedProblems;

import java.util.Arrays;

public class H_MinimumCoins {
    /*
        Leetcode: https://leetcode.com/problems/coin-change/
     *
     * Related: https://leetcode.com/problems/minimum-cost-for-tickets/ Medium
     *
     * References:
     * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
     * Category: Must Do, Medium
     * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

    Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

    You may assume that you have an infinite number of each kind of coin.



    Example 1:

    Input: coins = [1,2,5], amount = 11
    Output: 3
    Explanation: 11 = 5 + 5 + 1
    Example 2:

    Input: coins = [2], amount = 3
    Output: -1
    Example 3:

    Input: coins = [1], amount = 0
    Output: 0
    Example 4:

    Input: coins = [1], amount = 1
    Output: 1
    Example 5:

    Input: coins = [1], amount = 2
    Output: 2
    DP 20. Minimum Coins | DP on Subsequences | Infinite Supplies Pattern, Must Do, VVImp Time complexity little different due to infinite supply of coin
 */
    public static class MinimumCoins {
        /*

           Time Complexity: O(N*T)

            Reason: There are N*T states therefore at max ‘N*T’ new problems will be solved.

            Space Complexity: O(N*T) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*T)).
         */
        // Recursive function to find the minimum number of elements to achieve the target sum
        static int minimumElementsUtil(int[] arr, int ind, int T, int[][] dp) {
            // Base case: If the current index is 0
            if (ind == 0) {
                // If T is divisible by the first element of the array, return the quotient
                if (T % arr[0] == 0)
                    return T / arr[0];
                else
                    // If not, return a large value to indicate it's not possible
                    return (int) Math.pow(10, 9);
            }

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind][T] != -1)
                return dp[ind][T];

            // Calculate the minimum number of elements needed without taking the current element
            int notTaken = 0 + minimumElementsUtil(arr, ind - 1, T, dp);

            // Initialize the minimum number of elements needed taking the current element
            int taken = (int) Math.pow(10, 9);

            // If the current element is less than or equal to T, calculate the minimum taking it
            if (arr[ind] <= T)
                taken = 1 + minimumElementsUtil(arr, ind, T - arr[ind], dp);

            // Store the minimum result in the dp array and return it
            return dp[ind][T] = Math.min(notTaken, taken);
        }

        // Function to find the minimum number of elements to achieve the target sum
        static int minimumElements(int[] arr, int T) {
            int n = arr.length;

            // Create a 2D array to store results of subproblems
            int[][] dp = new int[n][T + 1];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Calculate the minimum number of elements to achieve the target sum
            int ans = minimumElementsUtil(arr, n - 1, T, dp);

            // If it's not possible to achieve the target sum, return -1
            if (ans >= (int) Math.pow(10, 9))
                return -1;
            return ans;
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*T)

            Reason: There are two nested loops

            Space Complexity: O(N*T)

            Reason: We are using an external array of size ‘N*T’. Stack Space is eliminated.
         */
        // Function to find the minimum number of elements to achieve the target sum
        static int minimumElementsTabulation(int[] arr, int T) {
            int n = arr.length;

            // Create a 2D array to store results of subproblems
            int dp[][] = new int[n][T + 1];

            // Initialize the dp array for the first element of the array
            for (int i = 0; i <= T; i++) {
                if (i % arr[0] == 0)
                    dp[0][i] = i / arr[0];
                else
                    dp[0][i] = (int) Math.pow(10, 9);
            }

            // Fill the dp array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int target = 0; target <= T; target++) {
                    int notTake = 0 + dp[ind - 1][target];
                    int take = (int) Math.pow(10, 9);

                    // If the current element is less than or equal to the target, calculate 'take'
                    if (arr[ind] <= target)
                        take = 1 + dp[ind][target - arr[ind]];

                    // Store the minimum result in the dp array
                    dp[ind][target] = Math.min(notTake, take);
                }
            }

            // Get the minimum number of elements needed for the target sum
            int ans = dp[n - 1][T];

            // If it's not possible to achieve the target sum, return -1
            if (ans >= (int) Math.pow(10, 9))
                return -1;
            return ans;
        }

        /*
        Time Complexity: O(N*T)

        Reason: There are two nested loops.

        Space Complexity: O(T)

        Reason: We are using two external arrays of size ‘T+1’.
         */

        // Function to find the minimum number of elements to achieve the target sum
        static int minimumElementsSpaceOptimized(int[] arr, int T) {
            int n = arr.length;

            // Create two arrays to store results of subproblems: prev and cur
            int prev[] = new int[T + 1];
            int cur[] = new int[T + 1];

            // Initialize the prev array for the first element of the array
            for (int i = 0; i <= T; i++) {
                if (i % arr[0] == 0)
                    prev[i] = i / arr[0];
                else
                    prev[i] = (int) Math.pow(10, 9);
            }

            // Fill the cur array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int target = 0; target <= T; target++) {
                    int notTake = 0 + prev[target];
                    int take = (int) Math.pow(10, 9);

                    // If the current element is less than or equal to the target, calculate 'take'
                    if (arr[ind] <= target)
                        take = 1 + cur[target - arr[ind]];

                    // Store the minimum result in the cur array
                    cur[target] = Math.min(notTake, take);
                }

                // Update prev with cur for the next iteration
                prev = cur.clone();
            }

            // Get the minimum number of elements needed for the target sum
            int ans = prev[T];

            // If it's not possible to achieve the target sum, return -1
            if (ans >= (int) Math.pow(10, 9))
                return -1;
            return ans;
        }


        public static void main(String args[]) {
            int arr[] = { 1, 2, 3 };
            int T = 7;

            // Call the minimumElements function and print the result
            System.out.println("The minimum number of coins required to form the target sum is " + minimumElements(arr, T));
        }
    }
}
