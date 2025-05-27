package com.interview.dynamic.PartGDPOnStrings;

import java.util.Arrays;

public class A_LongestCommonSubsequence {
    /*
 * https://leetcode.com/problems/longest-common-subsequence/
 * Category: Medium, Must Do
 * Reference: Takeyouforward
 * Related: https://leetcode.com/problems/delete-operation-for-two-strings/
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

 A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

 For example, "ace" is a subsequence of "abcde".
 A common subsequence of two strings is a subsequence that is common to both strings.



 Example 1:

 Input: text1 = "abcde", text2 = "ace"
 Output: 3
 Explanation: The longest common subsequence is "ace" and its length is 3.
 Example 2:

 Input: text1 = "abc", text2 = "abc"
 Output: 3
 Explanation: The longest common subsequence is "abc" and its length is 3.
 Example 3:

 Input: text1 = "abc", text2 = "def"
 Output: 0
 Explanation: There is no such common subsequence, so the result is 0.


 Constraints:

 1 <= text1.length, text2.length <= 1000
 text1 and text2 consist of only lowercase English characters.
 */
    /*
    https://www.youtube.com/watch?v=NPZn9jBrX8U&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=26
    https://www.naukri.com/code360/problems/longest-common-subsequence_624879?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
     */
    public static class LongestCommonSubsequence {
        /*
        Time Complexity: O(N*M)

Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

Space Complexity: O(N*M) + O(N+M)

Reason: We are using an auxiliary recursion stack space(O(N+M)) (see the recursive tree, in the worst case, we will go till N+M calls at a time) and a 2D array ( O(N*M)).
         */
        // Recursive function to find the length of the Longest Common Subsequence (LCS)
        static int lcsUtil(String s1, String s2, int ind1, int ind2, int[][] dp) {
            // Base case: If either of the strings reaches the end, return 0
            if (ind1 < 0 || ind2 < 0)
                return 0;

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind1][ind2] != -1)
                return dp[ind1][ind2];

            // If the characters at the current indices are the same, increment the LCS length
            if (s1.charAt(ind1) == s2.charAt(ind2))
                return dp[ind1][ind2] = 1 + lcsUtil(s1, s2, ind1 - 1, ind2 - 1, dp);

