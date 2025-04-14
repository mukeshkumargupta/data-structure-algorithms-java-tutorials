package com.interview.twopointer;

import java.util.*;
/*
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 * Category: Medium, Google, FAANG
 * Related:
 * https://leetcode.com/problems/longest-word-in-dictionary/ Medium
 * Hint: other solution could be build dictionary and try to solve it.
 * Derived: Find no of deletion to achieve longest word,, in else part of subsequence, count it when result is final 
 * Related: https://leetcode.com/problems/longest-word-in-dictionary/ Medium, Use trie or map to solve this question
 * Given a string s and a string array dictionary, return the longest string in the dictionary that can be formed by deleting some of the given string characters. If there is more than one possible result, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

 

Example 1:

Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
Output: "apple"
Example 2:

Input: s = "abpcplea", dictionary = ["a","b","c"]
Output: "a"
 

Constraints:

1 <= s.length <= 1000
1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 1000
s and dictionary[i] consist of lowercase English letters.
 */
public class LongestWordinDictionarythroughDeleting {
    /*
    Approach 1: Brute Force (Generate All Subsequences)
    Intuition:
    Generate all possible subsequences of s (using recursion).

    Check if any of them exist in the dictionary.

    Return the longest valid word (or the lexicographically smallest one if tied).

    Time Complexity:
    O(2^n * k) → Too slow for large n
    (2^n for generating subsequences, k for checking each in the dictionary)

    Space Complexity:
    O(n) → Recursion stack usage.
     */
    private static class BruitForce {
        public String findLongestWord(String source, List<String> dictionary) {
            Set<String> allSubsequences = new HashSet<>();
            generateSubsequences(source, 0, new StringBuilder(), allSubsequences);

            String longestWord = "";
            for (String word : dictionary) {
                if (allSubsequences.contains(word)) {
                    if (word.length() > longestWord.length() ||
                            (word.length() == longestWord.length() && word.compareTo(longestWord) < 0)) {
                        longestWord = word;
                    }
                }
            }
            return longestWord;
        }

        private void generateSubsequences(String source, int index, StringBuilder current, Set<String> result) {
            if (index == source.length()) {
                result.add(current.toString());
                return;
            }
            generateSubsequences(source, index + 1, current, result); // Exclude current character
            current.append(source.charAt(index));
            generateSubsequences(source, index + 1, current, result); // Include current character
            current.deleteCharAt(current.length() - 1);
        }
    }

    /*
        Better Approach (Sorting + Two-Pointer)

        Intuition:
        - First, we sort the dictionary based on:
          1. Word length (Descending order) → Longer words first.
          2. Lexicographical order (Ascending order) → If same length, pick smallest lexicographically.
        - Then, we iterate through the sorted list and check if each word is a subsequence of `s` using a two-pointer technique.
        - The first valid subsequence is returned as the result.

        Algorithm:
        - Sort the dictionary by length (descending) and lexicographical order (ascending).
        - For each word in the sorted dictionary:
            - Use a two-pointer approach to check if it is a subsequence of `s`.
            - Return the first valid subsequence found.

        Time Complexity (TC):
        - Sorting the dictionary (m words) takes O(m log m).
        - Checking each word as a subsequence takes O(n).
        - Worst case: O(m log m + m * n).

        Space Complexity (SC):
        - O(1) (constant extra space for variables, no additional data structures).

        Final Comparison:

        Approach                         Time Complexity       Space Complexity   Notes
        ------------------------------------------------------------------------------------
        Better (Sorting + Two-Pointer)   O(m log m + m * n)   O(1)                Slower due to sorting.
        Optimal (Direct Two-Pointer)     O(m * n)             O(1)                Avoids sorting, making it faster.
    */
    private static class Better {
        public String findLongestWord(String s, List<String> d) {
            String result = "";
            d.sort((String a, String b) ->
                    a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
            for(String str : d) {
                if(isSubsequence(s, str)) return result = str;
            }
            return result;
        }

        public boolean isSubsequence(String S, String D) {
            int i = 0, j = 0;
            if(D.length() > S.length()) return false;
            while(i < S.length() && j < D.length()) {
                if(S.charAt(i) == D.charAt(j)) j++;
                i++;
            }
            return j == D.length();
        }
    }

    /*
        Optimal Approach (Direct Two-Pointer)

        Intuition:
        - We iterate through the dictionary list `d` and check if each word is a subsequence of `s`.
        - If a word is a valid subsequence, we update the result based on length and lexicographical order.

        Algorithm:
        - Initialize an empty string `result` to store the longest valid word.
        - For each word in `d`:
            - Use a two-pointer approach to check if it is a subsequence of `s`.
            - If valid and longer than `result`, update `result`.
            - If lengths are the same, choose the lexicographically smaller word.

        Time Complexity (TC):
        - No sorting: Eliminates O(m log m).
        - Checking each of the `m` words as a subsequence takes O(n).
        - Total: O(m * n).

        Space Complexity (SC):
        - O(1) (constant extra space for variables).

        Final Comparison:

        Approach                         Time Complexity       Space Complexity   Notes
        ------------------------------------------------------------------------------------
        Better (Sorting + Two-Pointer)   O(m log m + m * n)   O(1)                Slower due to sorting.
        Optimal (Direct Two-Pointer)     O(m * n)             O(1)                Avoids sorting, making it faster.
    */
    private static class Optimal {
        public String findLongestWord(String s, List<String> d) {
            String result = "";

            for(String str : d) {
                if(isSubsequence(s, str) ) {
                    //Length str > result || length is equal but str is lexicographically small.
                    if(str.length() > result.length() || (str.length() == result.length() && str.compareTo(result) < 1))
                        result = str;
                }
            }
            return result;
        }

        public boolean isSubsequence(String S, String D) {
            int i = 0, j = 0;
            if(D.length() > S.length()) return false;
            while(i < S.length() && j < D.length()) {
                if(S.charAt(i) == D.charAt(j)) j++;
                i++;
            }
            return j == D.length();
        }
    }
    
}
