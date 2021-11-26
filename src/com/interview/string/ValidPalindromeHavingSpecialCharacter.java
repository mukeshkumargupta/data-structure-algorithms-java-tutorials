package com.interview.string;

/*
 * Reference: https://leetcode.com/problems/valid-palindrome/
 * Category: Easy, Tricky, Facebook
 * 
 * Related: https://leetcode.com/problems/valid-palindrome-ii/ Easy
 * https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/ Medium
 * 
 * Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 

Example 1:

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
Example 2:

Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.
 

Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
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
        /*
         * Runtime: 4 ms, faster than 66.59% of Java online submissions for Valid Palindrome.
Memory Usage: 40.4 MB, less than 32.00% of Java online submissions for Valid Palindrome.
         */
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