                // If the characters are different, choose the maximum LCS length by either
                // skipping a character in s1 or skipping a character in s2
            else
                return dp[ind1][ind2] = Math.max(lcsUtil(s1, s2, ind1, ind2 - 1, dp),
                        lcsUtil(s1, s2, ind1 - 1, ind2, dp));
        }

        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcs(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store results of subproblems
            int dp[][] = new int[n][m];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Call the recursive function to find the LCS length
            return lcsUtil(s1, s2, n - 1, m - 1, dp);
        }


        // Recursive function to find the length of the Longest Common Subsequence (LCS)
        static int lcsUtilIncreaseIndex(String s1, String s2, int ind1, int ind2, int[][] dp) {
            // Base case: If either of the strings reaches the end, return 0
            if (ind1 == 0 || ind2 == 0)
                return 0;

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind1][ind2] != -1)
                return dp[ind1][ind2];

            // If the characters at the current indices are the same, increment the LCS length
            if (s1.charAt(ind1-1) == s2.charAt(ind2-1))
                return dp[ind1][ind2] = 1 + lcsUtil(s1, s2, ind1 - 1, ind2 - 1, dp);

                // If the characters are different, choose the maximum LCS length by either
                // skipping a character in s1 or skipping a character in s2
            else
                return dp[ind1][ind2] = Math.max(lcsUtil(s1, s2, ind1, ind2 - 1, dp),
                        lcsUtil(s1, s2, ind1 - 1, ind2, dp));
        }

        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcsIncreaseIndex(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store results of subproblems
            int dp[][] = new int[n+1][m+1];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Call the recursive function to find the LCS length
            return lcsUtil(s1, s2, n , m , dp);
        }

        /*
                    Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M)’. Stack Space is eliminated.
         */

        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcsTabulation(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store results of subproblems
            int dp[][] = new int[n + 1][m + 1];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Initialize the first row and first column with zeros since LCS with an empty string is zero
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= m; i++) {
                dp[0][i] = 0;
            }

            // Fill the dp array using dynamic programming
            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    // If the characters at the current indices are the same, increment the LCS length
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                        // If the characters are different, choose the maximum LCS length by either
                        // excluding a character in s1 or excluding a character in s2
                    else
                        dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

            return dp[n][m]; // Return the length of the Longest Common Subsequence (LCS)
        }

        static int lcsSpaceOptimization(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store results of subproblems
            int[] prev = new int [m + 1];;
            int[] cur = new int [m + 1];

            // Fill the dp array using dynamic programming
            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    // If the characters at the current indices are the same, increment the LCS length
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        cur[ind2] = 1 + prev[ind2 - 1];
                        // If the characters are different, choose the maximum LCS length by either
                        // excluding a character in s1 or excluding a character in s2
                    else
                        cur[ind2] = Math.max(prev[ind2], cur[ind2 - 1]);
                }
                prev = cur.clone();
            }

            return prev[m]; // Return the length of the Longest Common Subsequence (LCS)
        }

        /*
        https://www.youtube.com/watch?v=-zI4mrF2Pb4&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=27
         */
        public static class PrintLongestCommonSubsequence {
            static void lcs(String s1, String s2) {

                int n=s1.length();
                int m=s2.length();

                int dp[][]=new int[n+1][m+1];
                for(int i=0;i<=n;i++){
                    dp[i][0] = 0;
                }
                for(int i=0;i<=m;i++){
                    dp[0][i] = 0;
                }

                for(int ind1=1;ind1<=n;ind1++){
                    for(int ind2=1;ind2<=m;ind2++){
                        if(s1.charAt(ind1-1)==s2.charAt(ind2-1))
                            dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                        else
                            dp[ind1][ind2] = 0 + Math.max(dp[ind1-1][ind2],dp[ind1][ind2-1]);
                    }
                }

                int len=dp[n][m];
                int i=n;
                int j=m;

                int index = len-1;
                String ans="";
                StringBuilder ss= new StringBuilder(s1);
                while(i>0 && j>0){
                    if(s1.charAt(i-1) == s2.charAt(j-1)){
                        ans = ss.charAt(i-1) + ans;
                        index--;
                        i--;
                        j--;
                    }
                    else if(ss.charAt(i-1)>s2.charAt(j-1)){
                        i--;
                    }
                    else j--;
                }
                System.out.println(ans);
            }

            public static void main(String args[]) {

                String s1= "abcde";
                String s2= "bdgek";

                System.out.print("The Longest Common Subsequence is ");
                lcs(s1,s2);
            }
        }

        public static void main(String args[]) {
            String s1 = "acd";
            String s2 = "ced";

            // Call the lcs function and print the result
            System.out.println("The Length of Longest Common Subsequence is " + lcs(s1, s2));
        }
    }

    public static class LongestCommonSubstring {
        /*
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M)’. Stack Space is eliminated.
         */
        // Function to find the length of the Longest Common Substring (LCS)
        static int lcs(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store LCS lengths
            int[][] dp = new int[n + 1][m + 1];
            int ans = 0; // Initialize a variable to store the maximum LCS length

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    // If the characters at the current indices are the same, extend the LCS
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        int val = 1 + dp[i - 1][j - 1];
                        dp[i][j] = val;
                        ans = Math.max(ans, val); // Update the maximum LCS length
                    } else {
                        dp[i][j] = 0; // Reset LCS length if characters don't match
                    }
                }
            }

            return ans; // Return the length of the Longest Common Substring (LCS)
        }

        /*
            Time Complexity: O(N*M)

            Reason: There are two nested loops.

            Space Complexity: O(M)

            Reason: We are using an external array of size ‘M+1’ to store only two rows.
         */
        // Function to find the length of the Longest Common Substring (LCS)
        static int lcsSpaceOptimized(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create arrays to store LCS lengths
            int prev[] = new int[m + 1];
            int cur[] = new int[m + 1];

            int ans = 0; // Initialize a variable to store the maximum LCS length

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    // If the characters at the current indices are the same, extend the LCS
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        int val = 1 + prev[j - 1];
                        cur[j] = val;
                        ans = Math.max(ans, val); // Update the maximum LCS length
                    } else {
                        cur[j] = 0; // Reset LCS length if characters don't match
                    }
                }
                // Update the 'prev' array to the values of 'cur' for the next iteration
                prev = cur.clone();
            }

            return ans; // Return the length of the Longest Common Substring (LCS)
        }


        public static void main(String args[]) {
            String s1 = "abcjklp";
            String s2 = "acjkp";

            // Call the lcs function and print the result
            System.out.println("The Length of Longest Common Substring is " + lcs(s1, s2));
        }
    }
}
