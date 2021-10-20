package com.interview.hash;

import java.util.*;
/*
 *https://leetcode.com/problems/find-all-anagrams-in-a-string/
 *Category: Medium, Must Do
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
