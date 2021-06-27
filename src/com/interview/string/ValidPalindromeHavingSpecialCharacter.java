package com.interview.string;

/*
 * Reference: https://leetcode.com/problems/valid-palindrome/
 * Category: Easy, Tricky
 */
public class ValidPalindromeHavingSpecialCharacter {
    boolean isLetterOrDigit(Character ch) {
        int smallCharIndex = ch - 'a';
        int bigCharIndex = ch - 'A';
        int intCharIndex = ch - '0';
        if ((smallCharIndex <= 25 && smallCharIndex >=0) || (bigCharIndex <= 25 && bigCharIndex >=0) || (intCharIndex <= 9 && intCharIndex >=0) ) {
            return true;
            
        }
        return false;
        
    }
    public boolean isPalindrome(String s) {
        int len = s.length();
        int i = 0;
        int j = len -1;
        while( i < j) {
            if (!isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }
            if (!isLetterOrDigit(s.charAt(j))) {
                j--;
                continue;
            }
            
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
