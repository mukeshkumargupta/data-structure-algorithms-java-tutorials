package com.interview.dynamic.PartFSubsequencesBasedProblems;

import java.util.ArrayList;
import java.util.Arrays;

public class D_PartitionASetIntoTwoSubsetsWithMinimumAbsoluteSumDifference {
        /*
        Video: https://www.youtube.com/watch?v=GS_OqZb2CWc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=17
        https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/
        https://www.naukri.com/code360/problems/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum_842494
        Category: Hard
     */

    /*
        Time Complexity: O(N*totSum) +O(N) +O(N)

        Reason: There are two nested loops that account for O(N*totSum), at starting we are running a for loop to calculate totSum and at last a for loop to traverse the last row.

        Space Complexity: O(N*totSum) + O(N)

        Reason: We are using an external array of size ‘N * totSum’ and a stack space of O(N).
     */
    public static class PartitionASetIntoTwoSubsetsWithMinimumAbsoluteSumDifference {
        // Helper function to calculate the minimum absolute difference of two subsets
        static int minSubsetSumDifference(ArrayList<Integer> arr, int n) {
            int totSum = 0;

            // Calculate the total sum of the array elements
            for (int i = 0; i < n; i++) {
                totSum += arr.get(i);
            }

            // Create a DP table to store subset sum information
            boolean[][] dp = new boolean[n][totSum + 1];

            // Initialize the DP table for the first row
            for (int i = 0; i <= totSum; i++) {
                dp[0][i] = (i == arr.get(0));
            }

            // Fill in the DP table using bottom-up dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int target = 0; target <= totSum; target++) {
                    // Calculate if the current element is not taken
                    boolean notTaken = dp[ind - 1][target];

                    // Calculate if the current element is taken
                    boolean taken = false;
                    if (arr.get(ind) <= target) {
                        taken = dp[ind - 1][target - arr.get(ind)];
                    }

                    // Update the DP table for the current element and target sum
                    dp[ind][target] = notTaken || taken;
                }
            }

            int mini = Integer.MAX_VALUE;

            // Find the minimum absolute difference between two subsets
            for (int i = 0; i <= totSum; i++) {
                if (dp[n - 1][i]) {
                    int diff = Math.abs(i - (totSum - i));
                    mini = Math.min(mini, diff);
                }
            }
            return mini;
        }

        /*
            Time Complexity: O(N*totSum) +O(N) +O(N)

            Reason: There are two nested loops that account for O(N*totSum), at starting we are running a for loop to calculate totSum and at last a for loop to traverse the last row.

            Space Complexity: O(totSum)

            Reason: We are using an external array of size ‘totSum+1’ to store only one row.
         */

        // Function to calculate the minimum absolute difference of two subsets
        static int minSubsetSumDifferenceSpaceOptimized(ArrayList<Integer> arr, int n) {
            int totSum = 0;

            // Calculate the total sum of the array elements
            for (int i = 0; i < n; i++) {
                totSum += arr.get(i);
            }

            // Create an array to store DP results for the previous row
            boolean[] prev = new boolean[totSum + 1];

            // Initialize the DP array for the first row
            prev[0] = true;

            // Initialize the DP array for the first column
            if (arr.get(0) <= totSum) {
                prev[arr.get(0)] = true;
            }

            // Fill in the DP array using bottom-up dynamic programming
            for (int ind = 1; ind < n; ind++) {
                // Create an array to store DP results for the current row
                boolean[] cur = new boolean[totSum + 1];
                cur[0] = true;
                for (int target = 1; target <= totSum; target++) {
                    // Calculate if the current element is not taken
                    boolean notTaken = prev[target];

                    // Calculate if the current element is taken
                    boolean taken = false;
                    if (arr.get(ind) <= target) {
                        taken = prev[target - arr.get(ind)];
                    }

                    // Update the DP array for the current element and target sum
                    cur[target] = notTaken || taken;
                }
                prev = cur;
            }

            int mini = Integer.MAX_VALUE;

            // Find the minimum absolute difference between two subsets
            for (int i = 0; i <= totSum; i++) {
                if (prev[i]) {
                    int diff = Math.abs(i - (totSum - i));
                    mini = Math.min(mini, diff);
                }
            }
            return mini;
        }

        public static void main(String[] args) {
            ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
            int n = arr.size();

            // Calculate and print the minimum absolute difference
            System.out.println("The minimum absolute difference is: " + minSubsetSumDifference(arr, n));
        }
    }
}
