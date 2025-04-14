package com.interview.string;

/*
    Problem: Valid Palindrome II
    Link: https://leetcode.com/problems/valid-palindrome-ii/description/
    Video Explanation: https://www.youtube.com/watch?v=wX3-411uJH0
    Category: Easy, Facebook, FAANG

    Given a string s, return true if s can be a palindrome after deleting at most one character.

    Approach:
    - Use two pointers: one at the beginning and one at the end.
    - If characters at both pointers match, move both pointers inward.
    - If they don’t match, try skipping either character and check if the remaining string is a palindrome.
    - If at least one of the two possible deletions results in a palindrome, return true.

    Complexity Analysis:
    - Time Complexity: O(N) as we traverse the string at most twice.
    - Space Complexity: O(1) since we use constant extra space.

    Example 1:
    Input: s = "aba"
    Output: true

    Example 2:
    Input: s = "abca"
    Output: true
    Explanation: You could delete the character 'c'.

    Example 3:
    Input: s = "abc"
    Output: false

    Constraints:
    - 1 <= s.length <= 10^5
    - s consists of lowercase English letters.
*/
public class ValidPalindromeII {
    /*
        The concept behind Valid Palindrome II involves the two-pointer technique and handling one deletion to form a palindrome.
        Below are some custom problem variations that extend this concept:

        1️⃣ Can You Make It a k-Palindrome?
        💡 Problem Statement:
        Given a string s and an integer k, determine if you can make s a palindrome by removing at most k characters.

        Example:
        Input: s = "abca", k = 1
        Output: true
        Explanation: Removing 'b' or 'c' makes "aca" which is a palindrome.
        🔹 Follow-up: Solve it with O(N) time and O(1) space.

        2️⃣ Longest Near-Palindromic Substring
        💡 Problem Statement:
        Given a string s, find the longest contiguous substring of s that can become a palindrome by deleting at most one character.

        Example:
        Input: "abcbda"
        Output: "abcba"
        Explanation: Removing 'd' gives "abcba", which is the longest near-palindromic substring.
        🔹 Follow-up: Can you extend this to k deletions?

        3️⃣ Minimum Deletions to Make a Palindrome
        💡 Problem Statement:
        Given a string s, return the minimum number of deletions needed to make it a palindrome.

        Example:
        Input: "aebcbda"
        Output: 2
        Explanation: Removing 'e' and 'd' makes "abcba", which is a palindrome.
        🔹 Hint: Use Longest Palindromic Subsequence (LPS).

        4️⃣ Can You Make It a k-Mirrored Palindrome?
        💡 Problem Statement:
        A string is k-mirrored if it can become a palindrome after at most k modifications (changing one character to another).
        Find if s can be made a palindrome with at most k modifications.

        Example:
        Input: s = "abcdefa", k = 2
        Output: true
        Explanation: Changing 'b' → 'e' and 'c' → 'd' gives "adefeda", a palindrome.
        🔹 Hint: Use Two-Pointer + Counting Differences.

        5️⃣ Palindromic Partitioning with k Deletions
        💡 Problem Statement:
        Given a string s, return the minimum number of deletions required to split s into palindromic substrings, with at most k deletions allowed.

        Example:
        Input: "abcdeca", k = 2
        Output: 1
        Explanation: Deleting 'b' gives "acdeca", which can be split into "acd" | "eca", both palindromes.
        🔹 Follow-up: Can you solve it in O(N^2) DP?
    */
    boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // Keep moving till characters match
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                // Try deleting 1 character from either direction
                return isPalindrome(s, left + 1, right)
                        || isPalindrome(s, left, right - 1);
            }
        }

        return true;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else return false;
        }
        return true;
    }
}
