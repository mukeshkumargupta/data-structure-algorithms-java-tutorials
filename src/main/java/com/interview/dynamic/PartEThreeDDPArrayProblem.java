package com.interview.dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PartEThreeDDPArrayProblem {

    /*
        DP 13. Cherry Pickup II | 3D DP Made Easy | DP On Grids
        Video: https://www.youtube.com/watch?v=QGfn7JeXK54&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=14
        Category: Hard, 3D DP Problem
        https://leetcode.com/problems/cherry-pickup-ii/
        Related: https://leetcode.com/problems/find-all-lonely-numbers-in-the-array/description/ Medium
        https://leetcode.com/problems/count-tested-devices-after-test-operations/description/ Easy
        https://leetcode.com/problems/maximum-sum-of-subsequence-with-non-adjacent-elements/description/ Hard
     */
    static public class NinjaAndHisFriends {
        /*
            Complexity Analysis
            Time Complexity: O(R*C*C) * 9

            Reason: At max, there will be R*C*C calls of recursion to solve a new problem and in every call, two nested loops together run for 9 times.

            Space Complexity: O(R) + O(R*C*C)

            Reason: We are using a recursion stack space: O(R), where R is the path length and an external DP Array of size ‘R*C*C’.
         */
        // Function to find the maximum number of chocolates using dynamic programming
        static int maxChocoUtil(int i, int j1, int j2, int R, int C, int[][] grid,
                                int[][][] dp) {
            // Check if j1 and j2 are valid column indices
            if (j1 < 0 || j1 >= C || j2 < 0 || j2 >= C)
                return (int) (Math.pow(-10, 9));

            // If we are at the last row, return the sum of chocolates in the selected columns
            if (i == R - 1) {
                if (j1 == j2)
                    return grid[i][j1];
                else
                    return grid[i][j1] + grid[i][j2];
            }

            // If the result for this state is already computed, return it
            if (dp[i][j1][j2] != -1)
                return dp[i][j1][j2];

            int maxi = Integer.MIN_VALUE;
            // Iterate through possible moves in the next row
            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) { //9 combination
                    int ans;
                    // If j1 and j2 are the same, add chocolates from grid[i][j1] only
                    if (j1 == j2)
                        ans = grid[i][j1] + maxChocoUtil(i + 1, j1 + di, j2 + dj, R, C, grid, dp);
                    else
                        // Add chocolates from both j1 and j2
                        ans = grid[i][j1] + grid[i][j2] + maxChocoUtil(i + 1, j1 + di, j2 + dj, R, C, grid, dp);
                    // Update maxi with the maximum result
                    maxi = Math.max(maxi, ans);
                }
            }
            // Store the result in the dp array and return it
            return dp[i][j1][j2] = maxi;
        }

        // Function to find the maximum number of chocolates
        static int maximumChocolates(int n, int m, int[][] grid) {
            // Create a 3D array to store computed results
            int dp[][][] = new int[n][m][m];

            // Initialize the dp array with -1
            for (int row1[][] : dp) {
                for (int row2[] : row1) {
                    Arrays.fill(row2, -1);
                }
            }

            // Call the utility function to find the maximum number of chocolates
            return maxChocoUtil(0, 0, m - 1, n, m, grid, dp);
        }

        /*
            TiCe CoCplexity: O(R*C*C)*9

            ReasoR: The outer Rested loops ruR for (R*C*C) tiCes aRd the iRRer two Rested loops ruR for 9 tiCes.

            Space CoCplexity: O(R*C*C)

            ReasoR: We are usiRg aR exterRal array of size ‘R*C*C’. The stack space will be eliCiRated.
         */
        // Function to find the maximum number of chocolates using dynamic programming
        static int maximumChocolatesTabulation(int R, int C, int[][] grid) {
            // Create a 3D array to store computed results
            int dp[][][] = new int[R][C][C];

            // Initialize the dp array with values from the last row of the grid
            for (int j1 = 0; j1 < C; j1++) {
                for (int j2 = 0; j2 < C; j2++) {
                    if (j1 == j2)
                        dp[R - 1][j1][j2] = grid[R - 1][j1];
                    else
                        dp[R - 1][j1][j2] = grid[R - 1][j1] + grid[R - 1][j2];
                }
            }

            // Outer nested loops to traverse the DP array from the second last row to the first row
            for (int i = R - 2; i >= 0; i--) {
                for (int j1 = 0; j1 < C; j1++) {
                    for (int j2 = 0; j2 < C; j2++) {
                        int maxi = Integer.MIN_VALUE;

                        // Inner nested loops to try out 9 options
                        for (int di = -1; di <= 1; di++) {
                            for (int dj = -1; dj <= 1; dj++) {
                                int ans;

                                if (j1 == j2)
                                    ans = grid[i][j1];
                                else
                                    ans = grid[i][j1] + grid[i][j2];

                                // Check if the indices are valid
                                if ((j1 + di < 0 || j1 + di >= C) || (j2 + dj < 0 || j2 + dj >= C))
                                    ans += (int) Math.pow(-10, 9);
                                else
                                    ans += dp[i + 1][j1 + di][j2 + dj];

                                // Update maxi with the maximum result
                                maxi = Math.max(ans, maxi);
                            }
                        }
                        // Store the result in the dp array
                        dp[i][j1][j2] = maxi;
                    }
                }
            }

            // The final result is stored at the top row (first row) of the dp array
            return dp[0][0][C - 1];
        }

        /*
            Time complexity: O(R*C*C)*9

            Reason: The outer nested loops run for (N*C*C) tiCes and the inner two nested loops run for 9 times.

            Space Complexity: O(C*C)

            Reason: We are using an external array of size ‘C*C’.
         */
        // Function to find the maximum number of chocolates using dynamic programming
        static int maximumChocolatesSpaceOptiimized(int R, int C, int[][] grid) {
            // Create two 2D arrays to store computed results: front and cur
            int[][] prev = new int[C][C];
            int[][] cur = new int[C][C];

            // Initialize the front array with values from the last row of the grid
            for (int j1 = 0; j1 < C; j1++) {
                for (int j2 = 0; j2 < C; j2++) {
                    if (j1 == j2)
                        prev[j1][j2] = grid[R - 1][j1];
                    else
                        prev[j1][j2] = grid[R - 1][j1] + grid[R - 1][j2];
                }
            }

            // Outer nested loops to traverse the DP array from the second last row to the first row
            for (int i = R - 2; i >= 0; i--) {
                for (int j1 = 0; j1 < C; j1++) {
                    for (int j2 = 0; j2 < C; j2++) {
                        int maxi = Integer.MIN_VALUE;

                        // Inner nested loops to try out 9 options
                        for (int di = -1; di <= 1; di++) {
                            for (int dj = -1; dj <= 1; dj++) {
                                int ans;

                                if (j1 == j2)
                                    ans = grid[i][j1];
                                else
                                    ans = grid[i][j1] + grid[i][j2];

                                // Check if the indices are valid
                                if ((j1 + di < 0 || j1 + di >= C) || (j2 + dj < 0 || j2 + dj >= C))
                                    ans += (int) Math.pow(-10, 9);
                                else
                                    ans += prev[j1 + di][j2 + dj];

                                // Update maxi with the maximum result
                                maxi = Math.max(ans, maxi);
                            }
                        }
                        // Store the result in the cur array
                        cur[j1][j2] = maxi;
                    }
                }

                // Update the prev array with the values from the cur array for the next row
                for (int a = 0; a < C; a++) {
                    prev[a] = cur[a].clone();
                }
            }

            // The final result is stored at the top left corner of the prev array
            return prev[0][C - 1];
        }

        public static void main(String args[]) {
            int matrix[][] = {{2, 3, 1, 2},
                    {3, 4, 2, 2},
                    {5, 6, 3, 5}};
            int R = matrix.length;
            int C = matrix[0].length;

            // Call the maximumChocolates function and print the result
            System.out.println(maximumChocolates(R, C, matrix));
        }
    }

    /*
https://leetcode.com/problems/interleaving-string/description/?envType=study-plan-v2&envId=top-interview-150
https://www.youtube.com/watch?v=EzQ_YEmR598
Category: Medium, top150, tricky
Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.



Example 1:


Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
Example 3:

Input: s1 = "", s2 = "", s3 = ""
Output: true


Constraints:

0 <= s1.length, s2.length <= 100
0 <= s3.length <= 200
s1, s2, and s3 consist of lowercase English letters.


Follow up: Could you solve it using only O(s2.length) additional memory space?
 */
