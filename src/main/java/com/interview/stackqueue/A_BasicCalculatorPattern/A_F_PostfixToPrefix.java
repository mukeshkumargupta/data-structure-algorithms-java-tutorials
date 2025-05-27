package com.interview.stackqueue.A_BasicCalculatorPattern;

import java.util.Stack;

public class A_F_PostfixToPrefix {
       /*
   6. Postfix to Prefix Conversion
    Approach:
    Traverse the postfix expression from left to right.
    Use a stack to store intermediate prefix expressions.
    For every operand, push it onto the stack.
    For every operator, pop two operands from the stack, create a combined prefix expression, and push it back onto the stack.

    Time Complexity:
    O(n): Each character is processed once.
    Space Complexity:
    O(n): Stack stores operands and intermediate results.
    */

    public static class PostfixToPrefix {
        // Function to convert postfix to prefix
        public static String postfixToPrefix(String expression) {
            Stack<String> stack = new Stack<>();

            // Traverse the postfix expression from left to right
            for (char c : expression.toCharArray()) {
                // If the character is an operand, push it to the stack
                if (Character.isLetterOrDigit(c)) {
                    stack.push(c + "");
                }
                // If the character is an operator, pop two operands from the stack
                else {
                    String operand2 = stack.pop();
                    String operand1 = stack.pop();
                    String prefix = c + operand1 + operand2;
                    stack.push(prefix);
                }
            }

            // The remaining element in the stack is the result
            return stack.pop();
        }
    }
}
