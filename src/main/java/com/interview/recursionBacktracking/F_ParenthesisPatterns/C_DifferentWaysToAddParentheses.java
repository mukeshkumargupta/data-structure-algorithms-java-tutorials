package com.interview.recursionBacktracking.F_ParenthesisPatterns;

import java.util.*;

/**
 https://leetcode.com/problems/different-ways-to-add-parentheses/
 */
public class C_DifferentWaysToAddParentheses {
    /*
    ✅ Approach 1: Divide and Conquer (Recursive)
🔍 Idea:
For each operator in the expression, split the string into left and right subexpressions.

Recursively compute the results from left and right sides.

Combine each result from left with each from right using the current operator.

Base case: if the expression is just a number, return that number as the only result.
⏱️ Time & Space Complexity:
Time: Exponential O(2^n) in worst case, where n is the number of operators.

Space: O(2^n) for recursive stack and result storage.


     */
    private static class DevideAndConquerApproachBruitforce  {
        public List<Integer> diffWaysToCompute(String expression) {
            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                if (ch == '+' || ch == '-' || ch == '*') {
                    // Split expression
                    String left = expression.substring(0, i);
                    String right = expression.substring(i + 1);

                    List<Integer> leftResults = diffWaysToCompute(left);
                    List<Integer> rightResults = diffWaysToCompute(right);

                    // Combine results
                    for (int l : leftResults) {
                        for (int r : rightResults) {
                            if (ch == '+') result.add(l + r);
                            else if (ch == '-') result.add(l - r);
                            else result.add(l * r);
                        }
                    }
                }
            }

            // Base case: if expression is a number
            if (result.isEmpty()) {
                result.add(Integer.parseInt(expression));
            }

            return result;
        }
    }

    /*
    ✅ Approach 2: Memoized Recursion (Top-down DP)
    🔍 Idea:
    Use a HashMap to cache results for already computed substrings.

    Avoid recomputation for same subexpression.
        ⏱️ Time & Space Complexity (Memoized):
    Time: O(n^2 * 2^n) where n is length of expression; number of subexpressions is bounded and reused.

    Space: O(n^2) for memo and O(n) recursion stack.

    🆚 Which Approach is Better?
    Approach	Time	Space	When to Use
    Recursive (DC)	O(2^n)	O(2^n)	For small input size
    Memoized (DP)	O(n^2*2^n)	O(n^2)	More optimal for large inputs (recommended)

     */
    private static class MemoizedRecursionTopdownApproach {
        Map<String, List<Integer>> memo = new HashMap<>();

        public List<Integer> diffWaysToCompute(String expression) {
            if (memo.containsKey(expression)) return memo.get(expression);

            List<Integer> result = new ArrayList<>();

            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                if (ch == '+' || ch == '-' || ch == '*') {
                    String left = expression.substring(0, i);
                    String right = expression.substring(i + 1);

                    List<Integer> leftResults = diffWaysToCompute(left);
                    List<Integer> rightResults = diffWaysToCompute(right);

                    for (int l : leftResults) {
                        for (int r : rightResults) {
                            if (ch == '+') result.add(l + r);
                            else if (ch == '-') result.add(l - r);
                            else result.add(l * r);
                        }
                    }
                }
            }

            if (result.isEmpty()) {
                result.add(Integer.parseInt(expression));
            }

            memo.put(expression, result);
            return result;
        }
    }
}
