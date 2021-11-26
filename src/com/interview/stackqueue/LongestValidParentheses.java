package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/longest-valid-parentheses/
 * https://www.youtube.com/watch?v=VdQuwtEd10M
 * Category: Hard, Must Do
 * Related: https://leetcode.com/problems/beautiful-arrangement/ Medium
 * https://leetcode.com/problems/count-different-palindromic-subsequences/ Hard
 * https://leetcode.com/problems/basic-calculator-iv/ Hard
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 

Example 1:

Input: s = "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()".
Example 2:

Input: s = ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()".
Example 3:

Input: s = ""
Output: 0
 

Constraints:

0 <= s.length <= 3 * 104
s[i] is '(', or ')'.
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        /*
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Longest Valid Parentheses.
Memory Usage: 39 MB, less than 77.54% of Java online submissions for Longest Valid Parentheses.
TC: O(N)
SC: O(1)
         */
        
        int open = 0, close = 0;
        int l = s.length();
        int max = 0;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                max = Math.max(max, open + close);
            }
            if (close > open) {
                open = 0;
                close = 0;
            }
        }
        
        open = 0;
        close = 0;
        for (int i = l-1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                open++;
            } else {
                close++;
            }
            if (open == close) {
                max = Math.max(max, open + close);
            }
            if (open > close) {
                open = 0;
                close = 0;
            }
        }
        return max;
        
    }
    public int longestValidParenthesesSlow(String s) {
        /*
         * Runtime: 2 ms, faster than 79.66% of Java online submissions for Longest Valid Parentheses.
Memory Usage: 39.1 MB, less than 68.71% of Java online submissions for Longest Valid Parentheses.
TC: O(N)
SC: O(N)
         */
        Stack<Integer> st = new Stack<>();
        
        st.push(-1);
        int l = s.length();
        int max = 0;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '(') {
               st.push(i);
            } else {
                st.pop();
                if (st.empty()) {
                    st.push(i);
                } else {
                    max = Math.max(max, i - st.peek());
                }
            }
            
        }
        return max;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
