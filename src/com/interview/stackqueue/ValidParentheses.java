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
    /*
     * Runtime: 3 ms, faster than 28.22% of Java online submissions for Valid Parentheses.
Memory Usage: 38.8 MB, less than 23.76% of Java online submissions for Valid Parentheses.
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i = 0; i< len; i++) {
            Character c = s.charAt(i);
            if (c == '(' || c == '{'  || c == '[' ) {
                stack.push(c);
            } else {
                if (stack.empty()) return false;
                
                if (c == ')' && '(' != stack.pop()) {
                  return false;
                }
                if (c == '}' && '{' != stack.pop()) {
                  return  false;
                }
                if (c == ']' && '[' != stack.pop()) {
                  return false;
                }

            }
        }
        return stack.empty();
    }
    public static void main(String[] args) {
        
    }
    
}
