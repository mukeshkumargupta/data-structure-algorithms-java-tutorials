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
    public int countSubstrings(String s) {
        /*
         * Runtime: 7 ms, faster than 56.77% of Java online submissions for Palindromic Substrings.
Memory Usage: 38.9 MB, less than 45.42% of Java online submissions for Palindromic Substrings.
         */
        String str = s;
        // get length of input string
        int n = str.length();
 
        // table[i][j] will be false if
        // substring str[i..j] is not palindrome.
        // Else table[i][j] will be true
        boolean table[][] = new boolean[n][n];
 
        // All substrings of length 1 are palindromes
        int totalCount = n;
        for (int i = 0; i < n; ++i)
            table[i][i] = true;
 
        // check for sub-string of length 2.
        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                table[i][i + 1] = true;
                totalCount++;
            }
        }
 
        // Check for lengths greater than 2.
        // k is length of substring
        for (int k = 3; k <= n; ++k) {
 
            // Fix the starting index
            for (int i = 0; i < n - k + 1; ++i) {
                // Get the ending index of substring from
                // starting index i and length k
                int j = i + k - 1;
 
                // checking for sub-string from ith index to
                // jth index iff str.charAt(i+1) to
                // str.charAt(j-1) is a palindrome
                if (table[i + 1][j - 1]
                    && str.charAt(i) == str.charAt(j)) {
                    table[i][j] = true;
                    totalCount++;
                }
            }
        }
        return totalCount;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
