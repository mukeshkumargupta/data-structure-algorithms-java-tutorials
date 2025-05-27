package com.interview.dynamic.houseRobberPatterns;

import java.util.ArrayList;
import java.util.Arrays;

/*
https://www.youtube.com/watch?v=GrMBfJNk_NY
Category: Medium
 */
public class B_PartCOneDDPMaximumSumofNonAdjacentElementsOrHouseRobber {
    /*
Time Complexity: O(N)

Reason: The overlapping subproblems will return the answer in constant time O(1). Therefore the total number of new subproblems we solve is ‘n’. Hence total time complexity is O(N).

Space Complexity: O(N)

Reason: We are using a recursion stack space(O(N)) and an array (again O(N)). Therefore total space complexity will be O(N) + O(N) ≈ O(N)
 */
    // This function recursively calculates the maximum possible sum
    // by considering or not considering the current element.
    static int solveUtilRecursive(int ind, int[] arr, int[] dp) {
        // If the index is negative, there are no elements left to consider.
        if (ind < 0) return 0;

        // If the index is 0, there is only one element to consider, so return its value.
        if (ind == 0) return arr[ind];

        // If we have already calculated the result for this index, return it.
        if (dp[ind] != -1) return dp[ind];

        // Calculate the maximum sum by either picking the current element or not picking it.
        int pick = arr[ind] + solveUtilRecursive(ind - 2, arr, dp);
        int nonPick = solveUtilRecursive(ind - 1, arr, dp);

        // Store the maximum of the two options in the dp array for future reference.
        return dp[ind] = Math.max(pick, nonPick);
    }

    // This function initializes the dp array and calls the recursive solver.
    static int solveRecursive(int n, int[] arr) {
        int dp[] = new int[n];

        // Initialize the dp array with -1 to indicate that values are not calculated yet.
        Arrays.fill(dp, -1);

        // Call the recursive solver for the last index (n-1).
        return solveUtilRecursive(n - 1, arr, dp);
    }
    /*
    Time Complexity: O(N)

Reason: We are running a simple iterative loop

Space Complexity: O(N)

Reason: We are using an external array of size ‘n+1’.
     */

    // This function uses dynamic programming to find the maximum possible sum of non-adjacent elements.
    static int solveUtilBottomUp(int n, int[] arr, int[] dp) {
        // Initialize the dp array with the first element of the input array.
        dp[0] = arr[0];

        // Iterate through the input array to fill the dp array.
        for (int i = 1; i < n; i++) {
            // Calculate the maximum sum by either picking the current element or not picking it.
            int pick = arr[i];

            // If there are at least two elements before the current element, add the value from dp[i-2].
            if (i > 1)
                pick += dp[i - 2];

            // The non-pick option is to use the maximum sum from the previous element.
            int nonPick = dp[i - 1];

            // Store the maximum of the two options in the dp array for the current index.
            dp[i] = Math.max(pick, nonPick);
        }

        // The final element of the dp array contains the maximum possible sum.
        return dp[n - 1];
    }

    // This function initializes the dp array and calls the solver function.
    static int solveBottomUp(int n, int[] arr) {
        int dp[] = new int[n];

        // Initialize the dp array with -1 to indicate that values are not calculated yet.
        Arrays.fill(dp, -1);

        // Call the solver function to find the maximum possible sum.
        return solveUtilBottomUp(n, arr, dp);
    }

    /*
        Time Complexity: O(N)

    Reason: We are running a simple iterative loop

    Space Complexity: O(1)

    Reason: We are not using any extra space.
     */

    // This function finds the maximum possible sum of non-adjacent elements in an array
    // using a more space-efficient dynamic programming approach.
    static int solveBottomUpSpaceOptimized(int n, int[] arr) {
        // Initialize variables to keep track of the maximum sums at the current and previous positions.
        int prev = arr[0];
        int prev2 = 0;

        // Iterate through the array starting from the second element.
        for (int i = 1; i < n; i++) {
            // Calculate the maximum sum by either picking the current element or not picking it.
            int pick = arr[i];

            // If there are at least two elements before the current element, add the value from prev2.
            if (i > 1)
                pick += prev2;

            // The non-pick option is to use the maximum sum from the previous position.
            int nonPick = prev;

            // Calculate the maximum sum for the current position and update prev and prev2.
            int cur_i = Math.max(pick, nonPick);
            prev2 = prev;
            prev = cur_i;
        }

        // The 'prev' variable now holds the maximum possible sum.
        return prev;
    }


    public static void main(String args[]) {
        // Input array with elements.
        int arr[] = {2, 1, 4, 9};

        // Get the length of the array.
        int n = arr.length;

        // Call the solve function to find the maximum possible sum.
        int result = solveRecursive(n, arr);

        // Print the result.
        System.out.println(result);

        result = solveBottomUp(n, arr);

        // Print the result.
        System.out.println(result);

        result = solveBottomUpSpaceOptimized(n, arr);

        // Print the result.
        System.out.println(result);
    }

            /*
     * https://leetcode.com/problems/house-robber-ii/
     * Category: Medium, Tricky
     * https://www.youtube.com/watch?v=3WaxQMELSkw
     * Related: https://leetcode.com/problems/paint-house/ Medium Locked
     * https://leetcode.com/problems/paint-fence/ Medium
     * https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/ Hard
     * https://leetcode.com/problems/coin-path/ Hard
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

    Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.



    Example 1:

    Input: nums = [2,3,2]
    Output: 3
    Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
    Example 2:

    Input: nums = [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    Total amount you can rob = 1 + 3 = 4.
    Example 3:

    Input: nums = [1,2,3]
    Output: 3


    Constraints:

    1 <= nums.length <= 100
    0 <= nums[i] <= 1000
     */



    public static class HouseRobber2 {
        //only use third option method directly
        static long solve(ArrayList<Integer> arr) {
            int n = arr.size();
            long prev = arr.get(0);
            long prev2 = 0;

            for (int i = 1; i < n; i++) {
                long pick = arr.get(i);
                if (i > 1)
                    pick += prev2;
                long nonPick = 0 + prev;

                long cur_i = Math.max(pick, nonPick);
                prev2 = prev;
                prev = cur_i;

            }
            return prev;
        }

        static long robStreet(int n, ArrayList<Integer> arr) {
            ArrayList<Integer> arr1 = new ArrayList<>();
            ArrayList<Integer> arr2 = new ArrayList<>();

            if (n == 1)
                return arr.get(0);

            for (int i = 0; i < n; i++) {

                if (i != 0) arr1.add(arr.get(i));
                if (i != n - 1) arr2.add(arr.get(i));
            }

            long ans1 = solve(arr1);
            long ans2 = solve(arr2);

            return Math.max(ans1, ans2);
        }


        public static void main(String args[]) {

            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(1);
            arr.add(5);
            arr.add(1);
            arr.add(2);
            arr.add(6);
            int n = arr.size();
            System.out.println(robStreet(n, arr));

        }
    }
}
