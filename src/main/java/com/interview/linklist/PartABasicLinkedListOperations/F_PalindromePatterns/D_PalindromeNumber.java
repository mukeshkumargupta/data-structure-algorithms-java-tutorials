package com.interview.linklist.PartABasicLinkedListOperations.F_PalindromePatterns;

/*
 * Reference: https://leetcode.com/problems/palindrome-number/
 * Category: Easy
 */
public class D_PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        
        String s = "" + x;
        int len = s.length();
        int start = 0;
        int end = len-1;
        while (start <= end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }

        }
        return true;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
