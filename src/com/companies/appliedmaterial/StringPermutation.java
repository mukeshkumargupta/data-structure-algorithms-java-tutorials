package com.companies.appliedmaterial;

import java.util.*;
public class StringPermutation {
    public static boolean arePermutation(String s1, String s2) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        
        
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 != l2) {
            return false;
        }
        for (int i = 0; i < l1; i++) {
            m1.put(s1.charAt(i), m1.getOrDefault(s1.charAt(i), 0) +1);
        }
        for (int i = 0; i < l2; i++) {
            m2.put(s2.charAt(i), m2.getOrDefault(s2.charAt(i), 0) +1);
        }
        for (Character c : m1.keySet()) {
            if (m1.get(c) != m2.get(c)) {
                return false;
            }
        }
        return true;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //arePermutation("geeksforgeeks", "forgeeksgeeks");
        arePermutation("test", "ttew");
    }
    
}
