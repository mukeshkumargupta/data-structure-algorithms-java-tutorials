package com.interview.dynamic;


/*
 * 🔗 Problem: https://leetcode.com/problems/longest-palindromic-substring/
 * 📌 Category: Medium, Must Do, Google, Top 150, DP
 * 🎥 Video Explanation: https://www.youtube.com/watch?v=UflHuQj6MVA
 *
 * 🔗 Related Problems:
 *   - https://leetcode.com/problems/palindrome-permutation/ (Easy)
 *   - https://leetcode.com/problems/palindrome-pairs/ (Hard)
 *   - https://leetcode.com/problems/palindromic-substrings/ (Medium)
 *
 * 📝 Description:
 * Given a string `s`, return the longest palindromic substring in `s`.
 * A palindrome is a string that reads the same forward and backward.
 *
 * ---
 * 🔢 Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * 🔢 Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * 🔢 Example 3:
 * Input: s = "a"
 * Output: "a"
 *
 * 🔢 Example 4:
 * Input: s = "ac"
 * Output: "a"
 *
 * ---
 * 🎯 Constraints:
 * - 1 <= s.length <= 1000
 * - s consists of only digits and English letters.
 */
public class PartJ_B_LongestPalindromicSubstring {

    /*
     * 📌 **Problem: Longest Palindromic Substring**
     * - This problem requires finding the longest substring within a given string that is a palindrome.
     *
     * 🔗 **Related Problem**:
     *   - https://leetcode.com/problems/palindrome-partitioning-iv/submissions/1544940743/
     *
     * ---
     * 🛠 **Approach 1: Brute Force**
     *
     * ✅ **Explanation**:
     *   - This approach checks every possible substring of the input string to determine if it is a palindrome.
     *   - It verifies whether each substring reads the same forwards and backwards.
     *
     * 🔢 **Algorithm Steps**:
     *   1️⃣ Generate all possible substrings of the input string.
     *   2️⃣ For each substring, check if it is a palindrome.
     *   3️⃣ Keep track of the longest palindromic substring found.
     *
     * ⏳ **Time Complexity**:
     *   - **O(n³)**:
     *     - Generating all substrings takes **O(n²)**.
     *     - Checking each substring for palindrome property takes **O(n)**.
     *
     * 🛠 **Space Complexity**:
     *   - **O(1)**: Only a few variables are used; no additional data structures are needed.
     */
    public class Bruitforce {
        public String longestPalindrome(String s) {
            int n = s.length();
            String longest = "";

            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    String substring = s.substring(i, j + 1);
                    if (isPalindrome(substring) && substring.length() > longest.length()) {
                        longest = substring;
                    }
                }
            }

            return longest;
        }

        private boolean isPalindrome(String str) {
            int left = 0, right = str.length() - 1;
            while (left < right) {
                if (str.charAt(left) != str.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
    /*
     * 📌 **Performance Metrics**:
     *   ✅ Runtime: 115 ms (Faster than 40.43% of Java submissions)
     *   ✅ Memory Usage: 43 MB (Less than 31.54% of Java submissions)
     *
     * 📌 **Time Complexity**:
     *   - **O(n²)**: Filling the DP table requires checking all substrings, leading to a quadratic time complexity.
     *
     * 📌 **Space Complexity**:
     *   - **O(n²)**: A 2D DP table is used to store palindromic states.
     *
     * ---
     * 📌 **Functionality**:
     * - This function **prints** the **longest palindromic substring** of a given string.
     * - It also **returns** the **length** of the longest palindrome.
     */
    public static class Optimized {
        public String longestPalindrome(String str)
        {
            // get length of input string
            int n = str.length();

            // table[i][j] will be false if
            // substring str[i..j] is not palindrome.
            // Else table[i][j] will be true
            boolean dp[][] = new boolean[n][n];

            // All substrings of length 1 are palindromes
            int maxLength = 1;
            for (int i = 0; i < n; ++i)
                dp[i][i] = true;

            // check for sub-string of length 2.
            int start = 0;
            for (int i = 0; i < n - 1; ++i) {
                if (str.charAt(i) == str.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    start = i;
                    maxLength = 2;
                }
            }

            // Check for lengths greater than 2.
            // k is length of substring
            for (int k = 3; k <= n; ++k) {

                // Fix the starting index
                for (int i = 0; i < n - (k - 1); ++i) {
                    // Get the ending index of substring from
                    // starting index i and length k
                    int j = i + k - 1;

                    // checking for sub-string from ith index to
                    // jth index iff str.charAt(i+1) to
                    // str.charAt(j-1) is a palindrome
                    if (dp[i + 1][j - 1]
                            && str.charAt(i) == str.charAt(j)) {
                        dp[i][j] = true;

                        if (k > maxLength) {
                            start = i;
                            maxLength = k;
                        }
                    }
                }
            }
            return str.substring(start, start + maxLength);

        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }
    }



}

