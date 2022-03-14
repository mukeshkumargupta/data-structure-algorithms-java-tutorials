package com.interview.slidingwindow;

import java.util.*;
/*
 *https://leetcode.com/problems/find-all-anagrams-in-a-string/
 *Category: Medium, Must Do, VVImp
 *Related: https://leetcode.com/problems/minimum-index-sum-of-two-lists/ Easy Imp
 *https://leetcode.com/problems/sort-features-by-popularity/ Medium, Locked but i think map and pq will solve it
 *https://leetcode.com/problems/groups-of-strings/ Hard VImp
 *https://www.youtube.com/watch?v=fYgU6Bi2fRg&t=324s
 *TODO: Need to understand why this is failing
 *Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.
 */

public class FindAllAnagramsinaString {
    public List<Integer> findAnagrams(String s, String p) {
        /*
         * Runtime: 8 ms, faster than 86.97% of Java online submissions for Find All Anagrams in a String.
Memory Usage: 48.1 MB, less than 33.37% of Java online submissions for Find All Anagrams in a String.
         */
        int[] slidingWindowCount = new int[26];
        int[] patternCount = new int[26];
        int l1 = s.length();
        int windowSize = p.length();
        List<Integer> result = new ArrayList();
        if (windowSize > l1) {
            return result;
        }
        //process first window
        int i = 0;
        for (; i < windowSize; i++) {
            slidingWindowCount[s.charAt(i) - 'a'] += 1;
            patternCount[p.charAt(i) - 'a'] += 1;
        }
        //Check both are equals
        if (Arrays.equals(slidingWindowCount, patternCount)) {
            result.add(0);
        }
        
        //process window by window
        for (;i < l1; i++) {
            //encrease by one  due to including window
            slidingWindowCount[s.charAt(i) - 'a'] += 1;
            //reduce by one due to excluding window
            slidingWindowCount[s.charAt(i-windowSize) - 'a'] -= 1;//i-windowSize exclude of window
            //Check both are equals
            if (Arrays.equals(slidingWindowCount, patternCount)) {
                result.add(i-windowSize+1);//here start of window
            }
            
            
        }

        return result;   
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        FindAllAnagramsinaString faas = new FindAllAnagramsinaString();
        //System.out.println(faas.findAnagrams("cbaebabacd", "abc"));
        System.out.println(faas.findAnagrams("aaaaaaaaaa", "aaaaaaaaaaaaa"));

    }
    
}
