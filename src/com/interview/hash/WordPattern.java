package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/word-pattern/
 * Category: Easy, Google, Must Do
 * Given a pattern and a string s, find if s follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

 

Example 1:

Input: pattern = "abba", s = "dog cat cat dog"
Output: true
Example 2:

Input: pattern = "abba", s = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", s = "dog dog dog dog"
Output: false
 

Constraints:

1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lower-case English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        /*
         * 100% fast
         */
        int l1 = pattern.length();
        String[] splittedArray = s.split(" ");
        if (l1 != splittedArray.length) {
            return false;
        }
        
        Map<Character, String> lookup = new HashMap<>();
        Map<String, Character> reverseLookup = new HashMap<>();
        
        for (int i = 0; i < l1; i++) {
            Character ch = pattern.charAt(i);
            if (!lookup.containsKey(ch)) {
                lookup.put(ch, splittedArray[i]);
                if (!reverseLookup.containsKey(splittedArray[i])) {
                    reverseLookup.put(splittedArray[i], ch);
                } else {//endure two are not mapp with same char
                    if (reverseLookup.get(splittedArray[i]) != ch) {
                        return false;
                    }
                }
                
            } else {
                if (!lookup.get(ch).equals(splittedArray[i]) ) {
                    return false;
                }
            }
            
        }
        return true;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
