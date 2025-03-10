package com.interview.dynamic;

import java.util.ArrayList;
import java.util.Arrays;

public class PartFSubsequencesBasedProblem {
    /*
        Mainly three type of problem: isPossible so use || operator, then count where use + operator and then min or max then use min or max, in case of min, base case will with Max(Remember to avoid overflow use 10 power 9
        Video: DP 14. Subset Sum Equals to Target | Identify DP on Subsequences and Ways to Solve them
        Problem link: https://www.naukri.com/code360/problems/subset-sum-equal-to-k_1550954
        Category: Medium, 2D DP, Sunset pattern
     */
    public static class SubsetSumEqualToTarget {
        /*
        Time Complexity: O(N*K)

        Reason: There are N*K states therefore at max ‘N*K’ new problems will be solved.

        Space Complexity: O(N*K) + O(N)

        Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).
         */
        // Helper function to solve subset sum problem using dynamic programming
        static boolean subsetSumUtil(int ind, int target, int[] arr, int[][] dp) {
            // If the target sum is achieved, return true
            if (target == 0)
                return true;

            // If we have considered all elements but haven't reached the target, return false
            if (ind == 0)
                return arr[0] == target;

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind][target] != -1)
                return dp[ind][target] == 0 ? false : true;

            // Try not taking the current element
            boolean notTaken = subsetSumUtil(ind - 1, target, arr, dp);

            // Try taking the current element if it doesn't exceed the target
            boolean taken = false;
            if (arr[ind] <= target)
                taken = subsetSumUtil(ind - 1, target - arr[ind], arr, dp);

