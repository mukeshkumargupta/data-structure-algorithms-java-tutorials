package com.interview.dynamic;

/*

Category: Hard, VVImp, palindrome partioning
132. Palindrome Partitioning II
Solved
Hard
Topics
Companies
Given a string s, partition s such that every
substring
 of the partition is a
palindrome
.

Return the minimum cuts needed for a palindrome partitioning of s.



Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1


Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters only.
 */

import java.util.Arrays;

public class PartJ_E_PalindromePartitioningII {

    private static class Buitforce {
        /**Brute Force Approach (Recursive)
        Idea
        We try all possible partitions of the string.
        If a substring is a palindrome, we recursively check the remaining part.
        The goal is to minimize the number of cuts.
                Algorithm
        Start from index 0, try every possible partition.
        If the substring s[start:i] is a palindrome, recursively check the remaining part.
        Keep track of the minimum cuts.
        Base case: If the entire string is a palindrome, return 0.

         Dry Run Example
         Input: "aab"
         Recursive Calls
         sql
         Copy
         Edit
         partition(0, "aab")  →   "a" | "ab"  (check "a" first)
         partition(1, "aab")  →   "a" | "b"
         partition(2, "aab")  →  "b" (valid palindrome)
         partition(3, "aab") → return 0 (base case)

         So, the minimum cuts needed = 1

         Time Complexity Analysis
         Checking palindromes: O(N) per call.
         Worst case recursive calls: O(2^N).
         Total Complexity: O(2^N * N).
         Too slow for N = 2000!
         */

        public int minCut(String s) {
            return partition(0, s) - 1; // -1 because last partition doesn't need a cut
        }

        private int partition(int start, String s) {
            if (start == s.length()) return 0; // No more partitions needed

            int minCuts = Integer.MAX_VALUE;
            for (int end = start; end < s.length(); end++) {
                if (isPalindrome(s, start, end)) {
                    int cuts = 1 + partition(end + 1, s); // 1 cut + remaining cuts
                    minCuts = Math.min(minCuts, cuts);
                }
            }
            return minCuts;
        }

        private boolean isPalindrome(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start++) != s.charAt(end--)) return false;
            }
            return true;
        }
    }

    private static class better {
        /**
        Optimized Approach: Memoization (Top-Down DP)
         Time Complexity Analysis
         Each substring check: O(N).
         O(N²) recursive calls, each taking O(N).
         Total: O(N²) time, O(N) space (for DP array).
         */
        private int[] dp;

        public int minCut(String s) {
            int n = s.length();
            dp = new int[n];
            Arrays.fill(dp, -1); // Fill with -1 to indicate uncomputed states
            return partition(0, s) - 1; // -1 because last partition doesn’t need a cut
        }

        private int partition(int start, String s) {
            if (start == s.length()) return 0;
            if (dp[start] != -1) return dp[start]; // Use cached result

            int minCuts = Integer.MAX_VALUE;
            for (int end = start; end < s.length(); end++) {
                if (isPalindrome(s, start, end)) {
                    int cuts = 1 + partition(end + 1, s);
                    minCuts = Math.min(minCuts, cuts);
                }
            }
            return dp[start] = minCuts;
        }

        private boolean isPalindrome(String s, int start, int end) {
            while (start < end) {
                if (s.charAt(start++) != s.charAt(end--)) return false;
            }
            return true;
        }
    }

    private static class Optimized {
        /**
         * Final Optimized Approach: Bottom-Up DP
         * We can eliminate recursion and solve iteratively!
         *
         * Time Complexity Analysis
         * Precomputing isPalindrome[][]: O(N²)
         * Computing dp[]: O(N²)
         * Total Complexity: O(N²), Optimal!
         * ✅ Final Thoughts
         * Approach	Time Complexity	Space Complexity	Comments
         * Brute Force (Recursive)	O(2^N * N)	O(N)	Too slow for large N
         * Top-Down DP (Memoization)	O(N²)	O(N)	Faster but still recursive
         * Bottom-Up DP (Optimal)	O(N²)	O(N²)	Best approach 🚀
         */
        public int minCut(String s) {
            int n = s.length();
            boolean[][] isPalindrome = new boolean[n][n];
            int[] dp = new int[n];

            // Step 1: Compute palindrome table
            for (int len = 1; len <= n; len++) {
                for (int i = 0; i + len - 1 < n; i++) {
                    int j = i + len - 1;
                    if (s.charAt(i) == s.charAt(j)) {
                        isPalindrome[i][j] = (len == 1 || len == 2 || isPalindrome[i + 1][j - 1]);
                    }
                }
            }

            // Step 2: Compute minimum cuts
            Arrays.fill(dp, Integer.MAX_VALUE);
            for (int i = 0; i < n; i++) {
                if (isPalindrome[0][i]) {
                    dp[i] = 0;
                } else {
                    for (int j = 0; j < i; j++) {
                        if (isPalindrome[j + 1][i]) {
                            dp[i] = Math.min(dp[i], dp[j] + 1);
                        }
                    }
                }
            }

            return dp[n - 1];
        }
    }
}
