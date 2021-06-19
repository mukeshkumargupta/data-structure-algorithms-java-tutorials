package com.interview.string;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/first-unique-character-in-a-string/
 * Category: Easy
 * Derived: Last unique character in string, iterate from last hint
 */
public class FirstUniqueCharacterinaString {
    public int firstUniqChar(String s) {
        int len = s.length();
        Map<Character, Integer> mapData = new LinkedHashMap<>();
        for (int i = 0; i < len ; i++) {
            Character c = s.charAt(i);
            if (mapData.containsKey(c)) {
               mapData.put(c, mapData.get(c)+1);
            } else {
                mapData.put(c, 1);  
            }
        }
        for (int i = 0; i < len ; i++) {
            Character c = s.charAt(i);
            if (mapData.get(c) == 1) {
                return i;
                
            }
        }
        return -1;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
