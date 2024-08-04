package com.interview.string;

import java.util.*;
/*
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 * Category: medium
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
    boolean isSubsequence(String s, String dictWord) {
        /*
         * Avoided sorting, little optimized
         * TC: NX(for subsequence) where X is average length of String
         * Runtime: 14 ms, faster than 86.05% of Java online submissions for Longest Word in Dictionary through Deleting.
Memory Usage: 40.1 MB, less than 53.88% of Java online submissions for Longest Word in Dictionary through Deleting.
         */
        int l1 = s.length();
        int l2 = dictWord.length();
        if (l2 > l1 ) {
            return false;
        }
        int p1 = 0;
        int p2 = 0;
        while (p1 < l1 && p2 < l2) {
            if (s.charAt(p1) == dictWord.charAt(p2)) {
                p1++;
                p2++;
            } else {
                p1++;
                
            }
        }
        if (p2 == l2) {
            return true;
        } else {
            return false;
        }
        
    }
    public String findLongestWord(String s, List<String> dictionary) {
        
        String result = "";
        for (String word: dictionary) {
            if (isSubsequence(s, word)) {
                if (word.length() > result.length() || (word.length() == result.length() && word.compareTo(result) <= 0) )  {
                    result = word;
                }
            }
        }
        return result;
        
    }
    boolean isSubsequenceM2(String s, String dictWord) {
        /*
         * https://www.youtube.com/watch?v=G2y5bCDAzJk
         * TC: NXLOGN(for sorting dictionalry) + NX(subsequence) where n is list size and x is average length of string
         * Runtime: 21 ms, faster than 46.38% of Java online submissions for Longest Word in Dictionary through Deleting.
Memory Usage: 39.8 MB, less than 71.45% of Java online submissions for Longest Word in Dictionary through Deleting.
         */
        
        int l1 = s.length();
        int l2 = dictWord.length();
        if (l2 > l1 ) {
            return false;
        }
        int p1 = 0;
        int p2 = 0;
        while (p1 < l1 && p2 < l2) {
            if (s.charAt(p1) == dictWord.charAt(p2)) {
                p1++;
                p2++;
            } else {
                p1++;
                
            }
        }
        if (p2 == l2) {
            return true;
        } else {
            return false;
        }
        
    }
    public String findLongestWord(String s, List<String> dictionary) {
        
        Collections.sort(dictionary, (a, b) -> {
            return a.compareTo(b);
        });
        String result = "";
        for (String word: dictionary) {
            if (isSubsequence(s, word)) {
                if (word.length() > result.length())  {
                    result = word;
                }
            }
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
