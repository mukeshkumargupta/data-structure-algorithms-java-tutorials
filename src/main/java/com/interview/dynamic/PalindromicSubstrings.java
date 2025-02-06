package com.interview.dynamic;

/*
 * https://leetcode.com/problems/palindromic-substrings/
 * Category: Must Do, Top100, 
 * Related: https://leetcode.com/problems/stone-game/ Medium
 * https://leetcode.com/problems/check-if-string-is-transformable-with-substring-sort-operations/ Hard
 * https://leetcode.com/problems/count-the-number-of-consistent-strings/ Easy
 * Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

 

Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
        
public class PalindromicSubstrings {
    /**
     * Dynamic Programming Approach for Palindromic Substrings
     * In this approach, we use a 2D DP table (dp[i][j]) where:
     *
     * dp[i][j] = true means the substring s[i:j] is a palindrome.
     * dp[i][j] = false means it's not a palindrome.
     * Key Observations
     * Base Cases:
     *
     * Single character substrings (dp[i][i] = true) are always palindromes.
     * Two consecutive identical characters (s[i] == s[i+1]) form a palindrome of length 2.
     * Recursive Formula:
     *
     * If s[i] == s[j] and the inner substring s[i+1:j-1] is a palindrome (dp[i+1][j-1]), then s[i:j] is also a palindrome:
     * java
     * Copy
     * Edit
     * dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
     * Filling Order:
     *
     * Fill the DP table diagonally, starting from length 1 up to n.
     *
     * Approach 1: Dynamic Programming (DP) Dry Run
     * Example Input: s = "aaa"
     * We create a DP table dp[i][j], where dp[i][j] is true if s[i:j] (substring from index i to j) is a palindrome.
     *
     * Step 1: Initialize Table for Single Character Palindromes (dp[i][i] = true)
     * Every single character is a palindrome:
     *
     * css
     * Copy
     * Edit
     *     a   a   a
     * a  [T] [ ] [ ]
     * a  [ ] [T] [ ]
     * a  [ ] [ ] [T]
     * Count = 3 (for "a", "a", "a")
     *
     * Step 2: Check for Two Character Palindromes (s[i] == s[i+1])
     * If two consecutive characters are equal, mark dp[i][i+1] = true:
     *
     * css
     * Copy
     * Edit
     *     a   a   a
     * a  [T] [T] [ ]
     * a  [ ] [T] [T]
     * a  [ ] [ ] [T]
     * Count = 5 (for "a", "a", "a", "aa", "aa")
     *
     * Step 3: Check for Length 3 Substrings (s[i] == s[j] && dp[i+1][j-1])
     * css
     * Copy
     * Edit
     *     a   a   a
     * a  [T] [T] [T]
     * a  [ ] [T] [T]
     * a  [ ] [ ] [T]
     * Count = 6 (for "a", "a", "a", "aa", "aa", "aaa")
     *
     * Final DP Table:
     * i \ j	0	1	2
     * 0	✅	✅	✅
     * 1		✅	✅
     * 2			✅
     * Total Count = 6
     */
    public static class PalindromicSubstringsDP {
        public int countSubstrings(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            int count = 0;

            // Check all substrings of length 1 (single character)
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
                count++; // Single characters are palindromes
            }

            // Check all substrings of length 2
            for (int i = 0; i < n - 1; i++) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    count++;
                }
            }

            // Check all substrings of length 3 or more
            for (int length = 3; length <= n; length++) {
                for (int i = 0; i <= n - length; i++) {
                    int j = i + length - 1; // Ending index of substring

                    // Check if s[i:j] is a palindrome
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }
            }

            return count;
        }

        public static void main(String[] args) {
            PalindromicSubstringsDP solution = new PalindromicSubstringsDP();
            System.out.println(solution.countSubstrings("abc"));  // Output: 3 ("a", "b", "c")
            System.out.println(solution.countSubstrings("aaa"));  // Output: 6 ("a", "a", "a", "aa", "aa", "aaa")
        }
    }


    public static class PalindromicSubstringsBetter {
        /**
         * Example Walkthrough
         * Let's analyze "aaa" step by step:
         *
         * Center	Palindromes Found
         * a (0)	"a"
         * a (1)	"a", "aa"
         * a (2)	"a", "aa", "aaa"
         * Total 6 palindromes.
         *
         * Time Complexity Analysis
         * Each index is expanded at most O(n) times.
         * Since we expand around 2n centers (n odd + n-1 even), the total complexity is O(n²).
         * Space Complexity
         * O(1) as we use only a few integer variables.
         * Why is this better than brute force?
         * A brute-force approach generates all substrings (O(n²)) and checks each for being a palindrome (O(n)). That results in O(n³) complexity, which is too slow.
         *
         * Approach 2: Expand Around Center Dry Run
         * We expand palindromes from the center.
         *
         * Step 1: Odd-Length Expansion
         * Expand from "a" at index 0 → Count = 1
         * Expand from "a" at index 1 → Count = 1 + "aaa" → Count = 3
         * Expand from "a" at index 2 → Count = 1 + "aaa" → Count = 6
         * Step 2: Even-Length Expansion
         * Expand from "aa" at index (0,1) → Count = 1
         * Expand from "aa" at index (1,2) → Count = 1
         * Total Count = 6
         *
         * Comparison of Both Approaches
         * Approach	Time Complexity	Space Complexity	Dry Run Result
         * Dynamic Programming	O(n²)	O(n²)	✅ Works correctly, builds a table
         * Expand Around Center	O(n²)	O(1)	✅ Works correctly, no extra space
         */
        public int countSubstrings(String s) {
            if (s == null || s.length() == 0) return 0;

            int count = 0;

            // Consider each index as a potential center
            for (int i = 0; i < s.length(); i++) {
                count += expandAroundCenter(s, i, i);     // Odd-length palindromes
                count += expandAroundCenter(s, i, i + 1); // Even-length palindromes
            }

            return count;
        }

        private int expandAroundCenter(String s, int left, int right) {
            int count = 0;

            // Expand while the characters match
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++; // Found a palindrome
                left--;  // Expand left
                right++; // Expand right
            }

            return count;
        }

        public static void main(String[] args) {
            PalindromicSubstrings solution = new PalindromicSubstrings();
            System.out.println(solution.countSubstrings("abc"));  // Output: 3 ("a", "b", "c")
            System.out.println(solution.countSubstrings("aaa"));  // Output: 6 ("a", "a", "a", "aa", "aa", "aaa")
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