            // Store the result in the DP table and return whether either option was successful
            dp[ind][target] = notTaken || taken ? 1 : 0;
            return notTaken || taken;
        }

        // Main function to check if there exists a subset with a given target sum
        static boolean subsetSumToK(int n, int k, int[] arr) {
            // Create a DP table with dimensions [n][k+1]
            int dp[][] = new int[n][k + 1];

            // Initialize DP table with -1 (unprocessed)
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the recursive helper function
            return subsetSumUtil(n - 1, k, arr, dp);
        }

        /*
            Time Complexity: O(N*K)

            Reason: There are two nested loops

            Space Complexity: O(N*K)

            Reason: We are using an external array of size ‘N*K’. Stack Space is eliminated.
            https://www.youtube.com/watch?v=s6FhG--P7z0
         */
        // Function to check if there exists a subset with a given target sum
        static boolean subsetSumToKTabulation(int n, int k, int[] arr) {
            // Create a boolean DP table with dimensions [n][k+1]
            boolean dp[][] = new boolean[n][k + 1];

            // Initialize the first row of the DP table
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }

            // Initialize the first column of the DP table
            if (arr[0] <= k) {
                dp[0][arr[0]] = true;
            }

            // Fill in the DP table using bottom-up approach
            for (int ind = 1; ind < n; ind++) {
                for (int target = 1; target <= k; target++) {
                    // Calculate if the current target can be achieved without taking the current element
                    boolean notTaken = dp[ind - 1][target];

                    // Calculate if the current target can be achieved by taking the current element
                    boolean taken = false;
                    if (arr[ind] <= target) {
                        taken = dp[ind - 1][target - arr[ind]];
                    }

                    // Store the result in the DP table
                    dp[ind][target] = notTaken || taken;
                }
            }

            // The final result is stored in the bottom-right cell of the DP table
            return dp[n - 1][k];
        }

        /*
            Time Complexity: O(N*K)

            Reason: There are two nested loops

            Space Complexity: O(K)

            Reason: We are using an external array of size ‘K+1’ to store only one row.
         */
        // Function to check if there exists a subset with a given target sum
        static boolean subsetSumToKSpaceOptimized(int n, int k, int[] arr) {
            // Create an array to store the previous row of the DP table
            boolean prev[] = new boolean[k + 1];

            // Initialize the first row of the DP table
            prev[0] = true;

            // Initialize the first column of the DP table
            if (arr[0] <= k) {
                prev[arr[0]] = true;
            }

            // Fill in the DP table using bottom-up approach
            for (int ind = 1; ind < n; ind++) {
                // Create an array to store the current row of the DP table
                boolean cur[] = new boolean[k + 1];

                // Initialize the first column of the current row
                cur[0] = true;

                for (int target = 1; target <= k; target++) {
                    // Calculate if the current target can be achieved without taking the current element
                    boolean notTaken = prev[target];

                    // Calculate if the current target can be achieved by taking the current element
                    boolean taken = false;
                    if (arr[ind] <= target) {
                        taken = prev[target - arr[ind]];
                    }

                    // Store the result in the current row of the DP table
                    cur[target] = notTaken || taken;
                }

                // Update the previous row with the current row
                prev = cur;
            }

            // The final result is stored in the last cell of the previous row
            return prev[k];
        }


        public static void main(String args[]) {
            int arr[] = { 1, 2, 3, 4 };
            int k = 4;
            int n = arr.length;

            // Check if there exists a subset with the given target sum
            if (subsetSumToK(n, k, arr))
                System.out.println("Subset with the given target found");
            else
                System.out.println("Subset with the given target not found");
        }
    }

    /*
        https://www.youtube.com/watch?v=7win3dcgo3k&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=16

     */
    public static class PartitionEqualSubsetSum {
        /*
            Category: Medium, Subset pattern on DP
            https://leetcode.com/problems/partition-equal-subset-sum/description/
            https://www.youtube.com/watch?v=7win3dcgo3k&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=16
            Time Complexity: O(N*K) + O(N)

            Reason: There are N*K states therefore at max ‘N*K’ new problems will be solved and we are running a for loop for ‘N’ times to calculate the total sum

            Space Complexity: O(N*K) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).
    */
        // Helper function to check if there exists a subset with a given sum
        static boolean subsetSumUtil(int ind, int target, int arr[], int[][] dp) {
            // If the target sum is 0, we have found a valid subset
            if (target == 0)
                return true;

            // If we have processed all elements in the array
            if (ind == 0)
                return arr[0] == target;

            // If this subproblem has already been solved, return the result
            if (dp[ind][target] != -1)
                return dp[ind][target] == 0 ? false : true;

            // Try not taking the current element into the subset
            boolean notTaken = subsetSumUtil(ind - 1, target, arr, dp);

            // Try taking the current element into the subset if it doesn't exceed the target
            boolean taken = false;
            if (arr[ind] <= target)
                taken = subsetSumUtil(ind - 1, target - arr[ind], arr, dp);

            // Memoize the result and return true if either choice results in a valid subset
            dp[ind][target] = notTaken || taken ? 1 : 0;
            return notTaken || taken;
        }


        // Main function to check if the array can be partitioned into two equal subsets
        static boolean canPartition(int n, int[] arr) {
            // Calculate the total sum of the array elements
            int totSum = 0;
            for (int i = 0; i < n; i++) {
                totSum += arr[i];
            }

            // If the total sum is odd, it cannot be partitioned into equal subsets
            if (totSum % 2 == 1)
                return false;
            else {
                // Calculate the target sum for each subset
                int k = totSum / 2;
                // Create a memoization table
                int dp[][] = new int[n][k + 1];
                for (int row[] : dp)
                    Arrays.fill(row, -1);
                // Call the helper function to check if a valid subset exists
                return subsetSumUtil(n - 1, k, arr, dp);
            }
        }

        /*
            Time Complexity: O(N*K) +O(N)

            Reason: There are two nested loops that account for O(N*K) and at starting we are running a for loop to calculate totSum.

            Space Complexity: O(N*K)

            Reason: We are using an external array of size ‘N*K’. Stack Space is eliminated.
         */
        // Function to check if it's possible to partition the array into two equal subsets
        static boolean canPartitionTabulation(int n, int[] arr) {
            // Calculate the total sum of the array elements
            int totSum = 0;
            for (int i = 0; i < n; i++) {
                totSum += arr[i];
            }

            // If the total sum is odd, it cannot be partitioned into equal subsets
            if (totSum % 2 == 1)
                return false;
            else {
                // Calculate the target sum for each subset
                int k = totSum / 2;
                // Create a DP table to store the results of subproblems
                boolean dp[][] = new boolean[n][k + 1];

                // Initialize the first row of the DP table
                for (int i = 0; i < n; i++) {
                    dp[i][0] = true;
                }

                // Initialize the first column of the DP table
                if (arr[0] <= k) {
                    dp[0][arr[0]] = true;
                }

                // Fill in the DP table using bottom-up dynamic programming
                for (int ind = 1; ind < n; ind++) {
                    for (int target = 1; target <= k; target++) {
                        // Calculate if the current element is not taken
                        boolean notTaken = dp[ind - 1][target];

                        // Calculate if the current element is taken
                        boolean taken = false;
                        if (arr[ind] <= target) {
                            taken = dp[ind - 1][target - arr[ind]];
                        }

                        // Update the DP table for the current element and target sum
                        dp[ind][target] = notTaken || taken;
                    }
                }

                // The result is stored in the last cell of the DP table
                return dp[n - 1][k];
            }
        }

        /*
            Time Complexity: O(N*K) +O(N)

            Reason: There are two nested loops that account for O(N*K) and at starting we are running a for loop to calculate totSum.

            Space Complexity: O(K)

            Reason: We are using an external array of size ‘K+1’ to store only one row.
         */

        // Function to check if it's possible to partition the array into two equal subsets
        static boolean canPartitionSpaceOptimized(int n, int[] arr) {
            // Calculate the total sum of the array elements
            int totSum = 0;
            for (int i = 0; i < n; i++) {
                totSum += arr[i];
            }

            // If the total sum is odd, it cannot be partitioned into equal subsets
            if (totSum % 2 == 1)
                return false;
            else {
                // Calculate the target sum for each subset
                int k = totSum / 2;
                // Create two arrays to store the DP results for the previous and current rows
                boolean prev[] = new boolean[k + 1];

                // Initialize the first row of the DP table
                prev[0] = true;

                // Initialize the first column of the DP table
                if (arr[0] <= k) {
                    prev[arr[0]] = true;
                }

                // Fill in the DP table using bottom-up dynamic programming
                for (int ind = 1; ind < n; ind++) {
                    boolean cur[] = new boolean[k + 1];
                    cur[0] = true;
                    for (int target = 1; target <= k; target++) {
                        // Calculate if the current element is not taken
                        boolean notTaken = prev[target];

                        // Calculate if the current element is taken
                        boolean taken = false;
                        if (arr[ind] <= target) {
                            taken = prev[target - arr[ind]];
                        }

                        // Update the DP table for the current element and target sum
                        cur[target] = notTaken || taken;
                    }
                    // Update the previous row with the current row for the next iteration
                    prev = cur;
                }

                // The result is stored in the last cell of the DP table
                return prev[k];
            }
        }

        public static void main(String args[]) {
            int arr[] = {2, 3, 3, 3, 4, 5};
            int n = arr.length;

            // Check if the array can be partitioned into two equal subsets
            if (canPartition(n, arr))
                System.out.println("The Array can be partitioned into two equal subsets");
            else
                System.out.println("The Array cannot be partitioned into two equal subsets");
        }
    }

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

    /*
    https://www.youtube.com/watch?v=ZHyb-A2Mte4&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=18
    Category: Hard
     */
    public static class CountsSubsetswithSumK {
        /*
        Time Complexity: O(N*K)

        Reason: There are N*K states therefore at max ‘N*K’ new problems will be solved.

        Space Complexity: O(N*K) + O(N)

        Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).
         */
        // Helper function to find the number of ways to achieve a target sum
        static int findWaysUtil(int ind, int target, int[] arr, int[][] dp) {
            if (target == 0)
                return 1;

            if (ind == 0)
                return arr[0] == target ? 1 : 0;

            if (dp[ind][target] != -1)
                return dp[ind][target];

            // Calculate the number of ways when the current element is not taken
            int notTaken = findWaysUtil(ind - 1, target, arr, dp);

            // Calculate the number of ways when the current element is taken
            int taken = 0;
            if (arr[ind] <= target)
                taken = findWaysUtil(ind - 1, target - arr[ind], arr, dp);

            // Store and return the result for the current state
            return dp[ind][target] = notTaken + taken;
        }

        // Main function to find the number of ways to form subsets with a target sum
        static int findWays(int[] num, int k) {
            int n = num.length;
            int dp[][] = new int[n][k + 1];

            for (int row[] : dp)
                Arrays.fill(row, -1);

            return findWaysUtil(n - 1, k, num, dp);
        }

        /*
        Time Complexity: O(N*K)

        Reason: There are two nested loops

        Space Complexity: O(N*K)

        Reason: We are using an external array of size ‘N*K’. Stack Space is eliminated.
         */
        // Function to find the number of subsets with a given target sum
        static int findWaysTabulation(int[] num, int k) {
            int n = num.length;

            // Create a 2D DP array to store the number of ways to achieve each target sum
            int[][] dp = new int[n][k + 1];

            // Initialize the first column of the DP array
            for (int i = 0; i < n; i++) {
                dp[i][0] = 1;
            }

            // Initialize the first row of the DP array
            if (num[0] <= k) {
                dp[0][num[0]] = 1;
            }

            // Fill in the DP array using bottom-up dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int target = 1; target <= k; target++) {
                    // Calculate the number of ways when the current element is not taken
                    int notTaken = dp[ind - 1][target];

                    // Calculate the number of ways when the current element is taken
                    int taken = 0;
                    if (num[ind] <= target) {
                        taken = dp[ind - 1][target - num[ind]];
                    }

                    // Update the DP array for the current element and target sum
                    dp[ind][target] = notTaken + taken;
                }
            }

            // The result is stored in the last cell of the DP array
            return dp[n - 1][k];
        }

        /*
            Time Complexity: O(N*K)

            Reason: There are two nested loops

            Space Complexity: O(K)

            Reason: We are using an external array of size ‘K+1’ to store only one row.
         */
        // Function to find the number of subsets with a given target sum
        static int findWaysSpaceOptimized(int[] num, int k) {
            int n = num.length;

            // Create an array to store the number of ways to achieve each target sum
            int[] prev = new int[k + 1];

            // Initialize the first element of the array
            prev[0] = 1;

            // Initialize the array for the first column
            if (num[0] <= k) {
                prev[num[0]] = 1;
            }

            // Fill in the array using bottom-up dynamic programming
            for (int ind = 1; ind < n; ind++) {
                // Create an array to store the number of ways for the current element
                int[] cur = new int[k + 1];

                // Initialize the first element of the current array
                cur[0] = 1;

                for (int target = 1; target <= k; target++) {
                    // Calculate the number of ways when the current element is not taken
                    int notTaken = prev[target];

                    // Calculate the number of ways when the current element is taken
                    int taken = 0;
                    if (num[ind] <= target) {
                        taken = prev[target - num[ind]];
                    }

                    // Update the current array for the current element and target sum
                    cur[target] = notTaken + taken;
                }

                // Update the previous array with the current array for the next iteration
                prev = cur;
            }

            // The result is stored in the last element of the array
            return prev[k];
        }


        public static void main(String args[]) {
            int arr[] = {1, 2, 2, 3};
            int k = 3;

            // Calculate and print the number of subsets that sum up to k
            System.out.println("The number of subsets found are " + findWays(arr, k));
        }
    }

    /*
    https://www.youtube.com/watch?v=zoilQD1kYSg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=19
    Category: Hard, Trick base case, VVImp
     */

    public static class CountPartitionswithGivenDifference {
        static int mod =(int)(Math.pow(10,9)+7);
        /*
            The number of subsets found are 1

            Time Complexity: O(N*K)

            Reason: There are N*K states therefore at max ‘N*K’ new problems will be solved.

            Space Complexity: O(N*K) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).
         */
        static int countPartitionsUtil(int ind, int target,int[] arr, int[][] dp){

            if(ind == 0){
                if(target==0 && arr[0]==0)
                    return 2;
                if(target==0 || target == arr[0])
                    return 1;
                return 0;
            }

            if(dp[ind][target]!=-1)
                return dp[ind][target];

            int notTaken = countPartitionsUtil(ind-1,target,arr,dp);

            int taken = 0;
            if(arr[ind]<=target)
                taken = countPartitionsUtil(ind-1,target-arr[ind],arr,dp);

            return dp[ind][target]= (notTaken + taken)%mod;
        }

        static int countPartitions(int d,int[] arr){
            int n = arr.length;
            int totSum = 0;
            for(int i=0; i<arr.length;i++){
                totSum += arr[i];
            }

            //Checking for edge cases
            if(totSum-d<0) return 0;
            if((totSum-d)%2==1) return 0;

            int s2 = (totSum-d)/2;

            int dp[][] = new int[n][s2+1];

            for(int row[]: dp)
                Arrays.fill(row,-1);

            return countPartitionsUtil(n-1,s2,arr,dp);
        }

        /*
            The number of subsets found are 1

            Time Complexity: O(N*K)

            Reason: There are two nested loops

            Space Complexity: O(N*K)

            Reason: We are using an external array of size ‘N*K’. Stack Space is eliminated.
         */

        static int findWaysTabulation(int[] num, int tar){
            int n = num.length;

            int dp[][] = new int[n][tar+1];

            if(num[0] == 0) dp[0][0] =2;  // 2 cases -pick and not pick
            else dp[0][0] = 1;  // 1 case - not pick

            if(num[0]!=0 && num[0]<=tar) dp[0][num[0]] = 1;  // 1 case -pick

            for(int ind = 1; ind<n; ind++){
                for(int target= 0; target<=tar; target++){

                    int notTaken = dp[ind-1][target];

                    int taken = 0;
                    if(num[ind]<=target)
                        taken = dp[ind-1][target-num[ind]];

                    dp[ind][target]= (notTaken + taken)%mod;
                }
            }
            return dp[n-1][tar];
        }

        static int countPartitions(int n, int d,int[] arr){
            int totSum = 0;
            for(int i=0; i<n;i++){
                totSum += arr[i];
            }

            //Checking for edge cases
            if(totSum-d <0 || (totSum-d)%2==1 ) return 0;

            return findWaysTabulation(arr,(totSum-d)/2);
        }

        static int findWaysSpaceOptimized(int[] num, int tar){
            int n = num.length;

            int prev[] = new int[tar+1];

            if(num[0] == 0) prev[0] =2;  // 2 cases -pick and not pick
            else prev[0] = 1;  // 1 case - not pick

            if(num[0]!=0 && num[0]<=tar) prev[num[0]] = 1;  // 1 case -pick

            for(int ind = 1; ind<n; ind++){
                int cur[]=new int[tar+1];
                for(int target= 0; target<=tar; target++){
                    int notTaken = prev[target];

                    int taken = 0;
                    if(num[ind]<=target)
                        taken = prev[target-num[ind]];

                    cur[target]= (notTaken + taken)%mod;
                }
                prev = cur;
            }
            return prev[tar];
        }
        /*
            The number of subsets found are 1

            Time Complexity: O(N*K)

            Reason: There are three nested loops

            Space Complexity: O(K)

            Reason: We are using an external array of size ‘K+1’ to store only one row.
         */

        static int countPartitionsSpaceOptimized(int n, int d,int[] arr){
            int totSum = 0;
            for(int i=0; i<n;i++){
                totSum += arr[i];
            }

            //Checking for edge cases
            if(totSum-d <0 || (totSum-d)%2 ==1) return 0;

            return findWaysSpaceOptimized(arr,(totSum-d)/2);
        }


        public static void main(String args[]) {

            int arr[] = {5,2,6,4};
            int d=3;

            System.out.println("The number of subsets found are "+countPartitions(d,arr));
        }
    }

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
        static int knapsackTabulation(int[] wt, int[] val, int n, int W) {
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

    /*
        Count no of ways pattern based, including zero
        You are given an integer array nums and an integer target.
        Category: Medium, Top100, Must Do
                https://leetcode.com/problems/target-sum/description/ it is grouping problem (so group is done by plus or minus so u can do grouping by other was as well
        Related: https://leetcode.com/problems/expression-add-operators/description/ Hard
        https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/description/ Medium

        Video: https://www.youtube.com/watch?v=b3GD8263-PQ&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=22

        You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

        For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
        Return the number of different expressions that you can build, which evaluates to target.



        Example 1:

        Input: nums = [1,1,1,1,1], target = 3
        Output: 5
        Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
        -1 + 1 + 1 + 1 + 1 = 3
        +1 - 1 + 1 + 1 + 1 = 3
        +1 + 1 - 1 + 1 + 1 = 3
        +1 + 1 + 1 - 1 + 1 = 3
        +1 + 1 + 1 + 1 - 1 = 3
        Example 2:

        Input: nums = [1], target = 1
        Output: 1


        Constraints:

        1 <= nums.length <= 20
        0 <= nums[i] <= 1000
        0 <= sum(nums[i]) <= 1000
        -1000 <= target <= 1000
     */

    public static class TargetSum {
        /*
            Complexity Analysis
            Time Complexity: O(N*K)

            Reason: There are N*K states therefore at max ‘N*K’ new problems will be solved.

            Space Complexity: O(N*K) + O(N)

            Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*K)).
         */
        // Function to count partitions using dynamic programming
        static int mod = (int) (Math.pow(10, 9) + 7);
        static int countPartitionsUtil(int ind, int target, int[] arr, int[][] dp) {
            // Base case: If we have reached the first element
            if (ind == 0) {
                // Check if the target is 0 and the first element is also 0
                if (target == 0 && arr[0] == 0)
                    return 2;
                // Check if the target is equal to the first element or 0
                if (target == 0 || target == arr[0])
                    return 1;
                return 0;
            }

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind][target] != -1)
                return dp[ind][target];

            // Calculate the number of ways without taking the current element
            int notTaken = countPartitionsUtil(ind - 1, target, arr, dp);

            // Initialize the number of ways taking the current element as 0
            int taken = 0;

            // If the current element is less than or equal to the target, calculate 'taken'
            if (arr[ind] <= target)
                taken = countPartitionsUtil(ind - 1, target - arr[ind], arr, dp);

            // Store the result in the dp array and return it
            return dp[ind][target] = (notTaken + taken);
        }

        // Function to find the number of ways to achieve the target sum
        static int targetSum(int n, int target, int[] arr) {
            int totSum = 0;

            // Calculate the total sum of elements in the array
            for (int i = 0; i < arr.length; i++) {
                totSum += arr[i];
            }

            // Checking for edge cases
            if (totSum - target < 0)
                return 0;
            if ((totSum - target) % 2 == 1)
                return 0;

            // Calculate the second sum based on the total sum and the target
            int s2 = (totSum - target) / 2;

            // Create a 2D array to store results of subproblems
            int dp[][] = new int[n][s2 + 1];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the countPartitionsUtil function to calculate the number of ways
            return countPartitionsUtil(n - 1, s2, arr, dp);
        }
        /*
            Time Complexity: O(N*K)

            Reason: There are two nested loops

            Space Complexity: O(N*K)

            Reason: We are using an external array of size ‘N*K’. Stack Space is eliminated.
         */

        // Function to find the number of ways to achieve the target sum
        static int findWaysTabulation(int[] num, int tar) {
            int n = num.length;

            // Create a 2D array to store results of subproblems
            int[][] dp = new int[n][tar + 1];

            // Initialize the dp array for the first element of the array
            if (num[0] == 0)
                dp[0][0] = 2; // 2 cases - pick and not pick
            else
                dp[0][0] = 1; // 1 case - not pick

            if (num[0] != 0 && num[0] <= tar)
                dp[0][num[0]] = 1; // 1 case - pick

            // Fill the dp array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int target = 0; target <= tar; target++) {
                    int notTaken = dp[ind - 1][target];

                    int taken = 0;
                    if (num[ind] <= target)
                        taken = dp[ind - 1][target - num[ind]];

                    dp[ind][target] = (notTaken + taken) % mod;
                }
            }

            return dp[n - 1][tar];
        }

        // Function to calculate the number of ways to achieve the target sum
        static int targetSumTabulation(int n, int target, int[] arr) {
            int totSum = 0;

            // Calculate the total sum of elements in the array
            for (int i = 0; i < n; i++) {
                totSum += arr[i];
            }

            // Checking for edge cases
            if (totSum - target < 0 || (totSum - target) % 2 == 1)
                return 0;

            return findWaysTabulation(arr, (totSum - target) / 2);
        }

        /*
        Time Complexity: O(N*K)

        Reason: There are three nested loops

        Space Complexity: O(K)

        Reason: We are using an external array of size ‘K+1’ to store only one row.
         */

        // Function to find the number of ways to achieve the target sum
        static int findWaysSpaceOptimized(int[] num, int tar) {
            int n = num.length;

            // Create an array to store results of subproblems for the current element
            int prev[] = new int[tar + 1];

            // Initialize the prev array for the first element of the array
            if (num[0] == 0)
                prev[0] = 2; // 2 cases - pick and not pick
            else
                prev[0] = 1; // 1 case - not pick

            if (num[0] != 0 && num[0] <= tar)
                prev[num[0]] = 1; // 1 case - pick

            // Fill the prev array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                int cur[] = new int[tar + 1];//If you are taking inside then it is just new memory then u can assign cur to prev if taking outside then u need to clone it, both way u can do
                for (int target = 0; target <= tar; target++) {
                    int notTaken = prev[target];

                    int taken = 0;
                    if (num[ind] <= target)
                        taken = prev[target - num[ind]];

                    cur[target] = (notTaken + taken) % mod;
                }
                prev = cur;
            }

            return prev[tar];
        }

        // Function to calculate the number of ways to achieve the target sum
        static int targetSumSpaceOptimized(int n, int target, int[] arr) {
            int totSum = 0;

            // Calculate the total sum of elements in the array
            for (int i = 0; i < n; i++) {
                totSum += arr[i];
            }

            // Checking for edge cases
            if (totSum - target < 0 || (totSum - target) % 2 == 1)
                return 0;

            return findWaysSpaceOptimized(arr, (totSum - target) / 2);
        }

        public static void main(String args[]) {
            int arr[] = { 1, 2, 3, 1 };
            int target = 3;

            int n = arr.length;

            // Call the targetSum function and print the result
            System.out.println("The number of ways found is " + targetSum(n, target, arr));
        }
    }

    /*
        DP 22. Coin Change 2 | Infinite Supply Problems | DP on Subsequences
        https://www.youtube.com/watch?v=HgyouUi11zk&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=23
        Related:
         https://leetcode.com/problems/remove-boxes/ Hard
         * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/ Medium done
         * https://leetcode.com/problems/k-concatenation-maximum-sum/ Medium
         https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/description/ Hard
         https://leetcode.com/problems/number-of-ways-to-earn-points/description/ Hard
         https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum/description/ Hard
     */

    public static class CoinChangeTwo {
        /*
        Time Complexity: O(N*T)

        Reason: There are N*W states therefore at max ‘N*T’ new problems will be solved.

        Space Complexity: O(N*T) + O(N)

        Reason: We are using a recursion stack space(O(N)) and a 2D array ( O(N*T)).
         */
        // Recursive function to count the ways to make change
        static long countWaysToMakeChangeUtil(int[] arr, int ind, int T, long[][] dp) {
            // Base case: If the current index is 0
            if (ind == 0) {
                // If T is divisible by the first element of the array, return 1, else return 0
                if (T % arr[0] == 0)
                    return 1;
                else
                    return 0;
            }

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind][T] != -1)
                return dp[ind][T];

            // Calculate the number of ways without taking the current element
            long notTaken = countWaysToMakeChangeUtil(arr, ind - 1, T, dp);

            // Initialize the number of ways taking the current element as 0
            long taken = 0;

            // If the current element is less than or equal to T, calculate 'taken'
            if (arr[ind] <= T)
                taken = countWaysToMakeChangeUtil(arr, ind, T - arr[ind], dp);

            // Store the result in the dp array and return it
            return dp[ind][T] = notTaken + taken;
        }

        // Function to count the ways to make change
        static long countWaysToMakeChange(int[] arr, int n, int T) {
            // Create a 2D array to store results of subproblems
            long dp[][] = new long[n][T + 1];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (long row[] : dp)
                Arrays.fill(row, -1);

            // Call the countWaysToMakeChangeUtil function to calculate the number of ways
            return countWaysToMakeChangeUtil(arr, n - 1, T, dp);
        }

        /*
            Time Complexity: O(N*T)

            Reason: There are two nested loops

            Space Complexity: O(N*T)

            Reason: We are using an external array of size ‘N*T’. Stack Space is eliminated.
         */
        // Function to count the ways to make change
        static long countWaysToMakeChangeTabulation(int[] arr, int n, int T) {
            // Create a 2D array to store results of subproblems
            long dp[][] = new long[n][T + 1];

            // Initialize base condition for the first element of the array
            for (int i = 0; i <= T; i++) {
                if (i % arr[0] == 0)
                    dp[0][i] = 1;
                // Else condition is automatically fulfilled, as dp array is initialized to zero
            }

            // Fill the dp array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                for (int target = 0; target <= T; target++) {
                    long notTaken = dp[ind - 1][target];

                    long taken = 0;
                    if (arr[ind] <= target)
                        taken = dp[ind][target - arr[ind]];

                    dp[ind][target] = notTaken + taken;
                }
            }

            return dp[n - 1][T];
        }

        /*
            Time Complexity: O(N*T)

            Reason: There are two nested loops.

            Space Complexity: O(T)

            Reason: We are using an external array of size ‘T+1’ to store two rows only.
         */
        // Function to count the ways to make change
        static long countWaysToMakeChangeSpace(int[] arr, int n, int T) {
            // Create an array to store results of subproblems for the previous element
            long[] prev = new long[T + 1];

            // Initialize base condition for the first element of the array
            for (int i = 0; i <= T; i++) {
                if (i % arr[0] == 0)
                    prev[i] = 1;
                // Else condition is automatically fulfilled, as prev array is initialized to zero
            }

            // Fill the prev array using dynamic programming
            for (int ind = 1; ind < n; ind++) {
                // Create an array to store results of subproblems for the current element
                long[] cur = new long[T + 1];
                for (int target = 0; target <= T; target++) {
                    long notTaken = prev[target];

                    long taken = 0;
                    if (arr[ind] <= target)
                        taken = cur[target - arr[ind]];

                    cur[target] = notTaken + taken;
                }
                prev = cur;
            }

            return prev[T];
        }

        public static void main(String args[]) {
            int arr[] = { 1, 2, 3 };
            int target = 4;
            int n = arr.length;

            // Call the countWaysToMakeChange function and print the result
            System.out.println("The total number of ways is " + countWaysToMakeChange(arr, n, target));
        }
    }
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
