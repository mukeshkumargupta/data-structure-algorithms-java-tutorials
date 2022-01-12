package com.interview.hash;

import java.util.*;
/*
 *https://leetcode.com/problems/find-all-anagrams-in-a-string/
 *Category: Medium, Must Do
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
    boolean isMapEqual(Map<Character, Integer> m1, Map<Character, Integer> m2) {
        boolean isEqual = true;
        for (Character key: m2.keySet()) {
            if (m1.get(key) != m2.get(key)) {//Compare
                isEqual = false;
            }
            
        }
        return isEqual;
        
        
    }
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        int l1 = s.length();
        int l2 = p.length();
        List<Integer> result = new ArrayList();
        if (l2 > l1) {
            return result;
        }
        for (int i = 0; i < l2; i++) {
            Character ch = p.charAt(i);
             m2.put(ch, m2.getOrDefault(ch, 0) +1);
            
            ch = s.charAt(i);
            m1.put(ch, m1.getOrDefault(ch, 0) +1);
        }


        //Now comparte and accumulate result
        if (isMapEqual(m1, m2)) {
            result.add(0);
            
        }
        int start = 0;
        for (int end = l2; end < l1; end++) {
            //Reduce one count
            Character startChar = s.charAt(start++);
            m1.put(startChar, m1.getOrDefault(startChar, 0) -1);
            
            //m1.remove(s.charAt(start));
            Character ch = s.charAt(end);
            m1.put(ch, m1.getOrDefault(ch, 0) +1);
            if (isMapEqual(m1, m2)) {
                result.add(start);
            
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
