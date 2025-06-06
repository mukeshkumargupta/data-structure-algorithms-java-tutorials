package com.interview.dynamic.PartGDPOnStrings;

import java.util.Arrays;

public class D_MinimumInsertionsDeletionstoConvertString {
        /*

    https://www.youtube.com/watch?v=yMnH0jrir0Q&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=31
    Both  problem is same only wording is different
    https://leetcode.com/problems/delete-operation-for-two-strings/submissions/1374304008/
    https://www.naukri.com/code360/problems/minimum-number-of-deletions-and-insertions_4244510?leftPanelTabValue=SUBMISSION
    Complexity Analysis
    Time Complexity: O(N*M)

    Reason: There are two nested loops

    Space Complexity: O(N*M)

    Reason: We are using an external array of size (N*M). Stack Space is eliminated.
     */

    public static class MinimumInsertionsDeletionstoConvertString {
        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcs(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store the LCS lengths
            int dp[][] = new int[n + 1][m + 1];

            // Initialize the dp array with -1
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Initialize the first row and first column with 0
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= m; i++) {
                dp[0][i] = 0;
            }

            // Fill the dp array using a bottom-up approach
            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    else
                        dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

            return dp[n][m];
        }

        // Function to find the minimum operations required to convert str1 to str2
        static int canYouMake(String str1, String str2) {
            int n = str1.length();
            int m = str2.length();

            // Find the length of the LCS between str1 and str2
            int k = lcs(str1, str2);

            // The minimum operations required is the sum of the lengths of str1 and str2 minus twice the length of LCS
            return (n - k) + (m - k);
        }

        public static void main(String args[]) {
            String str1 = "abcd";
            String str2 = "anc";
            System.out.println("The Minimum operations required to convert str1 to str2: "
                    + canYouMake(str1, str2));
        }
    }
}
