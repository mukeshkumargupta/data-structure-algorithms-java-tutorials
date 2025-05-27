package com.interview.dynamic.PartIDPOnLISubsequence;

import java.util.Arrays;

public class D_LongestBitonicSubsequence {
    /*
     * http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
     * https://www.youtube.com/watch?v=y4vN0WNdrlg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=47
     *
     * This is derived question from bitonic subsequence
     * https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/solutions/1166577/total-length-length-of-longest-bitonic-sequence/
     * Reference: https://www.youtube.com/watch?v=TWHytKnOPaQ
     * Derived question: Find what the elements rather finding only max count, i think take one extra array and keep that index info
     * for which max is updated.
     * Company:
     * Category: Easy
     * Status: Done
     */
    public static class LongestBitonicSubsequence {

        /*
            The length of the longest bitonic subsequence is 6

            Time Complexity: O(N*N)

            Reason: There are two nested loops that are run twice.

            Space Complexity: O(N)

            Reason: We are only using two rows of size n.
         */
        // Function to find the length of the longest bitonic subsequence
        static int longestBitonicSequence(int[] arr, int n) {
            // Arrays to store lengths of increasing and decreasing subsequences
            int[] dp1 = new int[n];
            int[] dp2 = new int[n];

            // Initialize both arrays with 1, as each element itself is a subsequence of length 1
            Arrays.fill(dp1, 1);
            Arrays.fill(dp2, 1);

            // Calculate the lengths of increasing subsequences
            for (int i = 0; i < n; i++) {
                for (int prevIndex = 0; prevIndex < i; prevIndex++) {
                    if (arr[prevIndex] < arr[i]) {
                        dp1[i] = Math.max(dp1[i], 1 + dp1[prevIndex]);
                    }
                }
            }

            // Reverse the direction of nested loops and calculate the lengths of decreasing subsequences
            for (int i = n - 1; i >= 0; i--) {
                for (int prevIndex = n - 1; prevIndex > i; prevIndex--) {
                    if (arr[prevIndex] < arr[i]) {
                        dp2[i] = Math.max(dp2[i], 1 + dp2[prevIndex]);
                    }
                }
            }

            int maxi = -1;

            // Calculate the length of the longest bitonic subsequence
            for (int i = 0; i < n; i++) {
                maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
            }

            return maxi;
        }

        public static void main(String[] args) {
            int arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
            int n = arr.length;

            System.out.println("The length of the longest bitonic subsequence is " +
                    longestBitonicSequence(arr, n));
        }
    }
}
