package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/backspace-string-compare/
 * Category: Easy
 * 
 * 
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

 

Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a##c", t = "#a#c"
Output: true
Explanation: Both s and t become "c".
Example 4:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".
 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        int len1 = s.length();
        int len2 = t.length();
        for (int i = 0; i < len1 ; i++) {
            if (s.charAt(i) == '#') {
                if (!stack1.isEmpty()) {
                    stack1.pop(); 
                }
                
            } else {
                stack1.push(s.charAt(i));
                
            }
        }
        
       for (int i = 0; i < len2 ; i++) {
            if (t.charAt(i) == '#') {
                if (!stack2.isEmpty()) {
                    stack2.pop();
                    
                }
                
            } else {
                stack2.push(t.charAt(i));
                
            }
        }
        
        //Compare both stack
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            if (stack1.pop() == stack2.pop()) {
                continue; 
            } else {
                return false;
            }
            
        }
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return true;
        } else {
            return false;
        }
        
    }
}
