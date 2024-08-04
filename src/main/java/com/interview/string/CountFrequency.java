package com.interview.string;

import java.util.*;
public class CountFrequency {
    int method1(String s1, String s2) {
        Map<String, Integer> mp1 = new HashMap<>();
        
        
        int l = s1.length();
        StringBuilder sb = null;
        int i = 0;
        while (i < l) {
            sb = new StringBuilder();
            while (i < l && s1.charAt(i) != ' ') {
                sb.append(s1.charAt(i));
                i++;
            }
            
            mp1.put(sb.toString(), mp1.getOrDefault(sb.toString(), 0) + 1);
            i++;
        }

        
        return mp1.get(s2);
        
    }
    int method2(String s1, String s2) {
        Map<String, Integer> mp1 = new HashMap<>();

        String[] splitted = s1.split("\\s+"); 
 
        for (String elm: splitted) {
            mp1.put(elm, mp1.getOrDefault(elm, 0) + 1);
        }
        
        return mp1.get(s2);
        
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        CountFrequency cf = new CountFrequency();
        System.out.println(cf.method1("This is India is is", "is"));
        System.out.println(cf.method2("This is India is is", "is"));
        
    }
    
}
