package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/valid-parentheses/
 * Category: Easy, Must Do
 * Related: https://leetcode.com/problems/longest-valid-parentheses/ Hard
 * https://leetcode.com/problems/remove-invalid-parentheses/ Hard
 * https://leetcode.com/problems/check-if-word-is-valid-after-substitutions/ Medium
 * 
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
Example 4:

Input: s = "([)]"
Output: false
Example 5:

Input: s = "{[]}"
Output: true
 

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
Accepted
1,745,707
Submissions
4,310,724
 */
public class ValidParentheses {
    
    public boolean isValid(String s) {
        /*
         * Runtime: 1 ms, faster than 99.04% of Java online submissions for Valid Parentheses.
Memory Usage: 37.1 MB, less than 72.67% of Java online submissions for Valid Parentheses.
         */
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
