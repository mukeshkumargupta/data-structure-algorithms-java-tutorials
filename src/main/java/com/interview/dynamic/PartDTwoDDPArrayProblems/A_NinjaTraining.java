package com.interview.dynamic.PartDTwoDDPArrayProblems;

import java.util.Arrays;

/*
https://www.youtube.com/watch?v=AE39gJYuRog&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=8
DP 7. Ninja's Training | MUST WATCH for 2D CONCEPTS 🔥 | Vacation | Atcoder | 2D DP |
Category: Medium
 */
public class A_NinjaTraining {
    /*
    Time Complexity: O(N*4*3)

Reason: There are N*4 states and for every state, we are running a for loop iterating three times.

Space Complexity: O(N) + O(N*4)

Reason: We are using a recursion stack space(O(N)) and a 2D array (again O(N*4)). Therefore total space complexity will be O(N) + O(N) ≈ O(N)
     */
    // Recursive function to calculate the maximum points for the ninja training
    static int solveUtilRecursive(int day, int last, int[][] points, int[][] dp) {
        // If the result is already calculated, return it
        if (dp[day][last] != -1) return dp[day][last];

        // Base case: When it's the first day (day == 0)
        if (day == 0) {
            int maxi = 0;
            for (int i = 0; i <= 2; i++) {
                if (i != last)
                    maxi = Math.max(maxi, points[0][i]);
            }
            return dp[day][last] = maxi; // Store and return the result
        }

        int maxi = 0;
        // Loop through the three activities on the current day
        for (int i = 0; i <= 2; i++) {
            if (i != last) {
                // Calculate the points for the current activity and recursively
                // calculate the maximum points for the previous day
                int activity = points[day][i] + solveUtilRecursive(day - 1, i, points, dp);
                maxi = Math.max(maxi, activity); // Update the maximum points
            }
        }

        return dp[day][last] = maxi; // Store and return the result
    }

    // Function to find the maximum points for ninja training
    static int solveRecursive(int n, int[][] points) {
        // Initialize a memoization table with -1 values
        int dp[][] = new int[n][4];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Start the recursive calculation from the last day (n - 1) with the last activity (3)
        return solveUtilRecursive(n - 1, 3, points, dp);
    }

    /*
    Time Complexity: O(N*4*3)

Reason: There are three nested loops

Space Complexity: O(N*4)

Reason: We are using an external array of size ‘N*4’’.
     */
    // Function to find the maximum points for ninja training
    static int solveBottomUp(int n, int[][] points) {
        // Initialize a 2D array 'dp' to store the maximum points
        int[][] dp = new int[n][4];

        // Initialize the first day's maximum points based on the available choices
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        // Iterate through each day and each activity
        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0; // Initialize the maximum points for the current day and last activity
                // Consider each possible task for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != last) { // Ensure that the current task is different from the last
                        // Calculate the points for the current activity and add it to the maximum points from the previous day
                        int activity = points[day][task] + dp[day - 1][task];
                        // Update the maximum points for the current day and last activity
                        dp[day][last] = Math.max(dp[day][last], activity);
                    }
                }
            }
        }

        // Return the maximum points achievable after all days (last activity is 3)
        return dp[n - 1][3];
    }

    /*
        Time Complexity: O(N*4*3)

        Reason: There are three nested loops

        Space Complexity: O(4)

        Reason: We are using an external array of size ‘4’ to store only one row.
     */
    // Function to find the maximum points for ninja training
    static int solveBottomUpSpaceOptimized(int n, int[][] points) {
        // Initialize an array 'prev' to store the maximum points for the previous day
        int prev[] = new int[4];

        // Initialize the first day's maximum points based on the available choices
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        // Iterate through each day starting from the second day
        for (int day = 1; day < n; day++) {
            // Initialize an array 'temp' to store the maximum points for the current day
            int temp[] = new int[4];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0; // Initialize the maximum points for the current day and last activity
                // Consider each possible task for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != last) { // Ensure that the current task is different from the last
                        // Calculate the points for the current activity and add it to the maximum points from the previous day
                        temp[last] = Math.max(temp[last], points[day][task] + prev[task]);
                    }
                }
            }
            // Update 'prev' to store the maximum points for the current day
            prev = temp;
        }

        // Return the maximum points achievable after all days (last activity is 3)
        return prev[3];
    }

    public static void main(String args[]) {
        // Define the points for each activity on each day
        int[][] points = {{10, 40, 70},
                {20, 50, 80},
                {30, 60, 90}};

        int n = points.length; // Get the number of days
        System.out.println(solveRecursive(n, points)); // Calculate and print the maximum points
        System.out.println(solveBottomUp(n, points)); // Calculate and print the maximum points
        System.out.println(solveBottomUpSpaceOptimized(n, points)); // Calculate and print the maximum points

    }
}
