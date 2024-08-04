package com.interview.string;

/*
 * Reference: https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/
 * Category: Easy
 */
public class CheckIfTwoStringArraysareEquivalent {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int l1 = word1.length;
        int l2 = word2.length;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < l1; i++) {
           sb1.append(word1[i]); 
        }
        for (int i = 0; i < l2; i++) {
           sb2.append(word2[i]); 
        }

        String s1 = sb1.toString();
        String s2 = sb2.toString();
        return s1.equals(s2);
        
    }
}
