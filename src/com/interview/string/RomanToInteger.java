package com.interview.string;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/roman-to-integer/
 * Category: Easy
 */


public class RomanToInteger {
    
    public int romanToInt(String s) {
        Map<Character, Integer> valueLookUp = new HashMap<>();
        valueLookUp.put('I', 1);
        valueLookUp.put('V', 5);
        valueLookUp.put('X', 10);
        valueLookUp.put('L', 50);
        valueLookUp.put('C', 100);
        valueLookUp.put('D', 500);
        valueLookUp.put('M', 1000);
        int length = s.length();
        int result = 0;
        for (int i = 0; i < length; i++) {
            //Check if first  character is less than next then evaluate next two character
            if ((i+1 < length) && valueLookUp.get(s.charAt(i)) < valueLookUp.get(s.charAt(i+1))) {
                result = result - valueLookUp.get(s.charAt(i));
                result = result + valueLookUp.get(s.charAt(i+1));
                //Jump i by one
                i++;
            } else {
                result = result + valueLookUp.get(s.charAt(i));
            } 
        }
        return result;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RomanToInteger ri = new RomanToInteger();
        ri.romanToInt("MCMXCIV");
        //1000+100+1000+10+
        
    }
    
}
