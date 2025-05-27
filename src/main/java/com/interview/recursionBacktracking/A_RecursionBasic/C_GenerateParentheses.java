package com.interview.recursionBacktracking.A_RecursionBasic;

import java.util.*;
/*
 * https://www.youtube.com/watch?v=pD2VNU2x8w8&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=8
 * https://leetcode.com/problems/generate-parentheses
 * Category: Medium, Must Do, Top150
 * Related:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/ Medium
 *
 * Problem Statement:
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Approach: Backtracking
 * We generate all possible combinations using backtracking while ensuring the sequence remains valid.
 *
 * We track the count of open and close parentheses.
 * A valid combination must always have open ≤ n and close ≤ open.
 * We explore two choices at each step:
 * 1. Add an opening bracket ( if we haven't used all n open brackets.
 * 2. Add a closing bracket ) if it doesn't exceed the number of open brackets used.
 *
 * Complexity Analysis:
 * Time Complexity: O(4^n / sqrt(n)) → This is related to the Catalan number C(n) = (1/(n+1)) * (2n choose n).
 * Space Complexity: O(4^n / sqrt(n)) due to storing valid combinations.
 *
 * Example Walkthrough:
 * Input: n = 3
 * Output:
 * ["((()))", "(()())", "(())()", "()(())", "()()()"]
 *
 * Recursive Execution (Tree):
 *                         ""
 *                      /       \
 *                   "("         ""
 *                 /     \
 *              "(("      "()"
 *            /     \      \
 *         "((("   "(()"   "()("
 *           \      /  \      \
 *         "((()"  "(())"    "()()"
 *           \       |         |
 *        "((())"  "(()())"  "()()()"
 *           \        |
 *        "((()))"  "(())()"
 *
 * Key Takeaways:
 * - Backtracking efficiently generates valid sequences.
 * - Use open ≤ n and close ≤ open to ensure valid parentheses placement.
 * - Time Complexity is based on Catalan numbers, a common pattern in combinatorial problems.
 */
public class C_GenerateParentheses {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            backtrack(result, new StringBuilder(), 0, 0, n);
            return result;
        }

        private void backtrack(List<String> result, StringBuilder current, int open, int close, int n) {
            if (current.length() == 2 * n) { // Base case: Valid sequence
                result.add(current.toString());
                return;
            }

            if (open < n) { // Add '(' if possible
                current.append('(');
                backtrack(result, current, open + 1, close, n);
                current.deleteCharAt(current.length() - 1); // Backtrack
            }

            if (close < open) { // Add ')' if valid
                current.append(')');
                backtrack(result, current, open, close + 1, n);
                current.deleteCharAt(current.length() - 1); // Backtrack
            }
        }

        public static void main(String[] args) {
            C_GenerateParentheses gp = new C_GenerateParentheses();
            System.out.println(gp.generateParenthesis(3));
        }
    
}
