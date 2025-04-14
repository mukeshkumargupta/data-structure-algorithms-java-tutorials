package com.interview.stackqueue;

import java.util.*;
/*
 * https://leetcode.com/problems/basic-calculator-ii/
 * https://www.youtube.com/watch?v=gmy6L9vHTbo
 * Category: Medium, Top150, Facebook, FAANG
 * Related: https://leetcode.com/problems/basic-calculator/ Hard
 * https://leetcode.com/problems/expression-add-operators/ Hard
 * https://leetcode.com/problems/basic-calculator-iii/ Hard
 * 
 * Given a string s which represents an expression, evaluate this expression and return its value. 

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

 

Example 1:

Input: s = "3+2*2"
Output: 7
Example 2:

Input: s = " 3/2 "
Output: 1
Example 3:

Input: s = " 3+5 / 2 "
Output: 5
 

Constraints:

1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
 * 
 */
public class BasicCalculatorII {
    public int calculate(String s) {
        /*
         * Runtime: 23 ms, faster than 18.67% of Java online submissions for Basic Calculator II.
Memory Usage: 42.1 MB, less than 17.17% of Java online submissions for Basic Calculator II.
         */
        if (s == null || s.length() == 0) {
            return 0;
        }
        Stack<Integer> st = new Stack<>();
        
        char sigh = '+';
        int l = s.length();
        for (int i = 0; i < l; i++) {
            int val = 0;
            if (Character.isDigit(s.charAt(i))) {
                while (i <l && Character.isDigit(s.charAt(i))) {
                    val = val*10 + (s.charAt(i) - '0');
                   // System.out.println(val);
                    
                    
                    i++;
                }
                i--;//Note since out loop is doing actual increment so inner loop will make one extra so decrement one time 
                //System.out.println(sigh);
                //System.out.println(val);
                if (sigh == '+') {
                    st.push(val);
                } else if (sigh == '-') {
                    st.push(val*-1);
                }  else if (sigh == '*') {
                    
                    if (!st.empty()) {
                        
                      st.push(st.pop()*val);  
                    }
                }  else if (sigh == '/') {
                    if (!st.empty()) {
                      st.push(st.pop()/val);  
                    }
                }
            } else if (s.charAt(i) != ' ') {//ignore space
                
                sigh = s.charAt(i);
                
                
            } 
        }
        int result = 0;
        while (!st.empty()) {
            result += st.pop();
        }
        return result;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
