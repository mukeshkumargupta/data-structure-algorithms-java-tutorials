package com.interview.dynamic.PartGDPOnStrings;

public class E_ShortestCommonSupersequence {
    /*
    📚 Problem:
    https://www.youtube.com/watch?v=xElxAuBcvsU&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=32
    https://leetcode.com/problems/shortest-common-supersequence/description/
    Category: Hard, Tricky
    https://www.naukri.com/code360/problems/shortest-supersequence_4244493
    Derived: here it is asked to print the shortest supersequence, but if it asked length then ans will be l1 + l2 - lcs length
    📌 Related Problems:
    - https://leetcode.com/problems/shortest-string-that-contains-three-strings/description/ Medium
    https://leetcode.com/problems/longest-common-subsequence/ Medium

    📖 Description:
    Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
    If there are multiple valid strings, return any of them.

    A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.

    🧩 Examples:

    Example 1:
    Input: str1 = "abac", str2 = "cab"
    Output: "cabac"
    Explanation:
        - "abac" is a subsequence of "cabac" (delete the first 'c').
        - "cab" is a subsequence of "cabac" (delete the last 'ac').
        - "cabac" is the shortest string satisfying the conditions.

    Example 2:
    Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
    Output: "aaaaaaaa"
    Explanation:
        - Both strings are identical, so the shortest supersequence is the same string.

    🧠 Constraints:
    - 1 <= str1.length, str2.length <= 1000
    - str1 and str2 consist of lowercase English letters.

    🛠️ Complexity Analysis:

    Time Complexity: O(N * M)
    Reason: Two nested loops are used for dynamic programming (building the DP table).

    Space Complexity: O(N * M)
    Reason: A 2D external DP array of size (N * M) is used to store results.

    🔥 Note:
    The Longest Common Subsequence (LCS) concept is critical to solving this problem efficiently.
    First, find LCS, then merge both strings while avoiding repeated parts.
*/

    public static class ShortestCommonSupersequence {
        static String shortestSupersequence(String s1, String s2){

            int n = s1.length();
            int m = s2.length();

            int[][] dp =new int[n+1][m+1];
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= m; i++) {
                dp[0][i] = 0;
            }

            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    else
                        dp[ind1][ind2] = 0 + Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

            int len = dp[n][m];
            int i = n;
            int j = m;

            int index = len - 1;
            String ans = "";

            while (i > 0 && j > 0) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    ans += s1.charAt(i-1);
                    index--;
                    i--;
                    j--;
                } else if (dp[i - 1][j] > dp[i][j - 1]) {
                    ans += s1.charAt(i-1);
                    i--;
                } else {
                    ans += s2.charAt(j-1);
                    j--;
                }
            }

            //Adding Remaing Characters - Only one of the below two while loops will run

            while(i>0){
                ans += s1.charAt(i-1);
                i--;
            }
            while(j>0){
                ans += s2.charAt(j-1);
                j--;
            }

            String ans2=new StringBuilder(ans).reverse().toString();

            return ans2;
        }

        public static void main(String args[]) {

            String s1 = "brute";
            String s2 = "groot";

            System.out.println("The Longest Common Supersequence is "+shortestSupersequence(s1,s2));
        }
    }
}
