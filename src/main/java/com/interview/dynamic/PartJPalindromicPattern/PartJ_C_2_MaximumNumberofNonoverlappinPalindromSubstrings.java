package com.interview.dynamic.PartJPalindromicPattern;

/*

Category: Hard,
https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/description/
2472. Maximum Number of Non-overlapping Palindrome Substrings
Hard
Topics
Companies
Hint
You are given a string s and a positive integer k.

Select a set of non-overlapping substrings from the string s that satisfy the following conditions:

The length of each substring is at least k.
Each substring is a palindrome.
Return the maximum number of substrings in an optimal selection.

A substring is a contiguous sequence of characters within a string.



Example 1:

Input: s = "abaccdbbd", k = 3
Output: 2
Explanation: We can select the substrings underlined in s = "abaccdbbd". Both "aba" and "dbbd" are palindromes and have a length of at least k = 3.
It can be shown that we cannot find a selection with more than two valid substrings.
Example 2:

Input: s = "adbcda", k = 2
Output: 0
Explanation: There is no palindrome substring of length at least 2 in the string.


Constraints:

1 <= k <= s.length <= 2000
s consists of lowercase English letters.
 */
public class PartJ_C_2_MaximumNumberofNonoverlappinPalindromSubstrings {

    /**
     * Example Input
     * java
     * Copy
     * Edit
     * s = "abacab"
     * k = 2
     * Step 1: DP Table Computation
     * We'll construct a 2D DP table where dp[i][j] = true means s[i:j] is a palindrome.
     *
     * 1️⃣ Single-character palindromes (dp[i][i] = true)
     * Each individual character is a palindrome:
     *
     * i\j	0	1	2	3	4	5
     * 0	✅
     * 1		✅
     * 2			✅
     * 3				✅
     * 4					✅
     * 5						✅
     * 2️⃣ Two-character palindromes (dp[i][i+1] = true if s[i] == s[i+1])
     * "ab" → ❌
     * "ba" → ❌
     * "ac" → ❌
     * "ca" → ❌
     * "ab" → ❌
     * i\j	0	1	2	3	4	5
     * 0	✅	❌
     * 1		✅	❌
     * 2			✅	❌
     * 3				✅	❌
     * 4					✅	❌
     * 5						✅
     * 3️⃣ Multi-character palindromes (dp[i][j] = true if s[i] == s[j] and dp[i+1][j-1] == true)
     * "aba" → ✅ (s[0] == s[2], and "b" is a palindrome)
     * "aca" → ✅ (s[2] == s[4], and "c" is a palindrome)
     * "bacab" → ✅ (s[1] == s[5], and "aca" is a palindrome)
     * i\j	0	1	2	3	4	5
     * 0	✅	❌	✅
     * 1		✅	❌			✅
     * 2			✅	❌	✅
     * 3				✅	❌
     * 4					✅	❌
     * 5						✅
     * Step 2: Greedy Selection (Right to Left)
     * We now find the maximum number of non-overlapping palindromes.
     *
     * Start from the end (i = 5 → 0).
     * Find the first dp[i][j] = true where j < lastUsed.
     * Select that palindrome and update lastUsed = i.
     * Greedy Selection
     * "bacab" (dp[1][5] = ✅) → Count = 1, lastUsed = 1
     * "aba" (dp[0][2] = ✅) → Count = 2, lastUsed = 0
     * Final Output
     * maxPalindromes("abacab", 2) → Output: 2
     */
    public int maxPalindromes(String s, int k) {
        int n = s.length();



        // Step 1: Precompute all palindromes using DP
        boolean dp[][] = new boolean[n][n];

        // Single-character palindromes
        for (int i = 0; i < n; ++i)
            dp[i][i] = true;

        // Two-character palindromes
        for (int i = 0; i < n - 1; ++i) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }

        // Multi-character palindromes (length ≥ 3)
        for (int len = 3; len <= n; ++len) {
            for (int i = 0; i  < n - len +1; ++i) {  // Ensure `j` stays in bounds
                int j = i + len - 1;
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                }
            }
        }

        // Step 2: Greedy Selection from Right to Left
        int count = 0, lastUsed = n;  // Last used position (initialized to `n` for easy handling)

        for (int i = n - 1; i >= 0; i--) {  // Start from the end
            for (int j = i + k - 1; j < n; j++) {
                if (dp[i][j] && j < lastUsed) { // Ensure non-overlapping
                    count++;
                    lastUsed = i; // Update last used index
                    break;  // Move to the next one
                }
            }
        }
        return count;
    }
}
