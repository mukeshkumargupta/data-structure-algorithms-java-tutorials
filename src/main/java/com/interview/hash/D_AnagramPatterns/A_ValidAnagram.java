package com.interview.hash.D_AnagramPatterns;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/valid-anagram
 * Category: Easy, Must Do, String, Hashing
 *
 * Related:
 * https://leetcode.com/problems/group-anagrams/ Medium
 * https://leetcode.com/problems/palindrome-permutation/ Easy
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/ Medium
 * https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/ Easy
 *
 * 📝 Problem:
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * 📘 Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * 📘 Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 *
 * ✅ Constraints:
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 *
 * 🧠 Optimal Approach:
 * - Use an array of size 26 to count characters in `s` and subtract for `t`.
 * - Return false if any count becomes non-zero.
 *
 * ⏱️ Time Complexity: O(N)
 * 🔁 Space Complexity: O(1) — constant space for 26 characters.
 */
public class A_ValidAnagram {
    /*
    This is generic code it is application for aA or Aa both are anagram
     */
    public boolean isAnagram(String s, String t) {
        int length1 = s.length();
        int length2 = t.length();
        
       if (length1 != length2) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for (int i = 0; i < length1; i++) {
            // int nCount1 = map.get(s.charAt(i));

            if (map.containsKey(s.charAt(i))) {
                int nCount1 = map.get(s.charAt(i));
                map.put(s.charAt(i), ++nCount1);
            } else {
                map.put(s.charAt(i), 1);
            }

            if (map.containsKey(t.charAt(i))) {
                int nCount2 = map.get(t.charAt(i));
                map.put(t.charAt(i), --nCount2);
            } else {
                map.put(t.charAt(i), -1);
            }
        }
        for (Integer j : map.values()) {
            System.out.println(j);
            if (j != 0) {

                return false;
            }
        }
        return true; 
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
