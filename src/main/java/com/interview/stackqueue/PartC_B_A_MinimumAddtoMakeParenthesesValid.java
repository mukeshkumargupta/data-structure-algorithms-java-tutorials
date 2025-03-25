package com.interview.stackqueue;

import java.util.Stack;

/*
https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/?envType=problem-list-v2&envId=7p59281&utm_source=chatgpt.com
Category: Facebook, FAANG, Medium
https://www.youtube.com/watch?v=ViJHGZ7la2U
https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/description/?envType=problem-list-v2&envId=7p59281&utm_source=chatgpt.com
Related:
https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/ Medium
A parentheses string is valid if and only if:

It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.



Example 1:

Input: s = "())"
Output: 1
Example 2:

Input: s = "((("
Output: 3


Constraints:

1 <= s.length <= 1000
s[i] is either '(' or ')'.
 */
public class PartC_B_A_MinimumAddtoMakeParenthesesValid {

    /*
    🔹 Idea:
    Traverse the string and maintain a stack.
    Push ( into the stack.
    If ) appears:
    If the stack is non-empty (contains (), pop the stack (valid pair found).
    Otherwise, increase the counter (missingOpens++).
    Finally, the stack size gives the count of missing ).
    TC: O(N) SC O (N)
     */
    private static class BruitForce {
        public int minAddToMakeValidBruteForce(String s) {
            Stack<Character> stack = new Stack<>();
            int missingOpens = 0;

            for (char c : s.toCharArray()) {
                if (c == '(') {
                    stack.push(c);
                } else { // c == ')'
                    if (!stack.isEmpty()) {
                        stack.pop(); // Remove a matching '('
                    } else {
                        missingOpens++; // There's an extra ')'
                    }
                }
            }

            return missingOpens + stack.size(); // Extra '(' remain in stack
        }

        public static void main(String[] args) {
            BruitForce solution = new BruitForce();
            System.out.println(solution.minAddToMakeValidBruteForce("())"));  // Output: 1
            System.out.println(solution.minAddToMakeValidBruteForce("((("));  // Output: 3
            System.out.println(solution.minAddToMakeValidBruteForce("()"));   // Output: 0
            System.out.println(solution.minAddToMakeValidBruteForce("()))((")); // Output: 4
        }
    }

    /*
    🟢 Optimized Approach (O(N))
    🔹 Idea (Two Counters):
    Instead of using a stack, we maintain two counters:

    openCount → Keeps track of unmatched (
    missingClose → Counts extra ) that don't have a matching (
    🔹 Steps:
    Traverse s:
    If ( → Increment openCount
    If ):
    If openCount > 0 → Pair it with an ( by decrementing openCount
    Otherwise, increment missingClose (extra ) found)
    Final answer = openCount + missingClose
    openCount → Unmatched ( left
    missingClose → Unmatched ) encountered
    TC: O(N) SC: O( 1)
     */
    private static class Optimized {
        public int minAddToMakeValidOptimized(String s) {
            int openCount = 0;  // Unmatched '(' count
            int missingClose = 0; // Extra ')' count

            for (char c : s.toCharArray()) {
                if (c == '(') {
                    openCount++;
                } else { // c == ')'
                    if (openCount > 0) {
                        openCount--; // Valid match found, decrease openCount
                    } else {
                        missingClose++; // Extra ')' needs an open '('
                    }
                }
            }

            return openCount + missingClose;
        }

        public static void main(String[] args) {
            Optimized solution = new Optimized();
            System.out.println(solution.minAddToMakeValidOptimized("())"));  // Output: 1
            System.out.println(solution.minAddToMakeValidOptimized("((("));  // Output: 3
            System.out.println(solution.minAddToMakeValidOptimized("()"));   // Output: 0
            System.out.println(solution.minAddToMakeValidOptimized("()))((")); // Output: 4
        }
    }
}
