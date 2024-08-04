package com.interview.stackqueue;
import java.util.*;
/*
 * https://leetcode.com/problems/score-of-parentheses/
 * https://www.youtube.com/watch?v=rWsv46ME6lI
 * Category: Medium
 * Related: https://leetcode.com/problems/map-sum-pairs/ Medium good question
 * https://leetcode.com/problems/k-similar-strings/ Hard
 * https://leetcode.com/problems/determine-if-string-halves-are-alike/ good question
 * Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: s = "()"
Output: 1
Example 2:

Input: s = "(())"
Output: 2
Example 3:

Input: s = "()()"
Output: 2
 

Constraints:

2 <= s.length <= 50
s consists of only '(' and ')'.
s is a balanced parentheses string.
 */
public class ScoreofParentheses {
    public int scoreOfParentheses(String s) {
        /*
         * Runtime: 1 ms, faster than 37.29% of Java online submissions for Score of Parentheses.
Memory Usage: 38.3 MB, less than 24.93% of Java online submissions for Score of Parentheses.
Next challenges:
         */
        Stack<Integer> st = new Stack<>();
        int l = s.length();
        for (int i = 0; i < l ; i++) {
           if (s.charAt(i) == '(') {
               st.push(-1); 
           } else {
               if (st.peek() == -1) {
                   st.pop();
                   st.push(1);
               } else {
                   int sum = 0;
                   while(st.peek() != -1) {
                       sum += st.pop();
                   }
                   st.pop();
                   st.push(2*sum);
               }
               
           }
        }
        int sum = 0;
        while(!st.empty()) {
            sum += st.pop();
        }
        
        return sum;

        
    } 
}
