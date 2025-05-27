package com.interview.dynamic.PartIDPOnLISubsequence;

import java.util.ArrayList;
import java.util.Arrays;

public class A_LongestIncreasingSubsequence {
    /*
    https://www.youtube.com/watch?v=ekcwMsSIzVc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=42
    Category: Medium
    https://leetcode.com/problems/longest-increasing-subsequence/
    /**
* Date 05/02/2017
* @author Mukesh Kumar Gupta
*
* Youtube link - https://youtu.be/CE2b_-XfVDk
* Leetcode: https://leetcode.com/problems/longest-increasing-subsequence/
* Category: Medium, Must Do
*
* Find a subsequence in given array in which the subsequence's elements are
* in sorted order, lowest to highest, and in which the subsequence is as long as possible
*
* Solution :
* Dynamic Programming is used to solve this question. DP equation is
* if(arr[i] > arr[j]) { dp[i] = max(dp[i], dp[j] + 1 }
*
* Time complexity is O(n^2).
* Space complexity is O(n)
*
* Reference
* http://en.wikipedia.org/wiki/Longest_increasing_subsequence
* http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
* Category: Must Do, Medium
* If you want to track one more what are the sequences then take one more array and store from where it came
* Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104


Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/
    public static class LongestIncreasingSubsequence {
        /*
        Time Complexity: O(N*N)

        Reason: There are N*N states therefore at max ‘N*N’ new problems will be solved.

        Space Complexity: O(N*N) + O(N)

        Reason: We are using an auxiliary recursion stack space(O(N)) (see the recursive tree, in the worst case we will go till N calls at a time) and a 2D array ( O(N*N+1)).
         */
        // Function to find the length of the longest increasing subsequence
        static int getAns(int arr[], int n, int ind, int prev_index, int[][] dp) {
            // Base condition
            if (ind == n) {
                return 0;
            }

            if (dp[ind][prev_index + 1] != -1) {
                return dp[ind][prev_index + 1];
            }

            int notTake = 0 + getAns(arr, n, ind + 1, prev_index, dp);

            int take = 0;

            if (prev_index == -1 || arr[ind] > arr[prev_index]) {
                take = 1 + getAns(arr, n, ind + 1, ind, dp);
            }

            dp[ind][prev_index + 1] = Math.max(notTake, take);

            return dp[ind][prev_index + 1];
        }

        // Function to find the length of the longest increasing subsequence
        static int longestIncreasingSubsequence(int arr[], int n) {
            int dp[][] = new int[n][n + 1];

            // Initialize dp array with -1 to mark states as not calculated yet
            for (int row[] : dp) {
                Arrays.fill(row, -1);
            }

            return getAns(arr, n, 0, -1, dp);
        }

        /*
        The length of the longest increasing subsequence is 4

        Time Complexity: O(N*N)

        Reason: There are two nested loops

        Space Complexity: O(N*N)

        Reason: We are using an external array of size ‘(N+1)*(N+1)’. Stack Space is eliminated.
         */
        static int longestIncreasingSubsequenceTabulation(int arr[], int n) {

            int dp[][] = new int[n + 1][n + 1];

            for (int ind = n - 1; ind >= 0; ind--) {
                for (int prev_index = ind - 1; prev_index >= -1; prev_index--) {

                    int notTake = 0 + dp[ind + 1][prev_index + 1];

                    int take = 0;

                    if (prev_index == -1 || arr[ind] > arr[prev_index]) {

                        take = 1 + dp[ind + 1][ind + 1];
                    }

                    dp[ind][prev_index + 1] = Math.max(notTake, take);

                }
            }

            return dp[0][0];
        }

        /*
        Output:

        The length of the longest increasing subsequence is 4

        Time Complexity: O(N*N)

        Reason: There are two nested loops.

        Space Complexity: O(N)

        Reason: We are only using two rows of size n.
         */
        static int longestIncreasingSubsequenceSpcaeOptimized(int arr[], int n) {

            int next[] = new int[n + 1];
            int cur[] = new int[n + 1];

            for (int ind = n - 1; ind >= 0; ind--) {
                for (int prev_index = ind - 1; prev_index >= -1; prev_index--) {

                    int notTake = 0 + next[prev_index + 1];

                    int take = 0;

                    if (prev_index == -1 || arr[ind] > arr[prev_index]) {

                        take = 1 + next[ind + 1];
                    }

                    cur[prev_index + 1] = Math.max(notTake, take);
                }
                next = cur.clone();
            }

            return cur[0];
        }

        /*
        Output:

        The length of the longest increasing subsequence is 4

        Time Complexity: O(N*N)
        https://youtu.be/CE2b_-XfVDk  Most Easy

        Reason: There are two nested loops.

        Space Complexity: O(N)

        Reason: We are only using two rows of size ‘N’.
         */

        static int longestIncreasingSubsequenceCustommAlgorithms(int arr[], int n) {

            int dp[] = new int[n];
            Arrays.fill(dp, 1);

            for (int i = 0; i <= n - 1; i++) {
                for (int prev_index = 0; prev_index <= i - 1; prev_index++) {

                    if (arr[prev_index] < arr[i]) {
                        dp[i] = Math.max(dp[i], 1 + dp[prev_index]);
                    }
                }
            }

            int ans = -1;

            for (int i = 0; i <= n - 1; i++) {
                ans = Math.max(ans, dp[i]);
            }

            return ans;
        }


        /*
        https://www.naukri.com/code360/problems/printing-longest-increasing-subsequence_8360670
        Time Complexity: O(N*N)

    Reason: There are two nested loops.

    Space Complexity: O(N)

    Reason: We are only using two rows of size ‘N’.
         */
        static int longestIncreasingSubsequencePrinting(int arr[], int n) {

            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            int[] hash = new int[n];
            Arrays.fill(hash, 1);

            for (int i = 0; i <= n - 1; i++) {

                hash[i] = i; // initializing with current index
                for (int prev_index = 0; prev_index <= i - 1; prev_index++) {

                    if (arr[prev_index] < arr[i] && 1 + dp[prev_index] > dp[i]) {
                        dp[i] = 1 + dp[prev_index];
                        hash[i] = prev_index;
                    }
                }
            }

            int ans = -1;
            int lastIndex = -1;

            for (int i = 0; i <= n - 1; i++) {
                if (dp[i] > ans) {
                    ans = dp[i];
                    lastIndex = i;
                }
            }

            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(arr[lastIndex]);

            while (hash[lastIndex] != lastIndex) { // till not reach the initialization value
                lastIndex = hash[lastIndex];
                temp.add(arr[lastIndex]);
            }

            // reverse the array

            System.out.print("The subsequence elements are ");

            for (int i = temp.size() - 1; i >= 0; i--) {
                System.out.print(temp.get(i) + " ");
            }
            System.out.println();

            return ans;
        }

        public static void main(String args[]) {
            int arr[] = {10, 9, 2, 5, 3, 7, 101, 18};

            int n = arr.length;

            System.out.println("The length of the longest increasing subsequence is " + longestIncreasingSubsequence(arr, n));
        }
    }
}
