package com.interview.string;

/**
 * Date 04/09/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 *
 * https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (!isAlphaNum(s.charAt(start))) {
                start++;
            } else if (!isAlphaNum(s.charAt(end))) {
                end--;
            } else {
                if (Character.toLowerCase(s.charAt(start++)) != Character.toLowerCase(s.charAt(end--))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isAlphaNum(char ch) {
        if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
            return true;
        }
        return false;
    }
    
    public boolean isPalindrome_M1(String s) {
        int len = s.length();
        int i = 0;
        int j = len -1;
        while( i < j) {
            //if (!isAlphaNumeric(s.charAt(i))) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                i++;
                continue;
            }
            //if (!isAlphaNumeric(s.charAt(j))) { //Note both API working
            if (!Character.isLetterOrDigit(s.charAt(j))) {
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
    
    boolean isAlphaNumeric(char ch) {
        if ((ch >= 'a' && ch <= 'z') ||  (ch >= 'A' && ch <= 'Z')  ||  (ch >= '0' && ch <= '9') ) {
            return true;
        } else {
            return false;
        }
        
    }
        
    
    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();
        //System.out.println(vp.isPalindrome_M1("1ABc2, ;2CBa1"));
        System.out.println(vp.isPalindrome_M1("A man, a plan, a canal: Panama"));
        
        
        
    }
}