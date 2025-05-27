package com.interview.linklist.PartABasicLinkedListOperations.F_PalindromePatterns;

/*
 * Reference: https://leetcode.com/problems/valid-palindrome/
 * Category: Easy, Tricky, Facebook, Top150
 * 
 * Related:
 * https://leetcode.com/problems/palindrome-linked-list/ Easy
 * https://leetcode.com/problems/valid-palindrome-ii/ Easy
 * https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/ Medium
 * https://leetcode.com/problems/find-first-palindromic-string-in-the-array/ Easy
 * https://leetcode.com/problems/valid-palindrome-iv/ Medium Locked
 * https://leetcode.com/problems/maximum-palindromes-after-operations/ Medium
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
public class B_ValidPalindromeHavingSpecialCharacter {
    boolean isLetterOrDigit(Character ch) {

        if ((ch <= 'z' && ch >='a') || (ch <= 'Z' && ch >='A') || (ch <= '9' && ch >='0') ) {
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
        int start = 0;
        int end = len -1;
        while( start < end) {
            if (!isLetterOrDigit(s.charAt(start))) {
                start++;
                continue;
            }
            if (!isLetterOrDigit(s.charAt(end))) {
                end--;
                continue;
            }

            if (Character.toLowerCase(s.charAt(start)) == Character.toLowerCase(s.charAt(end))) {
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
