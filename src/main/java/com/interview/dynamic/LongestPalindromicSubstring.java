package com.interview.dynamic;

/*
 * https://leetcode.com/problems/longest-palindromic-substring/
 * Category: Medium, Must Do, Google, Top150, dp
 * Video: https://www.youtube.com/watch?v=UflHuQj6MVA
 * Related: https://leetcode.com/problems/palindrome-permutation/ Easy
 * https://leetcode.com/problems/palindrome-pairs/ Hard
 * https://leetcode.com/problems/palindromic-substrings/ Medium 
 * 
 * Given a string s, return the longest palindromic substring in s.
 * 

 

Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
Example 3:

Input: s = "a"
Output: "a"
Example 4:

Input: s = "ac"
Output: "a"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 * 
 */
public class LongestPalindromicSubstring {

    /*
    The "Longest Palindromic Substring" problem is a classic algorithmic challenge that requires finding the longest substring within a given string that is a palindrome. Below, I outline three approaches: brute force, a better approach using dynamic programming, and an optimized approach using center expansion.

    1. Brute Force Approach
    Explanation: The brute force approach checks every possible substring of the given string to determine if it is a palindrome. For each substring, it verifies whether the characters read the same forwards and backwards.

    Algorithm Steps:

    Generate all possible substrings of the input string.
    For each substring, check if it is a palindrome.
    Keep track of the longest palindromic substring found.
    Time Complexity:

    O(n^3): Generating substrings takes O(n^2), and checking if each substring is a palindrome takes O(n).
    Space Complexity:

    O(1): Only a few variables are used for storage, but no additional data structures are needed.
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
     * Runtime: 115 ms, faster than 40.43% of Java online submissions for Longest Palindromic Substring.
Memory Usage: 43 MB, less than 31.54% of Java online submissions for Longest Palindromic Substring.
* Time Complexity:

O(n^2): Filling the DP table takes quadratic time.
Space Complexity:

O(n^2): A 2D array is used to store palindromic states.

     */
 // This function prints the longest
    // palindrome substring of str[].
    // It also returns the length of the
    // longest palindrome
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
