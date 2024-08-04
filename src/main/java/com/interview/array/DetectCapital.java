package com.interview.array;

/*
 * https://leetcode.com/problems/detect-capital/
 * Category: Easy, Google, 
 * Related: https://leetcode.com/problems/word-pattern-ii/ Medium
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/ Medium
 * https://leetcode.com/problems/parse-lisp-expression/ Hard
 * We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Given a string word, return true if the usage of capitals in it is right.

 

Example 1:

Input: word = "USA"
Output: true
Example 2:

Input: word = "FlaG"
Output: false
 */
public class DetectCapital {
    boolean isValid(int i) {
        if (i >= 0 && i <= 25) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean detectCapitalUse(String word) {
        /*
         * Runtime: 1 ms, faster than 98.91% of Java online submissions for Detect Capital.
Memory Usage: 37.4 MB, less than 67.36% of Java online submissions for Detect Capital.
         */
        int l = word.length();
        
        //if first is capital and second is also capital then all shoud be capital
        boolean firstCap = l > 0 && isValid(word.charAt(0) - 'A');
        boolean secondCap = l > 1 &&  isValid(word.charAt(1) - 'A');
        if (firstCap && secondCap) {
            for (int i = 2; i < l; i++) {
                //then all cap
                if (!isValid(word.charAt(i) -'A')) {
                   return false; 
                }
            }
        } else if (!firstCap) {
            for (int i = 1; i < l; i++) {
                //then all small
                if (!isValid(word.charAt(i) -'a')) {
                   return false; 
                }
            }
        } else  {
            for (int i = 1; i < l; i++) {
                //then all small
                if (!isValid(word.charAt(i) -'a')) {
                   return false; 
                }
            }
        }
        return true;
 
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
