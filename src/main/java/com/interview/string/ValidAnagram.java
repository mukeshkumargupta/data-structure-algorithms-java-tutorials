package com.interview.string;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/valid-anagram
 * Category: Easy
 * 
 */
public class ValidAnagram {
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
