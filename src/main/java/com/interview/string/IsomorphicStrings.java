package com.interview.string;

import java.util.*;
/*
 * https://leetcode.com/problems/isomorphic-strings/
 * 
 * Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

 

Example 1:

Input: s = "egg", t = "add"
Output: true
Caregory: Easy
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        
        Map<Character, Character> lookup = new HashMap<>();
        Map<Character, Character> reverseLookup = new HashMap<>();
        int l1 = s.length();
        int l2 = t.length();
        if (l1 != l2) {
            return false;
        }
        
        for (int i = 0; i < l1; i++) {
            Character ch = s.charAt(i);
            if (!lookup.containsKey(ch)) {
                lookup.put(ch, t.charAt(i));
                //Map only when if not mapped
                if (!reverseLookup.containsKey(t.charAt(i))) {
                    reverseLookup.put(t.charAt(i), ch);
                } else {
                    if (reverseLookup.get(t.charAt(i)) != ch) {//Ensute two character not mapp with same charatcer
                        //System.out.println(t.charAt(i) + " relookup " + ch);
                        return false;
                    }
                }
                

            } else {
                if (lookup.get(ch) != t.charAt(i)) {
                    System.out.println(ch + " lookup " + t.charAt(i));
                    return false;
                }
            }
            
        }
        return true;
        
        
    }
}
