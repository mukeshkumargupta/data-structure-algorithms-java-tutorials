package com.interview.stackqueue.A_BasicCalculatorPattern;

import java.util.Stack;

public class A_E_PrefixToPostfix {
        /*
    5. Prefix to Postfix Conversion
        Approach:
        Traverse the prefix expression from right to left.
        Use a stack to store intermediate postfix expressions.
        For every operand, push it onto the stack.
        For every operator, pop two operands from the stack, create a combined postfix expression, and push it back onto the stack.

        Time Complexity:
        O(n): Each character is processed once.
        Space Complexity:
        O(n): Stack stores operands and intermediate results.
     */

    public static class PrefixToPostfix {
        // Function to convert prefix to postfix
        public static String prefixToPostfix(String expression) {
            Stack<String> stack = new Stack<>();

            // Traverse the prefix expression from right to left
            for (int i = expression.length() - 1; i >= 0; i--) {
                char c = expression.charAt(i);

                // If the character is an operand, push it to the stack
                if (Character.isLetterOrDigit(c)) {
                    stack.push(c + "");
                }
                // If the character is an operator, pop two operands from the stack
                else {
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    String postfix = operand1 + operand2 + c;
                    stack.push(postfix);
                }
            }

            // The remaining element in the stack is the result
            return stack.pop();
        }
    }
}
