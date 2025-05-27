package com.interview.stackqueue.B_ParanethesisPatterns;

import java.util.*;

public class B_B_RemoveInvalidParentheses {

    /*
    https://leetcode.com/problems/remove-invalid-parentheses/
    Category: Hard, Facebook, FAANG, Backtracking
        https://www.youtube.com/watch?v=y7Us-H5um0M
        Related:
        https://leetcode.com/problems/valid-parentheses/ Easy
        https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/ Medium
    🔵 Explanation of Approach
    Step 1: Count Minimum Invalid Parentheses (getMinInvalid)
    Use a stack to determine how many ( and ) are unbalanced.

    If a ) doesn't have a matching (, push it.

    If a ( remains unmatched at the end, it is also counted as an invalid parenthesis.

    The total size of the stack gives the minimum number of removals needed.

    Step 2: Backtracking to Generate Valid Strings (solve)
    Memoization (Map<String, Integer> memo) prevents re-processing the same string.

    If minInvalid == 0 and the string is valid, add it to the result set.

    Try removing each character (only ( or )) and recursively check if it leads to a valid expression.

    Stop if more than the needed removals have been made.

    🔥 Why This Approach Works Well
    ✅ Backtracking avoids brute-force checking of all substrings
    ✅ Memoization (memo) avoids redundant processing
    ✅ HashSet (result) prevents duplicate results
    Backtracking with Memoization (Best)	TC : O(2^N(Bracktracking) * N(validating )	SC: O(N) memoization	Prunes invalid states early
     */
    private static class BruitForce {
        private Set<String> result = new HashSet<>();
        private Map<String, Integer> memo = new HashMap<>();

        public List<String> removeInvalidParentheses(String s) {
            int minInvalid = getMinInvalid(s); // Step 1: Find minimum invalid parentheses
            solve(s, minInvalid);
            return new ArrayList<>(result);
        }

        // Step 1: Count the minimum invalid parentheses to remove
        private int getMinInvalid(String s) {
            Stack<Character> stack = new Stack<>();
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
            }
            return stack.size();
        }

        // Step 2: Use Backtracking to remove invalid parentheses
        private void solve(String s, int minInvalid) {
            // Memoization: Avoid re-processing the same string
            if (memo.containsKey(s)) {
                return;
            }
            memo.put(s, 1);

            // Base Case: Stop if removals exceed needed
            if (minInvalid < 0) {
                return;
            }

            // If there are no more invalid parentheses and the string is valid, add to result
            if (minInvalid == 0 && getMinInvalid(s) == 0) {
                result.add(s);
                return;
            }

            // Try removing each character one by one
            for (int i = 0; i < s.length(); i++) {
                String newStr = s.substring(0, i) + s.substring(i + 1); // Remove the character at index i
                solve(newStr, minInvalid - 1);
            }
        }
    }

    private static class Better {
        private Set<String> result = new HashSet<>();
        private Map<String, Integer> memo = new HashMap<>();

        public List<String> removeInvalidParentheses(String s) {
            int minInvalid = getMinInvalid(s); // Step 1: Find minimum invalid parentheses
            solve(s, minInvalid);
            return new ArrayList<>(result);
        }

        // Step 1: Count the minimum invalid parentheses to remove
        private int getMinInvalid(String s) {//Remove stack
            int openCount = 0;  // Unmatched '(' count
            int missingClose = 0; // Extra ')' count
            for (char c : s.toCharArray()) {  //jsut remove the stack
                if (c == '(') {
                    openCount++;
                } else if (c == ')') {
                    if (openCount > 0) {
                        openCount--; // Valid match found, decrease openCount
                    } else {
                        missingClose++; // Extra ')' needs an open '('
                    }
                }
            }
            return openCount + missingClose;
        }

        // Step 2: Use Backtracking to remove invalid parentheses
        private void solve(String s, int minInvalid) {
            // Memoization: Avoid re-processing the same string
            if (memo.containsKey(s)) {
                return;
            }
            memo.put(s, 1);

            // Base Case: Stop if removals exceed needed
            if (minInvalid < 0) {
                return;
            }

            // If there are no more invalid parentheses and the string is valid, add to result
            if (minInvalid == 0 && getMinInvalid(s) == 0) {
                result.add(s);
                return;
            }

            // Try removing each character one by one
            for (int i = 0; i < s.length(); i++) {
                String newStr = s.substring(0, i) + s.substring(i + 1); // Remove the character at index i
                solve(newStr, minInvalid - 1);
            }
        }
    }

}
