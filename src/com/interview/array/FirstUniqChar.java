package com.interview.array;

import java.util.*;

public class FirstUniqChar {
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
        //Need to optimise
        for (int i = 0; i < len ; i++) {
            Character c = s.charAt(i);
            if (mapData.get(c) == 1) {
                return i;
                
            }
        }
        return -1;
        
    }
    public static void main(String[] args) {
        FirstUniqChar fuc = new FirstUniqChar();
        fuc.firstUniqChar("leetcode");
        
    }
    
}
