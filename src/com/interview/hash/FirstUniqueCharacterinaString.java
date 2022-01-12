package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/zigzag-conversion/ Medium
 * https://leetcode.com/problems/design-phone-directory/ Medium
 * https://leetcode.com/problems/design-in-memory-file-system/ Medium
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

 

Example 1:

Input: s = "leetcode"
Output: 0
Example 2:

Input: s = "loveleetcode"
Output: 2
Example 3:

Input: s = "aabb"
Output: -1
 

Constraints:

1 <= s.length <= 105
s consists of only lowercase English letters.
 * 
 */
public class FirstUniqueCharacterinaString {
    public int firstUniqChar(String s) {//runtime 65.5 but optimize maintaining queue to get order of 1 like get first unique in running stream 
        Map<Character, Integer> lookup = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (!lookup.containsKey(ch)) {
                lookup.put(ch, i);
            } else {
                lookup.put(ch, -1);
            }  
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            char ch = (char)('a' + i);
            if (lookup.containsKey(ch)) {
                
                if (lookup.get(ch) != -1) {
                   if (min > lookup.get(ch)) {
                       min = lookup.get(ch);
                   } 
                }
            }
        }
        if (min != Integer.MAX_VALUE) {
            return min;
        } else {
            return -1;
        }
    }
}
