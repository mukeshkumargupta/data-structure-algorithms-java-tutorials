package com.interview.recursionBacktracking.F_ParenthesisPatterns;

import java.util.*;

/*
 * 🔥 Problem: Remove Invalid Parentheses
 * https://leetcode.com/problems/remove-invalid-parentheses/
 *
 * 💡 Description:
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 *
 * 🧠 Category: Hard
 *
 * 🔗 Related Problems:
 * - https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/
 *
 * 📥 Input:
 * A string `s` that contains parentheses and letters.
 *
 * 🎯 Goal:
 * Remove the minimum number of invalid parentheses to make the input valid.
 * Return all **unique** valid strings with the **minimum** number of removals.
 * You may return the result in any order.
 *
 * 🧪 Examples:
 *
 * Example 1:
 * Input: s = "()())()"
 * Output: ["(())()", "()()()"]
 *
 * Example 2:
 * Input: s = "(a)())()"
 * Output: ["(a())()", "(a)()()"]
 *
 * Example 3:
 * Input: s = ")("
 * Output: [""]
 *
 * 🔒 Constraints:
 * - 1 <= s.length <= 25
 * - s consists of lowercase English letters and parentheses '(' and ')'.
 * - There will be at most 20 parentheses in s.
 */
public class B_RemoveInvalidParenthesis {

    public List<String> removeInvalidParentheses(String s) {
        Set<String> res = new HashSet<>();
        int rmL = 0, rmR = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') rmL++;
            if(s.charAt(i) == ')') {
                if(rmL != 0) rmL--;
                else rmR++;
            }
        }
        DFS(res, s, 0, rmL, rmR, 0, new StringBuilder());
        return new ArrayList<String>(res);
    }

    public void DFS(Set<String> res, String s, int i, int rmL, int rmR, int open, StringBuilder sb) {
        if(i == s.length() && rmL == 0 && rmR == 0 && open == 0) {
            res.add(sb.toString());
            return;
        }
        if(i == s.length() || rmL < 0 || rmR < 0 || open < 0) return;

        char c = s.charAt(i);
        int len = sb.length();

        if(c == '(') {
            DFS(res, s, i + 1, rmL - 1, rmR, open, sb);
            DFS(res, s, i + 1, rmL, rmR, open + 1, sb.append(c));

        } else if(c == ')') {
            DFS(res, s, i + 1, rmL, rmR - 1, open, sb);
            DFS(res, s, i + 1, rmL, rmR, open - 1, sb.append(c));

        } else {
            DFS(res, s, i + 1, rmL, rmR, open, sb.append(c));
        }

        sb.setLength(len);
    }

    public static void main(String args[]) {

        String s = "(r(()()(";
        B_RemoveInvalidParenthesis rmp = new B_RemoveInvalidParenthesis();
        List<String> result = rmp.removeInvalidParentheses(s);
        result.forEach(s1 -> System.out.println(s1));
    }
}
