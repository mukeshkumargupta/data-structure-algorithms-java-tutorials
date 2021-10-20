package com.interview.hash;

import java.util.*;

/*
 * https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/
 * Category: Easy
 * https://leetcode.com/problems/integer-to-roman/ Medium
 * https://leetcode.com/problems/powerful-integers/ Medium
 * https://leetcode.com/problems/unique-length-3-palindromic-subsequences/ Medium
 */
public class SubstringsofSizeThreewithDistinctCharacters {
    boolean checkUnique(Map<Character, Integer> lookup, int index, String s) {
        //Check last three
        for (int i = index; i >= index -2; i--) {
            //System.out.print(s.charAt(i));
            if (lookup.get(s.charAt(i)) != 1) {
                //System.out.println("----\n");
                return false;
            }
        }
        //System.out.println("----\n");
        return true;
    }
    public int countGoodSubstrings(String s) {//runtime 52.19
        int l = s.length();
        if (l < 3) {
            return 0;
        }
        int count = 0;
        Map<Character, Integer> lookup = new HashMap<>();
        //First check three window
        int i = 0;
        for (; i < 3; i++) {
            lookup.put(s.charAt(i), lookup.getOrDefault(s.charAt(i), 0) + 1); 
            
        }
        if (checkUnique(lookup, i-1, s)) {
            count++;
        }
        //Slide window
        int start = 0;
        for (int j = i; j < l ; j++) {
            //reduce count if cound
            lookup.put(s.charAt(start), lookup.get(s.charAt(start)) -1);
            start++;
            lookup.put(s.charAt(j), lookup.getOrDefault(s.charAt(j), 0) +1);
            if (checkUnique(lookup, j, s)) {
                count++;
            }
        }
        return count;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
