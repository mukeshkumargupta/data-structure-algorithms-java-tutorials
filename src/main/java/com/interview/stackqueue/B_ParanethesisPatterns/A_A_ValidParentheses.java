package com.interview.stackqueue.B_ParanethesisPatterns;

import java.util.*;
/*
 * https://leetcode.com/problems/valid-parentheses/
 * Category: Easy, Must Do, Top150, Facebook, FAANG
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
public class A_A_ValidParentheses {
    /*
     * Runtime: 3 ms, faster than 28.22% of Java online submissions for Valid Parentheses.
Memory Usage: 38.8 MB, less than 23.76% of Java online submissions for Valid Parentheses.
     */
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (isOpeningBracket(ch)) {
                st.push(ch);
            } else {
                if (st.isEmpty() || !isMatchingBracket(st.pop(), ch)) {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    boolean isOpeningBracket(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    boolean isMatchingBracket(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '{' && close == '}') ||
                (open == '[' && close == ']');
    }
    public static void main(String[] args) {
        
    }
    
}