/*
    Derived questions:

    The LeetCode problem "Interleaving String" (Problem 97) focuses on determining if a string s3 can be
    formed by interleaving two other strings, s1 and s2. This problem is categorized under Dynamic Programming (DP)
    and shares similarities with several other problems that involve combining or partitioning strings and sequences.

    Here are some related problems that can help deepen your understanding of similar concepts:

    1. Longest Common Subsequence (LeetCode 1143):
       - Involves finding the length of the longest subsequence present in both given sequences.
       - Fundamental for understanding sequence alignment.

    2. Edit Distance (LeetCode 72):
       - Also known as Levenshtein distance.
       - Requires computing the minimum number of operations to convert one string into another.
       - Classic string transformation problem.

    3. Distinct Subsequences (LeetCode 115):
       - Asks for the number of distinct subsequences of s1 which equal s2.
       - Focuses on pattern counting within sequences.

    4. Scramble String (LeetCode 87):
       - Checks whether one string is a scrambled version of another.
       - Involves recursive partitioning and rearrangement.

    5. Minimum Deletions to Make String Balanced (LeetCode 1653):
       - Finds the minimum deletions required to make a string balanced (characters 'a' and 'b').

    6. Flatten Nested List Iterator (LeetCode 341):
       - Involves traversing nested structures.
       - Requires careful handling of multiple levels of data.

    7. Repeated Substring Pattern (LeetCode 459):
       - Checks if a string can be constructed by repeating a substring.
       - Related to pattern detection in strings.
 */
    public class InterleavingString {
        /*
        ✅ Time and Space Complexity
    ⏱️ Time Complexity: O(m * n)
    m = s1.length(), n = s2.length()

    Each state (p1, p2) is visited once.

    p3 is always p1 + p2, so doesn't add complexity.

    🧠 Space Complexity: O(m * n)
    Due to memoization (memo stores at most m * n keys).

    Recursion stack space is at most O(m + n) in the worst case (fully recursive depth).
         */
        private Map<String, Boolean> memo = new HashMap<>();

        private boolean check(String s1, String s2, String s3, int p1, int p2, int p3) {
            // If we have reached the end of s3, check if we have also finished s1 and s2
            if (p3 == s3.length()) {
                return p1 == s1.length() && p2 == s2.length();
            }

            String key = p1 + "*" + p2 + "*" + p3;
            if (memo.containsKey(key)) {
                return memo.get(key);
            }

            // If s1 is exhausted, we can only try matching s2 with s3
            if (p1 == s1.length()) {
                boolean match = s2.charAt(p2) == s3.charAt(p3) && check(s1, s2, s3, p1, p2 + 1, p3 + 1);
                memo.put(key, match);
                return match;
            }

            // If s2 is exhausted, we can only try matching s1 with s3
            if (p2 == s2.length()) {
                boolean match = s1.charAt(p1) == s3.charAt(p3) && check(s1, s2, s3, p1 + 1, p2, p3 + 1);
                memo.put(key, match);
                return match;
            }

            // Try matching both s1 and s2 with s3
            boolean matchS1 = false, matchS2 = false;
            if (s1.charAt(p1) == s3.charAt(p3)) {
                matchS1 = check(s1, s2, s3, p1 + 1, p2, p3 + 1);
            }
            if (s2.charAt(p2) == s3.charAt(p3)) {
                matchS2 = check(s1, s2, s3, p1, p2 + 1, p3 + 1);
            }

            boolean result = matchS1 || matchS2;
            memo.put(key, result);
            return result;
        }

        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            return check(s1, s2, s3, 0, 0, 0);
        }
    }
}
