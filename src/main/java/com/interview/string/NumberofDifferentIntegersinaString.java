package com.interview.string;

import java.util.*;
/*
 * https://leetcode.com/problems/number-of-different-integers-in-a-string/
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/design-in-memory-file-system/ Hard
 * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/ Medium
 * https://leetcode.com/problems/number-of-good-pairs/ Easy
 * You are given a string word that consists of digits and lowercase English letters.

You will replace every non-digit character with a space. For example, "a123bc34d8ef34" will become " 123  34 8  34". Notice that you are left with some integers that are separated by at least one space: "123", "34", "8", and "34".

Return the number of different integers after performing the replacement operations on word.

Two integers are considered different if their decimal representations without any leading zeros are different.

 

Example 1:

Input: word = "a123bc34d8ef34"
Output: 3
Explanation: The three different integers are "123", "34", and "8". Notice that "34" is only counted once.
Example 2:

Input: word = "leet1234code234"
Output: 2
Example 3:

Input: word = "a1b01c001"
Output: 1
Explanation: The three integers "1", "01", and "001" all represent the same integer because
the leading zeros are ignored when comparing their decimal values.
 */
public class NumberofDifferentIntegersinaString {
    public int numDifferentIntegers(String word) {//73% runtime
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < word.length(); i++)
            if(word.charAt(i) >= 'a' && word.charAt(i) <= 'z')
                sb.append(" ");
            else
                sb.append(word.charAt(i));
        String[] words = sb.toString().split(" ");
        for(String s : words) {
            if (!s.equals("")) {//ignore this case
               set.add(trimZeroes(s)); 
            }
        }
                
        return set.size();
    }
    private String trimZeroes(String s){
        if(s.length() == 0 || s.charAt(0) == ' ')
            return "";
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while(index < s.length() && s.charAt(index++) == '0');
        return s.substring(index-1, s.length());
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
