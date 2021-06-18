package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/valid-parentheses/
 * Category: Easy
 */
public class ValidParentheses {
    
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        boolean bValid = true;
        for (int i = 0; i< len; i++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '{'  || c == '[' ) {
                stack.push(c);
            } else {
                if (stack.empty()) return false;
                
                if (c == ')' && '(' != stack.pop()) {
                  bValid = false;
                }
                if (c == '}' && '{' != stack.pop()) {
                  bValid = false;
                }
                if (c == ']' && '[' != stack.pop()) {
                  bValid = false;
                }

            }
            if (!bValid)  {
                return false;
            }
        }
        return stack.empty() && bValid;
    }
    public static void main(String[] args) {
        
    }
    
}
